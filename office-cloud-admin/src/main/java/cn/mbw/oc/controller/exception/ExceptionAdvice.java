package cn.mbw.oc.controller.exception;

import cn.mbw.oc.common.results.ResponseResults;
import cn.mbw.oc.common.throwable.ServiceException;
import com.google.gson.Gson;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.authz.AuthorizationException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 控制器异常处理
 *
 * @author haoyun.zheng mabowen
 */
@Slf4j
//@ControllerAdvice
public class ExceptionAdvice {
    final static String DEFAULT_ERROR_MSG = "当前请求出现错误,请重试或者联系管理员";

    private static final String ERROR_403_VIEW = "errors/403.html";
    private static final String ERROR_500_VIEW = "errors/500.html";

    @ExceptionHandler(AuthorizationException.class)
    public Object authorizationException(AuthorizationException exception, HttpServletRequest request, HttpServletResponse response) throws IOException {
        log.debug("访问权限异常: {}", exception);
        if (isAjaxRequest(request)) {
            handleAjaxException(response, exception);
            return null;
        } else {
            return handleException(exception, ERROR_403_VIEW);
        }
    }

    @ExceptionHandler(ServiceException.class)
    public Object serviceException(ServiceException exception, HttpServletRequest request, HttpServletResponse response) throws IOException {
        log.debug("服务器数据异常: {}", exception);
        if (isAjaxRequest(request)) {
            handleAjaxException(response, exception);
            return null;
        } else {
            return handleException(exception, ERROR_500_VIEW);
        }
    }

    @ExceptionHandler(Exception.class)
    public Object exception(Exception exception, HttpServletRequest request, HttpServletResponse response) throws IOException {
        log.debug("服务器数据异常: {}", exception);
        if (isAjaxRequest(request)) {
            handleAjaxException(response, exception);
            return null;
        } else {
            return handleException(exception, ERROR_500_VIEW);
        }
    }

    /**
     * 处理Ajax请求异常
     *
     * @author haoyun.zheng
     */
    private void handleAjaxException(HttpServletResponse response, Exception exception) throws IOException {
        response.setCharacterEncoding("UTF-8");
        response.setContentType("application/json; charset=utf-8");
        response.sendRedirect(new Gson().toJson(ResponseResults.newFailed(exception.getMessage())));
    }

    /**
     * 处理请求异常
     *
     * @author haoyun.zheng
     */
    private ModelAndView handleException(Exception exception, String exceptionView) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("exception", exception.toString());
        modelAndView.setViewName(exceptionView);

        return modelAndView;
    }

    /**
     * 判断某个请求是否是ajax请求
     *
     * @return 返回true, 是ajax请求，false则不是
     * @author haoyun.zheng
     */
    private boolean isAjaxRequest(HttpServletRequest request) {
        return request.getHeader("X-Requested-With") != null &&
                "XMLHttpRequest".equals(request.getHeader("X-Requested-With"));
    }

}
