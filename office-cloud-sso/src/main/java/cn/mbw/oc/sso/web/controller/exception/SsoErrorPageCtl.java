package cn.mbw.oc.sso.web.controller.exception;

import cn.mbw.oc.sso.web.controller.base.BaseCtl;
import org.springframework.boot.autoconfigure.web.ErrorProperties;
import org.springframework.boot.autoconfigure.web.servlet.error.BasicErrorController;
import org.springframework.boot.web.servlet.error.ErrorAttributes;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * TODO
 *
 * @author Mabowen
 * @date 2020-03-31 11:37
 */
@Controller
@RequestMapping("sso")
public class SsoErrorPageCtl {


    @RequestMapping("error/400")
    public String error400Page(Model model) {
        return "error/400.html";
    }

    @RequestMapping("error/401")
    public String error401Page(Model model) {
        return "error/401.html";
    }

    @RequestMapping("error/403")
    public String error403Page(Model model) {
        return "error/403.html";
    }

    @RequestMapping("error/404")
    public String error404Page(Model model) {
        return "error/404.html";
    }

    @RequestMapping("error/500")
    public String error500Page(Model model) {
        return "error/500.html";
    }

    @RequestMapping("error")
    public String errorPage(Model model) {
        return "error/error.html";
    }
}
