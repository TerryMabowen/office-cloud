package cn.mbw.oc.controller;

import cn.mbw.oc.common.results.ResponseResults;
import cn.mbw.oc.common.throwable.ServiceException;
import cn.mbw.oc.controller.base.BaseDataCtl;
import cn.mbw.oc.controller.fb.RegisterUserFB;
import cn.mbw.oc.data.user.dto.UserDTO;
import cn.mbw.oc.service.user.UserService;
import com.baidu.unbiz.fluentvalidator.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;

/**
 * @author Mabowen
 * @date 2020-01-10 19:08
 */
@Slf4j
@Controller
public class RegistCtl extends BaseDataCtl {
    @Autowired
    private UserService userService;

    @PostMapping("/register")
    public ResponseResults register(RegisterUserFB userFB){
        try {
            Result result = validate(userFB);
            if (!result.isSuccess()) {
                return ResponseResults.newFailed(result.getErrors().toString());
            }

            UserDTO userDTO = mapper.map(userFB, UserDTO.class);

            userService.register(userDTO);

            return ResponseResults.newSuccess("注册成功");
        } catch (ServiceException e) {
            e.printStackTrace();
            log.error("注册失败" + e.getMessage(), e);
            return ResponseResults.newFailed(e.getMessage());
        }
    }
}
