/**
 *
 */
package cn.mbw.oc.shiro;

import cn.mbw.oc.common.helper.ApplicationContextHelper;
import cn.mbw.oc.common.results.ResponseResults;
import cn.mbw.oc.constants.CacheKey;
import cn.mbw.oc.data.role.vo.RoleVO;
import cn.mbw.oc.data.user.vo.UserVO;
import cn.mbw.oc.service.user.admin.UserService;
import com.google.gson.Gson;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.filter.authc.FormAuthenticationFilter;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

/**
 * @author Mabowen
 * @date 2020/02/27 22:56
 */
@Slf4j
public class AdminFormAuthenticationFilter extends FormAuthenticationFilter {

	@Override
	protected boolean onAccessDenied(ServletRequest request, ServletResponse response) throws Exception {
		boolean isAjaxRequest = isAjaxRequest(request);
		HttpServletRequest httpServletRequest = (HttpServletRequest) request;

		//判断是否是登录请求
		if(isLoginRequest(request, response)) {
			if (isLoginSubmission(request, response)) {
				String vcode = request.getParameter("code");
				String vvcode = (String) httpServletRequest.getSession().getAttribute("code");
				boolean codeValid = vcode != null && vcode.equalsIgnoreCase(vvcode);
				// 是ajax登录请求
				if (isAjaxRequest) {
					// 先判断验证码
					if (codeValid) {
						return executeLogin(request, response);
					} else {
						ResponseResults data = new ResponseResults(HttpServletResponse.SC_FORBIDDEN, "验证码错误", null);
						responseJson(response, data);

						return false;
					}
				} else {
					if (codeValid) {
						return executeLogin(request, response);
					} else {
						request.setAttribute("loginError", "验证码错误.");
						return true;
					}
				}
			} else {
				return true;
			}
		} else {
			if (isAjaxRequest) {
				//没有登录
				ResponseResults data = new ResponseResults(HttpServletResponse.SC_FORBIDDEN, "没有登录", null);
				responseJson(response, data);
			} else {
				saveRequestAndRedirectToLogin(request, response);
			}

			return false;
		}
	}

	@Override
	protected boolean onLoginFailure(AuthenticationToken token, AuthenticationException e, ServletRequest request,
			ServletResponse response) {
		if (isAjaxRequest(request)) {
			ResponseResults data = new ResponseResults(HttpServletResponse.SC_MOVED_PERMANENTLY, "登录失败,用户名/密码错误", null);
			try {
				responseJson(response, data);
			} catch (IOException e1) {
				log.error(e1.getMessage());
			}
			return false;
		} else {
			request.setAttribute("loginError", "登录失败,用户名/密码错误");

			return true;
		}
	}

	@Override
	protected boolean onLoginSuccess(AuthenticationToken token, Subject subject, ServletRequest request,
			ServletResponse response) throws Exception {
		
		//设置初始默认角色
		HttpServletRequest servletRquest = (HttpServletRequest)request;
		UserVO user = (UserVO) subject.getPrincipal();
		UserService userService = ApplicationContextHelper.getBean(UserService.class);
		List<RoleVO> roles = userService.getRoles(user.getId());
		boolean isUpdate = true;
		Long currenRoleId = null;
		if(servletRquest.getSession().getAttribute(CacheKey.CURRENT_ROLE_ID) != null) {
			Long roleId = (Long) servletRquest.getSession().getAttribute(CacheKey.CURRENT_ROLE_ID);
			for (RoleVO role : roles) {
				if(role.getId().equals(roleId)) {
					isUpdate = false;
					currenRoleId = roleId;
				}
			}
		}
		if(isUpdate){
			if (roles != null && !roles.isEmpty()) {
				currenRoleId = roles.get(0).getId();
				servletRquest.getSession().setAttribute(CacheKey.CURRENT_ROLE_ID, roles.get(0).getId());
				servletRquest.getSession().setAttribute(CacheKey.CURRENT_ROLE_NAME, roles.get(0).getName());
			}
		}
		
		subject.getSession().setAttribute(CacheKey.CURRENT_ROLE_ID, currenRoleId);
		
		if (isAjaxRequest(request)) {
			ResponseResults data = new ResponseResults(ResponseResults.OK, "登录成功", null);
			responseJson(response, data);
			return false;
		} else {
			return super.onLoginSuccess(token, subject, request, response);
		}
	}

	private boolean isAjaxRequest(ServletRequest request) {
		HttpServletRequest httpServletRequest = (HttpServletRequest) request;
		String requestedWith = httpServletRequest.getHeader("X-Requested-With");

		return "XMLHttpRequest".equalsIgnoreCase(requestedWith);
	}

	private void responseJson(ServletResponse response, Object data) throws IOException {
		HttpServletResponse res = (HttpServletResponse) response;
		res.setContentType("text/plain;charset=UTF-8");
		res.setCharacterEncoding("UTF-8");
		res.sendRedirect(new Gson().toJson(data));
	}

}
