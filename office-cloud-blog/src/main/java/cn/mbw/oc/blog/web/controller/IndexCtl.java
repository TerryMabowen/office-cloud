package cn.mbw.oc.blog.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * TODO
 *
 * @author Mabowen
 * @date 2020-04-23 17:36
 */
@Controller
public class IndexCtl {

    @GetMapping({"", "/", "/index", "index.html"})
    public String index(Model model) {

        return "index.html";
    }
}
