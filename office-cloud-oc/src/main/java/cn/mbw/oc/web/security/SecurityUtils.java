/**
 * 
 */
package cn.mbw.oc.web.security;


import cn.mbw.oc.spi.data.user.vo.UserVO;

/**
 * @author Mabowen
 * @date 2019-12-26 09:20
 */
public class SecurityUtils {
	private static ThreadLocal<UserVO> subjectLocal = new ThreadLocal<UserVO>();

	public static UserVO getSubject() {
		return subjectLocal.get();
	}
	
	public static void setSubject(UserVO userSubject) {
		subjectLocal.set(userSubject);
	}
}
