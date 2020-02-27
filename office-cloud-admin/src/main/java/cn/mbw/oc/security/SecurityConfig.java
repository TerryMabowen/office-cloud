package cn.mbw.oc.security;

import cn.mbw.oc.filter.AfterCsrfFilter;
import cn.mbw.oc.filter.BeforeLoginFilter;
import cn.mbw.oc.service.user.admin.DbUserDetailServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
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
@Deprecated
//@Configuration
//@EnableWebSecurity
//@EnableGlobalMethodSecurity(securedEnabled = true, prePostEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    @Autowired
    private DbUserDetailServiceImpl userDetailService;

    @Autowired
    private SecurityAccessDeniedHandler deniedHandler;

    @Autowired
    private SecurityAuthenticationEntryPoint authenticationEntryPoint;

    /**
     * 配置验证规则
     */
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
            //禁用baisc和form认证，在AuthController中自己实现认证逻辑
            .httpBasic().disable()
            .formLogin().disable()
            .csrf().disable()
            .logout().disable()
            .authorizeRequests()
            .antMatchers("/static/**", "/css/**", "/js/**", "/font/**", "/images/**").permitAll()
            //必须有“USER”角色的才能访问
            .antMatchers("/level1/**").hasRole("VIP1")
            .antMatchers("/level2/**").hasRole("VIP2")
            .antMatchers("/level3/**").hasRole("VIP3")
            .antMatchers("/admin/**").hasAuthority("USER")
            .anyRequest().authenticated()
            .and()
            // 权限不足跳转到403页面/异常处理
            .exceptionHandling()
            .accessDeniedHandler(deniedHandler)
            .authenticationEntryPoint(authenticationEntryPoint)
            .accessDeniedPage("/error")
            .and()
            //设置登陆
//            .formLogin().loginPage("/login")
            // 设置登陆成功页
//            .defaultSuccessUrl("/index").permitAll()
//            .and()
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
    public void configure(WebSecurity web) {
        // 设置拦截忽略文件夹，可以对静态资源放行
        web.ignoring()
                .antMatchers("/vue/**", "/layui/**", "/css/**", "/js/**", "/images/**");
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//        auth
//            //配置 UserDetailsService 实现类，实现自定义登录校验
//            .userDetailsService(userDetailService)
//            //配置密码加密规则
//            .passwordEncoder(passwordEncoder());

        // TODO
        auth.inMemoryAuthentication()
                .withUser("lisi")
                .password("123456")
                .roles("VIP1","VIP2","VIP3");
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

    @Override
    @Bean
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }
}
