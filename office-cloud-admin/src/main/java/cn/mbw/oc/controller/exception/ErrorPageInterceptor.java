package cn.mbw.oc.controller.exception;

import org.springframework.boot.web.server.ErrorPage;
import org.springframework.boot.web.server.ErrorPageRegistrar;
import org.springframework.boot.web.server.ErrorPageRegistry;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;

/**
 * @author Mabowen
 * @date 2020-03-09 18:52
 */
@Component
@Controller("error")
public class ErrorPageInterceptor implements ErrorPageRegistrar {
    private static final String ERROR_403_VIEW = "/errors/403.html";
    private static final String ERROR_404_VIEW = "/errors/404.html";
    private static final String ERROR_500_VIEW = "/errors/500.html";

    @Override
    public void registerErrorPages(ErrorPageRegistry registry) {
        /*1、按错误的类型显示错误的网页*/
        ErrorPage e403 = new ErrorPage(HttpStatus.FORBIDDEN, ERROR_403_VIEW);
        /*错误类型为404，找不到网页的，默认显示404.html网页*/
        ErrorPage e404 = new ErrorPage(HttpStatus.NOT_FOUND, ERROR_404_VIEW);
        /*错误类型为500，表示服务器响应错误，默认显示500.html网页*/
        ErrorPage e500 = new ErrorPage(HttpStatus.INTERNAL_SERVER_ERROR, ERROR_500_VIEW);
        registry.addErrorPages(e403, e404, e500);
    }
}
