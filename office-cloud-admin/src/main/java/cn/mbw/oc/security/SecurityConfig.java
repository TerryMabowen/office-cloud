package cn.mbw.oc.security;

import cn.mbw.oc.filter.AfterCsrfFilter;
import cn.mbw.oc.filter.BeforeLoginFilter;
import cn.mbw.oc.service.user.DbUserDetailServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.csrf.CsrfFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

/**
 * @author Mabowen
 * @date 2019-12-25 19:18
 */
@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    @Autowired
    private DbUserDetailServiceImpl userDetailService;

    /**
     * 配置验证规则
     */
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                .antMatchers("/").permitAll()
                //必须有“USER”角色的才能访问
                .antMatchers("/user/**").hasAuthority("USER")
                .and()
                //登陆成功以后默认访问路径
                .formLogin().loginPage("/login").defaultSuccessUrl("/user")
                .and()
                //注销以后默认访问路径
                .logout().invalidateHttpSession(true)
                .clearAuthentication(true)
                .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
                .logoutSuccessUrl("/login").permitAll()
                .and()
                // 记住我 配置
                .rememberMe().key("unique-and-secret")
                .rememberMeCookieName("remember-me-cookie-name")
                .tokenValiditySeconds(24 * 60 * 60);
        // 在 UsernamePasswordAuthenticationFilter 前添加 BeforeLoginFilter
        http.addFilterBefore(new BeforeLoginFilter(), UsernamePasswordAuthenticationFilter.class);
        // 在 CsrfFilter 后添加 AfterCsrfFilter
        http.addFilterAfter(new AfterCsrfFilter(), CsrfFilter.class);
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth
                //配置 UserDetailsService 实现类，实现自定义登录校验
                .userDetailsService(userDetailService)
                //配置密码加密规则
                .passwordEncoder(passwordEncoder());
    }

    /**
     * 密码加密，必须为 @Bean ，否则报错
     *     作用：实例化密码加密规则，该规则首先会校验数据库中存储的密码是否符合其规则（经过 BCryptPasswordEncoder 加密的密码
     * 的字符串符合一定的规则）：
     *     1.若不符合，直接报错；
     *     2.若符合，则会将前端传递的密码经过 BCryptPasswordEncoder 加密，再和数据库中的密码进行比对，一样则通过
     *     所以，这里要求，我们存入进数据库的密码不能是明文，而必须是经过 BCryptPasswordEncoder 加密后，才能存入数据库
     */
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
