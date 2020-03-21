package cn.mbw.oc.spy.web.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.servlet.MultipartConfigFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.util.unit.DataSize;

import javax.servlet.MultipartConfigElement;

/**
 * @author Mabowen
 * @date 2019-10-15 09:51
 */
@Configuration
public class MultipartConfig {

    @Value("${spring.servlet.multipart.max-file-size}")
    private DataSize max_file_size;

    @Value("${spring.servlet.multipart.max-request-size}")
    private DataSize max_request_size;

    @Bean
    public MultipartConfigElement multipartConfigElement() {
        MultipartConfigFactory factory = new MultipartConfigFactory();
        //文件最大
        factory.setMaxFileSize(max_file_size);
        /// 设置总上传数据总大小
        factory.setMaxRequestSize(max_request_size);
        return factory.createMultipartConfig();
    }
}
