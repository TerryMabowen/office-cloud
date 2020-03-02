package cn.mbw.oc.listener;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * Spring security 登录成功后，防止session的固化攻击，
 * 会将旧的sessionId销毁，重新生成一个新的sessionId,
 * 因此此处需要做一下处理
 * @author Mabowen
 * @date 2019-12-26 09:57
 */
@Deprecated
@Slf4j
//@Component
public class SessionFixationProtectionEventListener {
//public class SessionFixationProtectionEventListener implements ApplicationListener<SessionFixationProtectionEvent> {
//    @Override
//    public void onApplicationEvent(SessionFixationProtectionEvent event) {
//        String oldSessionId = event.getOldSessionId();
//        String newSessionId = event.getNewSessionId();
//        // 更改sessionId的值
//    }

//    private void reloadUserAuthority(HttpSession session) {
//        // 新的权限
//        List<GrantedAuthority> authorityList = AuthorityUtils.createAuthorityList(new String[]{"从数据库中查询出来"});
//
//        SecurityContext securityContext = (SecurityContext) session.getAttribute(HttpSessionSecurityContextRepository.SPRING_SECURITY_CONTEXT_KEY);
//        Authentication authentication = securityContext.getAuthentication();
////        SecurityUser principal = (SecurityUser) authentication.getPrincipal();
////        principal.setAuthorities(authorityList);
//
//        // 重新new一个token，因为Authentication中的权限是不可变的.
////        UsernamePasswordAuthenticationToken result = new UsernamePasswordAuthenticationToken(
////                principal, authentication.getCredentials(),
////                authorityList);
////        result.setDetails(authentication.getDetails());
////        securityContext.setAuthentication(result);
//    }
}
