package cn.mbw.oc.spy.biz.service.user.admin;

import cn.mbw.oc.spy.spi.data.role.vo.RoleVO;
import cn.mbw.oc.spy.spi.data.user.dto.UserDTO;
import cn.mbw.oc.spy.spi.data.user.vo.UserVO;
import cn.mbw.oc.common.kit.valid.AssertUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.List;

/**
 * @author Mabowen
 * @date 2019-12-26 09:20
 */
@Slf4j
@Service
@Transactional(readOnly = true)
public class UserService {

    public UserVO findUserByName(String username) {
        AssertUtil.assertNotEmpty(username, "用户名不能为空");
        UserVO userVO = new UserVO();
        userVO.setName("李四");
        userVO.setLoginName(username);
        userVO.setPassword("123456");
        return userVO;
    }


    public void register(UserDTO userDTO) {

    }

    public List<RoleVO> getRoles(Long id) {
        return Collections.emptyList();
    }

    public UserVO loadUser(String username) {
        UserVO userVO = new UserVO();
        userVO.setLoginName("lisi");
        userVO.setPassword("123456");
        return userVO;
    }
}
