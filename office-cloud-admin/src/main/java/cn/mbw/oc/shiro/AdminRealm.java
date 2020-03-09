/**
 *
 */
package cn.mbw.oc.shiro;

import cn.mbw.oc.constants.CacheKey;
import cn.mbw.oc.data.permission.vo.PermissionVO;
import cn.mbw.oc.data.role.vo.RoleVO;
import cn.mbw.oc.data.user.vo.UserVO;
import cn.mbw.oc.service.user.admin.PermissionService;
import cn.mbw.oc.service.user.admin.UserService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationException;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * @author Mabowen
 * @date 2020/02/27 22:56
 */
public class AdminRealm extends AuthorizingRealm {
	@Autowired
	private UserService userService;
	@Autowired
	private PermissionService permissionService;

	// 授权
	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
		if (principals == null) {
			throw new AuthorizationException("认证失败, principals不能为空.");
		}

		SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
		if (principals.fromRealm(getName()).iterator().hasNext()) {
			UserVO user = (UserVO) principals.fromRealm(getName()).iterator().next();
			
			//授权当前角色权限
			Long currentRoleId = (Long) SecurityUtils.getSubject().getSession().getAttribute(CacheKey.CURRENT_ROLE_ID);
			List<PermissionVO> permissions = permissionService.getPermissionByRoleId(currentRoleId);
			permissions.forEach(permission -> {
				info.addStringPermission(permission.getCode());
				String [] pers = permission.getCode().split("\\.");
				if(pers.length > 0) {
					String per = null;
					for (String per1 : pers) {
						per = (per == null ? "" : (per + ".")) + per1;
						info.addStringPermission(per);
					}
				}
			});

			SecurityUtils.getSubject().getSession().setAttribute("sysUserPermissions", user.getPermissionCodes());

			// 默认，所有用户拥有BASE_USERS角色
			info.addRole("BASE_USER");

			List<RoleVO> roles = userService.getRoles(user.getId());
			for (RoleVO role : roles) {
				info.addRole(role.getName());
			}
		}

		return info;
	}

	// 登陆
	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
		UsernamePasswordToken upToken = (UsernamePasswordToken) token;

		try {
			UserVO user = userService.loadUser(upToken.getUsername());

			if (user == null) {
				throw new UnknownAccountException("账户不存在");
			} else {
				SecurityUtils.getSubject().getSession().setAttribute("sessionSysUser", user);

				return new SimpleAuthenticationInfo(user, user.getPassword(), getName());
			}
		} catch (Exception e) {
			throw new AuthenticationException(e.getMessage());
		}
	}

	@Override
	public String getName() {
		return "AdminRealm";
	}

}
