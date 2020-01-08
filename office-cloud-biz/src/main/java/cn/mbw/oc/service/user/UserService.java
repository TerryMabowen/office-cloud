package cn.mbw.oc.service.user;

import cn.mbw.oc.data.user.vo.UserVO;
import cn.mbw.oc.util.valid.AssertUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * @author Mabowen
 * @date 2019-12-26 09:20
 */
@Slf4j
@Service
public class UserService {

    public UserVO findUserByName(String username) {
        AssertUtil.assertNotEmpty(username, "用户名不能为空");
        UserVO userVO = new UserVO();
        userVO.setName("lisi");
        userVO.setPassword("123456");
        return userVO;
    }
}
