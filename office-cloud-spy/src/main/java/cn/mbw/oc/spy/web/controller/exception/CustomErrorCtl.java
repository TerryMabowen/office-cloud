package cn.mbw.oc.spy.web.controller.exception;

import cn.mbw.oc.common.results.ResponseResults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.error.ErrorAttributes;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.context.request.ServletWebRequest;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import java.util.Map;

/**
 * 控制器异常处理
 *
 * @author haoyun.zheng mabowen
 */
@Slf4j
//@Controller
public class CustomErrorCtl implements ErrorController {

    private static final String ERROR_404_VIEW = "errors/404.html";
    private static final String ERROR_500_VIEW = "errors/500.html";

    @Autowired
    private ErrorAttributes errorAttributes;

    /**
     * 页面错误处理
     *
     * @author haoyun.zheng
     */
    @RequestMapping(value = "/error", produces = MediaType.TEXT_HTML_VALUE)
    public String errorHtml(HttpServletRequest request, Model model) {
        ServletWebRequest webRequest = new ServletWebRequest(request);
        Map<String, Object> body = this.errorAttributes.getErrorAttributes(webRequest, true);

        Object status = request.getAttribute(RequestDispatcher.ERROR_STATUS_CODE);
        model.addAttribute("body", body);

        if (status != null) {
            int statusCode = Integer.parseInt(status.toString());
            if (statusCode == HttpStatus.NOT_FOUND.value()) {
                return ERROR_404_VIEW;
            } else {
                model.addAttribute("exception", "页面请求失败，状态码:" + statusCode);
            }
        } else {
            model.addAttribute("exception", ExceptionAdvice.DEFAULT_ERROR_MSG);
        }

        return ERROR_500_VIEW;
    }

    /**
     * 接口错误处理
     *
     * @author haoyun.zheng
     */
    @RequestMapping(value = "/error", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseResults errorJson(HttpServletRequest request, Model model) {
        ServletWebRequest webRequest = new ServletWebRequest(request);
        Map<String, Object> body = this.errorAttributes.getErrorAttributes(webRequest, true);

        Object status = request.getAttribute(RequestDispatcher.ERROR_STATUS_CODE);

        if (status != null) {
            int statusCode = Integer.parseInt(status.toString());
            if (statusCode == HttpStatus.NOT_FOUND.value()) {
                return ResponseResults
                        .newFailed("接口没有找到.")
                        .setStatus(statusCode)
                        .setCode(statusCode)
                        .setData(body);
            }

            return ResponseResults.newFailed("请求错误")
                    .setStatus(statusCode)
                    .setCode(statusCode)
                    .setData(body);
        }

        return ResponseResults.newFailed("未处理的HTTP错误", body);
    }

    /**
     * 返回错误的路径
     *
     * @author haoyun.zheng
     */
    @Override
    public String getErrorPath() {
        return "/error";
    }
}
