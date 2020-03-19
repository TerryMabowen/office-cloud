package cn.mbw.oc.officecloudsso.app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableCaching(proxyTargetClass = true)
@EnableScheduling
@EnableAsync
@ComponentScan(basePackages = {"cn.mbw.oc"})
public class OfficeCloudSsoApplication {

    public static void main(String[] args) {
        SpringApplication.run(OfficeCloudSsoApplication.class, args);
    }

}
