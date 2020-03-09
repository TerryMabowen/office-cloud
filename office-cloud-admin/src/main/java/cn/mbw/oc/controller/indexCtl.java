package cn.mbw.oc.controller;

import cn.mbw.oc.controller.base.BaseCtl;
import cn.mbw.oc.data.user.vo.UserVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author Mabowen
 * @date 2019-12-20 15:54
 */
@Slf4j
@Controller
public class indexCtl extends BaseCtl {

    @GetMapping(value = {"/index.html", "/index", "/", ""})
    public String index(Model model) {
        model.addAttribute("username", "李四");
        return "layui/index.html";
    }
}
