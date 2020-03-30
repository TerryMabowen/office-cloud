package cn.mbw.oc.spy.biz.service.user.admin;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author Mabowen
 * @date 2019-12-26 09:17
 */
@Deprecated
@Slf4j
@Service
public class DbUserDetailServiceImpl {
//public class DbUserDetailServiceImpl implements UserDetailsService {
    @Autowired
    private UserService userService;

    public DbUserDetailServiceImpl (UserService userService) {
        this.userService = userService;
    }

//    @Override
//    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
//        //1.查询数据库
//        UserVO userVO = userService.findUserByName(s);
//        if (userVO == null) {
//            throw new UsernameNotFoundException("用户不存在!");
//        }
//        //2.权限集合（一般也是从数据库查询，这里简化，直接赋值）
//        List<GrantedAuthority> grantedAuthorities = new ArrayList<>();
//        grantedAuthorities.add(new SimpleGrantedAuthority("USER"));
//        // todo 角色
//        //3.用户名、密码、权限集合 构建UserDetails对象
//        return new org.springframework.security.core.userdetails.User(userVO.getName(),
//                userVO.getPassword(), grantedAuthorities);
//    }
}
