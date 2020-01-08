package cn.mbw.oc.controller;

import cn.mbw.oc.controller.base.BaseCtl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpServletRequest;

/**
 * @author Mabowen
 * @date 2020-01-08 19:52
 */
@Slf4j
@Controller
public class loginCtl extends BaseCtl {

    @GetMapping("/login")
    public String login(HttpServletRequest request) {
        if (getCurrentLoginUser() != null) {

            return "redirect:/index";
        } else {
            return "/login";
        }
    }

    @GetMapping("logout")
    public String logout() {

        return "/login";
    }
}
