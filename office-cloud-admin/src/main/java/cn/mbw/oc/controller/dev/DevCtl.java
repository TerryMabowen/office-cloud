package cn.mbw.oc.controller.dev;

import cn.mbw.oc.common.results.ResponseResults;
import cn.mbw.oc.common.throwable.ServiceException;
import cn.mbw.oc.controller.base.BaseCtl;
import cn.mbw.oc.data.user.vo.UserVO;
import cn.mbw.oc.util.AssertUtil;
import cn.mbw.oc.util.ConvertUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotBlank;
import java.util.List;

/**
 * @author Mabowen
 * @date 2019-12-25 15:38
 */
@Slf4j
@Controller
@RequestMapping("dev")
public class DevCtl extends BaseCtl {

    @PostMapping("test1")
    @ResponseBody
    @NotBlank
//    @RequiresPermissions(value = "quanxian/juese")
    public ResponseResults test1(@RequestParam("str") String str) {
        try {
            AssertUtil.assertNotEmpty(str, "str不能为空");

            List<String> list = ConvertUtil.convertStrToList(str);
            return ResponseResults.newSuccess().setData(list);
        } catch (ServiceException e) {
            log.error("test1失败：" + e.getMessage(), e);
            return ResponseResults.newFailed(e.getMessage());
        }
    }

    @GetMapping(value = {"", "/", "/index", "/index.html"})
    @ResponseBody
    public String test2(Model model) {
        UserVO currentLoginUser = getCurrentLoginUser();
        model.addAttribute("user", currentLoginUser);

        return "index.html";
    }

}
