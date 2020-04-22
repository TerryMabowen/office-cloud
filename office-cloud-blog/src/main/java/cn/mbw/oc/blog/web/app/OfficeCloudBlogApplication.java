package cn.mbw.oc.blog.web.app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableCaching(proxyTargetClass = true)
@SpringBootApplication
@ServletComponentScan
@ComponentScan(basePackages = { "cn.mbw.oc.blog" })
@EnableScheduling
public class OfficeCloudBlogApplication {

    public static void main(String[] args) {
        SpringApplication.run(OfficeCloudBlogApplication.class, args);
    }

}
