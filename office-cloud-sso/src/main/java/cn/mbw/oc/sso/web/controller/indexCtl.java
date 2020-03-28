package cn.mbw.oc.sso.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * TODO
 *
 * @author Mabowen
 * @date 2020-03-28 19:30
 */
@Controller
@RequestMapping
public class indexCtl {

    @RequestMapping(value = {"/index.html", "/index", "/", ""})
    public String index(Model model) {
        model.addAttribute("username", "李四");
        return "index.html";
    }
}
