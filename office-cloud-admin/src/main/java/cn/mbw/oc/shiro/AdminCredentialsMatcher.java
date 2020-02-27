/**
 *
 */
package cn.mbw.oc.shiro;

import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.credential.CredentialsMatcher;

/**
 * @author Mabowen
 * @date 2020/02/27 22:56
 */
public class AdminCredentialsMatcher implements CredentialsMatcher {

	@Override
	public boolean doCredentialsMatch(AuthenticationToken token, AuthenticationInfo auth) {
		return true;
	}

}
