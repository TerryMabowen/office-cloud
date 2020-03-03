package cn.mbw.oc.controller.base;

import cn.mbw.oc.common.throwable.ServiceException;
import cn.mbw.oc.data.user.BaseUser;
import cn.mbw.oc.data.user.vo.UserVO;
import cn.mbw.oc.security.SecurityUtils;


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
        UserVO userVO = SecurityUtils.getSubject();
        if (userVO == null) {
            throw new ServiceException("用户未登录");
        }
        return userVO;
    }
}
