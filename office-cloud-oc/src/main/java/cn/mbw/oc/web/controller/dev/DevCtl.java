package cn.mbw.oc.web.controller.dev;

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
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.validation.constraints.NotBlank;
import java.util.List;

/**
 * @author Mabowen
 * @date 2019-12-25 15:38
 */
@Slf4j
@Controller
@RequestMapping("dev")
public class DevCtl {

    @GetMapping("index")
    public String layuiIndex() {
        return "layui/layedit.html";
    }

    @PostMapping("layedit/upload")
    @ResponseBody
    public ResponseResults layeditUploadArticle(MultipartFile file) {
        try {

            System.out.println(file.getOriginalFilename());
            return ResponseResults.newSuccess();
        } catch (ServiceException e) {
            return ResponseResults.newFailed();
        }
    }
}
