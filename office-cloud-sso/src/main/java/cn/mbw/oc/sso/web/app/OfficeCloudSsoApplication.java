package cn.mbw.oc.sso.web.app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 * @author mabowen
 */
@EnableCaching(proxyTargetClass = true)
@SpringBootApplication
@ServletComponentScan
@ComponentScan(basePackages = { "cn.mbw.oc.sso" })
@EnableScheduling
public class OfficeCloudSsoApplication {

    public static void main(String[] args) {
        SpringApplication.run(OfficeCloudSsoApplication.class, args);
    }

}
