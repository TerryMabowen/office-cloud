package cn.mbw.oc.service.user.admin;

import cn.mbw.oc.data.permission.vo.PermissionVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
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
        return Collections.emptyList();
    }
}
