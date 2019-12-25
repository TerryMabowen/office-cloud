package cn.mbw.ofcd.controller;

import cn.mbw.ofcd.controller.base.BaseCtl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @author Mabowen
 * @date 2019-12-20 15:54
 */
@Slf4j
@Controller
public class indexCtl extends BaseCtl {

    @GetMapping(value = {"/index.html", "/index", "/", ""})
    public String index(Model model) {
        model.addAttribute("title", "测试首页");
        return "index.html";
    }
}
