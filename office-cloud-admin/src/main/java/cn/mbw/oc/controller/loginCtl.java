package cn.mbw.oc.controller;

import cn.mbw.oc.controller.base.BaseCtl;
import cn.mbw.oc.data.user.vo.UserVO;
import com.google.gson.Gson;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
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
//    @Autowired
//    private AuthenticationManager authenticationManager;

    @GetMapping("/login")
    public String login(HttpServletRequest request) {
        UserVO currentLoginUser = getCurrentLoginUser();
        if (currentLoginUser != null) {
            System.out.println("loginUser: " + new Gson().toJson(currentLoginUser));
            Subject subject = SecurityUtils.getSubject();
            UsernamePasswordToken usernamePasswordToken = new UsernamePasswordToken(currentLoginUser.getLoginName(), currentLoginUser.getPasswordHash());
            usernamePasswordToken.setRememberMe(true);
            subject.login(usernamePasswordToken);
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

    @GetMapping("logout")
    public String logout() {
        SecurityUtils.getSubject().logout();
        return "redirect:/login";
    }
}
