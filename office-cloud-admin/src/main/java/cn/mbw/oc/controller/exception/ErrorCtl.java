package cn.mbw.oc.controller.exception;

import cn.mbw.oc.controller.base.BaseCtl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author Mabowen
 * @date 2020-03-09 19:32
 */
@Slf4j
@Controller("error")
public class ErrorCtl extends BaseCtl {
    private static final String ERROR_403_VIEW = "errors/403.html";
    private static final String ERROR_404_VIEW = "errors/404.html";
    private static final String ERROR_500_VIEW = "errors/500.html";

    @RequestMapping("403")
    public String e403Html(Model model) {
        return ERROR_403_VIEW;
    }

    @RequestMapping("404")
    public String e404Html(Model model) {
        return ERROR_404_VIEW;
    }

    @RequestMapping("500")
    public String e500Html(Model model) {
        return ERROR_500_VIEW;
    }
}
