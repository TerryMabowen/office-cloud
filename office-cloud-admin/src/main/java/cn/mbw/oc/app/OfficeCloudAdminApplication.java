package cn.mbw.oc.app;

import cn.mbw.oc.common.helper.ApplicationContextHelper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableCaching(proxyTargetClass = true)
@EnableScheduling
@EnableAsync
@ComponentScan(basePackages = {"cn.mbw.oc"})
public class OfficeCloudAdminApplication {

    public static void main(String[] args) {
        SpringApplication.run(OfficeCloudAdminApplication.class, args);
    }

    @Bean
    public ApplicationContextHelper contextHelper() {
        return new ApplicationContextHelper();
    }

    /**
     * 注册Servlet Listener,用于发布Session的创建和销毁事件
     */
//    @Bean
//    public ServletListenerRegistrationBean httpSessionEventPublisher() {
//        ServletListenerRegistrationBean<HttpSessionEventPublisher> registration = new ServletListenerRegistrationBean<>();
//        registration.setListener(new HttpSessionEventPublisher());
//        return registration;
//    }
}
