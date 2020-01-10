package cn.mbw.oc.controller.base;

import cn.mbw.oc.common.throwable.ServiceException;
import cn.mbw.oc.data.user.BaseUser;
import cn.mbw.oc.data.user.vo.UserVO;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;


/**
 * @author Mabowen
 * @date 2019-12-05 20:02
 */
public abstract class BaseCtl {

    protected UserVO getCurrentLoginUser() {
        UserVO principal = (UserVO) SecurityContextHolder
                .getContext()
                .getAuthentication()
                .getPrincipal();
        if (principal == null) {
            throw new ServiceException("用户未登录");
        }
        return principal;
    }
}
