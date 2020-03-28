package cn.mbw.oc.sso.biz.service.user.admin;

import cn.mbw.oc.sso.spi.data.permission.vo.PermissionVO;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.compress.utils.Lists;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author Mabowen
 * @date 2020/02/27 23:39
 */
@Slf4j
@Service
@Transactional(readOnly = true)
public class PermissionService {

    public List<PermissionVO> getPermissionByRoleId(Long currentRoleId) {
        List<PermissionVO> permissionVOS = Lists.newArrayList();
        permissionVOS.add(new PermissionVO(1L, "admin"));
        return permissionVOS;
    }
}
