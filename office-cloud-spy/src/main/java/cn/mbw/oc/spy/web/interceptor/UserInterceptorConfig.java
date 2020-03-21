package cn.mbw.oc.spy.web.interceptor;
import cn.mbw.oc.spy.web.controller.base.BaseCtl;
import cn.mbw.oc.spy.spi.data.user.BaseUser;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 用户拦截器
 *
 * @author haoyun.zheng
 */
@Slf4j
public class UserInterceptorConfig implements HandlerInterceptor {

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) {
        if (handler instanceof HandlerMethod) {
            registerCurrentLoginUser((HandlerMethod) handler, modelAndView);
        }
    }

    private void registerCurrentLoginUser(HandlerMethod handlerMethod, ModelAndView modelAndView) {
        boolean extendsBaseController = BaseCtl.class.isAssignableFrom(handlerMethod.getBeanType());
        if (extendsBaseController) {
            addUsernameAttribute(modelAndView);
        }
    }

    private void addUsernameAttribute(ModelAndView modelAndView) {
        try {
            BaseUser user = (BaseUser) SecurityUtils.getSubject().getPrincipal();
            if (user != null && StringUtils.isNotBlank(user.getLoginName())) {
                modelAndView.addObject("username", user.getLoginName());
            }
        } catch (Exception ignored) {
        }
    }
}
