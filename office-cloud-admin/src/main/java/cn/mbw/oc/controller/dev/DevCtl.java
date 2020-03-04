package cn.mbw.oc.controller.dev;

import cn.mbw.oc.common.results.ResponseResults;
import cn.mbw.oc.common.throwable.ServiceException;
import cn.mbw.oc.controller.base.BaseCtl;
import cn.mbw.oc.data.user.vo.UserVO;
import cn.mbw.oc.util.valid.AssertUtil;
import cn.mbw.oc.util.data.ConvertUtil;
import lombok.extern.slf4j.Slf4j;
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

    @GetMapping("jodit/index")
    public String indexHtml(Model model) {

        return "jodit/index.html";
    }

    @PostMapping("jodit/upload")
    public ResponseResults uploadArticle() {
        try {

            return ResponseResults.newSuccess();
        } catch (ServiceException e) {
            return ResponseResults.newFailed();
        }
    }
}
