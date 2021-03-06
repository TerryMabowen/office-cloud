package cn.mbw.oc.spy.web.config;


import at.pollux.thymeleaf.shiro.dialect.ShiroDialect;
import cn.mbw.oc.spy.web.shiro.AdminFormAuthenticationFilter;
import cn.mbw.oc.spy.web.shiro.AdminRealm;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authz.Authorizer;
import org.apache.shiro.authz.ModularRealmAuthorizer;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.realm.Realm;
import org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.aop.framework.autoproxy.DefaultAdvisorAutoProxyCreator;

import java.util.LinkedHashMap;

/**
 * @author Mabowen
 * @date 2019-12-26 09:20
 */
@Slf4j
//@Configuration
public class ShiroConfig {
    //将自己的验证方式加入容器
//    @Bean
    public Realm adminRealm() {
        return new AdminRealm();
    }

    /**
     * shiro方言  支持shiro标签
     * 用于thymeleaf使用shiro标签
     */
//    @Bean
    public ShiroDialect shiroDialect() {
        return new ShiroDialect();
    }

//    @Bean
    public AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor(SecurityManager securityManager) {
        AuthorizationAttributeSourceAdvisor attributeSourceAdvisor = new AuthorizationAttributeSourceAdvisor();
        attributeSourceAdvisor.setSecurityManager(securityManager);
        return attributeSourceAdvisor;
    }

//    @Bean
    public DefaultAdvisorAutoProxyCreator advisorAutoProxyCreator() {
        DefaultAdvisorAutoProxyCreator advisorAutoProxyCreator = new DefaultAdvisorAutoProxyCreator();
        advisorAutoProxyCreator.setProxyTargetClass(true);
        return advisorAutoProxyCreator;
    }

//    @Bean
//    public CookieRememberMeManager cookieRememberMeManager() {
//        CookieRememberMeManager cookieRememberMeManager = new CookieRememberMeManager();
//        cookieRememberMeManager.setCookie(simpleCookie());
//        //rememberMe cookie加密的密钥 建议每个项目都不一样 默认AES算法 密钥长度(128 256 512 位)
//        cookieRememberMeManager.setCipherKey(Base64.decode("4AvVhmFLUs0KTA3Kprsdag=="));
//        return cookieRememberMeManager;
//    }

//    @Bean
//    public EhCacheManager ehCacheManager() {
//        EhCacheManager cacheManager = new EhCacheManager();
//        cacheManager.setCacheManagerConfigFile("classpath:ehcache-shiro.xml");
//        return cacheManager;
//    }

//    @Bean("shiroSessionManager")
//    public ShiroSessionManager shiroSessionManager() {
//        ShiroSessionManager sessionManager = new ShiroSessionManager();
//        Collection<SessionListener> listeners = new ArrayList<>();
//        //配置监听
//        listeners.add(shiroSessionListener());
//        sessionManager.setSessionListeners(listeners);
//        sessionManager.setSessionIdCookie(sessionIdCookie());
//        sessionManager.setSessionDAO(redisSessionDAO());
//        sessionManager.setCacheManager(ehCacheManager());
//
//        //全局会话超时时间（单位毫秒）
//        sessionManager.setGlobalSessionTimeout(60 * 1000 * 60);
//        //是否开启删除无效的session对象  默认为true
//        sessionManager.setDeleteInvalidSessions(true);
//        //是否开启定时调度器进行检测过期session 默认为true
//        sessionManager.setSessionValidationSchedulerEnabled(true);
//        //设置session失效的扫描时间, 清理用户直接关闭浏览器造成的孤立会话
//        //设置该属性 就不需要设置 ExecutorServiceSessionValidationScheduler 底层也是默认自动调用ExecutorServiceSessionValidationScheduler
//        sessionManager.setSessionValidationInterval(60 * 1000 * 10);
//        sessionManager.setSessionFactory(shiroSessionFactory());
//
//        //取消url 后面的 JSESSIONID
//        sessionManager.setSessionIdUrlRewritingEnabled(false);
//
//        return sessionManager;
//
//    }

//    @Bean
    public Authorizer authorizer() {
        // FIXME: 我也不知道怎么回事。在多个realm的情况下，不定义这个Bean会报错。这个Bean是不完整的
        ModularRealmAuthorizer authorizer = new ModularRealmAuthorizer();

        return authorizer;
    }

//    @Bean
    public SecurityManager securityManager() {
        DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
        //设置自定义realm.
        securityManager.setRealm(adminRealm());
        //配置记住我
//        securityManager.setRememberMeManager(cookieRememberMeManager());
        //配置缓存管理器
//        securityManager.setCacheManager(ehCacheManager());
        //配置session管理器
//        securityManager.setSessionManager(shiroSessionManager());
        return securityManager;
    }

//    @Bean
//    public FormAuthenticationFilter formAuthenticationFilter() {
//        FormAuthenticationFilter formAuthenticationFilter = new FormAuthenticationFilter();
//        //对应前端的checkbox的name = rememberMe
//        formAuthenticationFilter.setRememberMeParam("rememberMe");
//        return formAuthenticationFilter;
//    }

//    @Bean
//    public LifecycleBeanPostProcessor lifecycleBeanPostProcessor() {
//        return new LifecycleBeanPostProcessor();
//    }

//    @Bean
//    public SimpleCookie simpleCookie() {
//        //这个参数是cookie的名称，对应前端的checkbox的name = rememberMe
//        SimpleCookie simpleCookie = new SimpleCookie("rememberMe");
//        /*
//         * 设为true后，只能通过http访问，javascript无法访问
//         * 防止xss读取cookie
//         */
//        simpleCookie.setHttpOnly(true);
//        simpleCookie.setPath("/");
//        //<!-- 记住我cookie生效时间30天 ,单位秒;-->
//        simpleCookie.setMaxAge(2592000);
//        return simpleCookie;
//    }

//    @Bean
    public ShiroFilterFactoryBean shiroFilterFactoryBean(SecurityManager securityManager){//shiro 包
        ShiroFilterFactoryBean bean = new ShiroFilterFactoryBean();
        bean.setSecurityManager(securityManager);
        //权限验证
        /**
         * https://lists.apache.org/thread.html/189e4d001236062df286d11314fb96c818e93f9d34de245265aef6f8@%3Cdev.shiro.apache.org%3E
         * 按照官方文档，至少只需要配置2个bean就可以了。一个realm的bean，一个filterChain的bean。我们需要重写authc过滤器，
         * 本来自定义一个filter就行了，但不知道为什么没用。所以索性重写shiroFilterFactoryBean了。
         */
        bean.getFilters().put("authc", new AdminFormAuthenticationFilter());
        //当此用户是一个非认证用户,需要先登陆进行认证
        bean.setLoginUrl("/login");
        bean.setSuccessUrl("/index");
        bean.setUnauthorizedUrl("/error/403");
        LinkedHashMap<String,String> fcMap = new LinkedHashMap<>();
        // 静态资源可直接访问
        fcMap.put("/static/**", "anon");
        // 后端相关访问控制
        fcMap.put("/dev/**", "anon");
        fcMap.put("/login","anon");
        fcMap.put("/logout","logout");
        // 配置匿名访问
        //必须授权才能访问
        fcMap.put("/**", "authc");
        bean.setFilterChainDefinitionMap(fcMap);
        return bean;
    }
}
