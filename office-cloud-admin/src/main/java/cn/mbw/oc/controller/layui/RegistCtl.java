package cn.mbw.oc.controller.layui;

import cn.mbw.oc.common.results.ResponseResults;
import cn.mbw.oc.common.throwable.ServiceException;
import cn.mbw.oc.controller.base.BaseDataCtl;
import cn.mbw.oc.controller.layui.fb.RegisterUserFB;
import cn.mbw.oc.data.user.dto.UserDTO;
import cn.mbw.oc.service.user.admin.UserService;
import com.baidu.unbiz.fluentvalidator.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

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
