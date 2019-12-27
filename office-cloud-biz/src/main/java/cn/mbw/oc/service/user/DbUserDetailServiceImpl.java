package cn.mbw.oc.service.user;

import cn.mbw.oc.data.user.vo.UserVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Mabowen
 * @date 2019-12-26 09:17
 */
@Slf4j
@Service
public class DbUserDetailServiceImpl implements UserDetailsService {
    @Autowired
    private UserService userService;

    public DbUserDetailServiceImpl (UserService userService) {
        this.userService = userService;
    }

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        //1.查询数据库
        UserVO userVO = userService.findUserByName(s);
        if (userVO == null) {
            throw new UsernameNotFoundException("用户不存在!");
        }
        //2.权限集合（一般也是从数据库查询，这里简化，直接赋值）
        List<GrantedAuthority> grantedAuthorities = new ArrayList<>();
        grantedAuthorities.add(new SimpleGrantedAuthority("USER"));
        //3.用户名、密码、权限集合 构建UserDetails对象
        return new org.springframework.security.core.userdetails.User(userVO.getName(),
                userVO.getPassword(), grantedAuthorities);
    }
}
