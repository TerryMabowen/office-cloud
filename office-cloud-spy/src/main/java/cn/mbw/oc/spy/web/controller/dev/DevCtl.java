package cn.mbw.oc.spy.web.controller.dev;

import cn.mbw.oc.common.results.ResponseResults;
import cn.mbw.oc.common.throwable.ServiceException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

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
