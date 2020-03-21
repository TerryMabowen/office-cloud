package cn.mbw.oc.spy.web.controller.base;

import cn.mbw.oc.spy.spi.data.user.vo.UserVO;
import cn.mbw.oc.spy.web.security.SecurityUtils;


/**
 * @author Mabowen
 * @date 2019-12-05 20:02
 */
public abstract class BaseCtl {

    protected UserVO getCurrentLoginUser() {
//        UserVO userVO = (UserVO) SecurityContextHolder
//                .getContext()
//                .getAuthentication()
//                .getPrincipal();

        return SecurityUtils.getSubject();
    }
}
