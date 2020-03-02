package cn.mbw.oc.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;

/**
 * @author Mabowen
 * @date 2020/02/27 20:56
 */
@Deprecated
//@EnableWebSecurity
//@Configuration
public class SecurityConfig2 {
//public class SecurityConfig2 extends WebSecurityConfigurerAdapter{

////    @Bean
//    @Override
//    public AuthenticationManager authenticationManagerBean() throws Exception {
//        return super.authenticationManagerBean();
//    }
//
//    @Override
//    protected void configure(HttpSecurity http) throws Exception {
//        http.authorizeRequests()
//                .antMatchers("/css/**", "/js/**", "/images/**", "/font/**", "/index").permitAll()
//                .antMatchers("/users/**").hasRole("ADMIN")
//                .and()
//                .formLogin()
//                .loginPage("/login").failureUrl("/login_error");
//    }
//
//    @Autowired
//    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception{
//        auth.inMemoryAuthentication()
//                .withUser("lisi")
//                .password("123456")
//                .roles("ADMIN");
//    }

}
