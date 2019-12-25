package cn.mbw.ofcd.app;

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
@ComponentScan(basePackages = { "cn.mbw.ofcd" })
public class OfficeCloudAdminApplication {

    public static void main(String[] args) {
        SpringApplication.run(OfficeCloudAdminApplication.class, args);
    }

}
