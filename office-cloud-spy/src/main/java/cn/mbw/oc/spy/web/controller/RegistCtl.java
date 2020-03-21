package cn.mbw.oc.spy.web.controller;

import cn.mbw.oc.spy.web.controller.base.BaseDataCtl;
import cn.mbw.oc.spy.biz.service.user.admin.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @author Mabowen
 * @date 2020-01-10 19:08
 */
@Slf4j
@Controller
public class RegistCtl extends BaseDataCtl {
    @Autowired
    private UserService userService;

    @GetMapping("/register")
    public String registerHtml(Model model) {
        return "layui/register.html";
    }

}
