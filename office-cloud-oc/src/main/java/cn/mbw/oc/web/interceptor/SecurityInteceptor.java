/**
 * 
 */
package cn.mbw.oc.web.interceptor;

import cn.mbw.oc.data.user.vo.UserVO;
import cn.mbw.oc.interceptor.annotations.Auth;
import cn.mbw.oc.security.SecurityUtils;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author Mabowen
 * @date 2019-12-26 09:20
 */
public class SecurityInteceptor extends HandlerInterceptorAdapter {
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		
		if(handler instanceof HandlerMethod) {
			if(request.getSession(true).getAttribute("sessionUser") != null) {
				UserVO subject = (UserVO)request.getSession(true).getAttribute("sessionUser");
				SecurityUtils.setSubject(subject);
			}
			
			HandlerMethod method = (HandlerMethod)handler;
			Auth auth = method.getMethodAnnotation(Auth.class);
			
			if(auth != null) {
				if(request.getSession(true).getAttribute("sessionUser") != null) {
					return true;
				} else {
					String contextPath = request.getContextPath() + "/login";
					response.sendRedirect(contextPath);
					return false;
				}
			}
		}

		return true;
	}

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		SecurityUtils.setSubject(null);
		super.postHandle(request, response, handler, modelAndView);
	}

}
