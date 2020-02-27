package cn.mbw.oc.controller;

import cn.mbw.oc.controller.base.BaseCtl;
import cn.mbw.oc.data.user.vo.UserVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpServletRequest;

/**
 * @author Mabowen
 * @date 2020-01-08 19:52
 */
@Slf4j
@Controller
public class loginCtl extends BaseCtl {
    @Autowired
    private AuthenticationManager authenticationManager;

    @GetMapping("/login")
    public String login(HttpServletRequest request) {
        UserVO currentLoginUser = getCurrentLoginUser();
        if (currentLoginUser != null) {
            UsernamePasswordAuthenticationToken token =
                    new UsernamePasswordAuthenticationToken(currentLoginUser.getUsername(), currentLoginUser.getPassword());
            Authentication authentication = authenticationManager.authenticate(token);
            SecurityContextHolder.getContext().setAuthentication(authentication);
            return "redirect:/index";
        } else {
            return "login.html";
        }
    }

    @GetMapping("/login_error")
    public String loginError(Model model) {
        model.addAttribute("loginError", true);
        model.addAttribute("errorMsg", "登录失败,用户名或者密码错误!");

        return "login.html";
    }

    @PreAuthorize("isAuthenticated()")
    @GetMapping("logout")
    public String logout() {

        return "redirect:/login";
    }
}
