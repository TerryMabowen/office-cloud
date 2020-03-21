package cn.mbw.oc.web.app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableCaching(proxyTargetClass = true)
@SpringBootApplication
@ServletComponentScan
@ComponentScan(basePackages = { "cn.mbw.oc" })
@EnableScheduling
public class OfficeCloudOcApplication {

    public static void main(String[] args) {
        SpringApplication.run(OfficeCloudOcApplication.class, args);
    }

}
