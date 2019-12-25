package cn.mbw.ofcd.config;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.nio.charset.StandardCharsets;
import java.text.SimpleDateFormat;
import java.util.List;

/**
 * @author Mabowen
 * @date 2019-12-20 15:59
 */
@Slf4j
@Configuration
public class WebMvcConfig implements WebMvcConfigurer {

    /**
     * 静态资源映射
     */
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/vue/**").addResourceLocations("classpath:/static/vue/");
        registry.addResourceHandler("/static/**").addResourceLocations("classpath:/static/");
    }

    /**
     * 消息转换器
     */
    @Override
    public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
        // 消息转换器
        converters.add(responseBodyConverter());
        converters.add(responseJacksonConverter());
    }

    /**
     * 解决跨域问题
     */
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedOrigins("*")
                .allowedMethods("GET", "HEAD", "POST","PUT", "DELETE", "OPTIONS")
                .allowedHeaders("*")
                .allowCredentials(true)
                .maxAge(3600);
    }

    @Bean
    public HttpMessageConverter<String> responseBodyConverter() {
        return new StringHttpMessageConverter(StandardCharsets.UTF_8);
    }

    /**
     * 消息转换器
     */
    @Bean
    public HttpMessageConverter<Object> responseJacksonConverter() {
        MappingJackson2HttpMessageConverter convert = new MappingJackson2HttpMessageConverter();

        ObjectMapper mapper = new ObjectMapper();
        mapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
        mapper.disable(SerializationFeature.FAIL_ON_EMPTY_BEANS);
        mapper.setDateFormat(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"));
        mapper.configure(SerializationFeature.INDENT_OUTPUT, true);
        mapper.registerModule(new JavaTimeModule());
        mapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
        convert.setObjectMapper(mapper);
        return convert;
    }

    /**
     * cat监控的过滤器
     */
//    @Bean
//    public FilterRegistrationBean<CatFilter> catFilter() {
//        CatFilter catFilter = new CatFilter();
//        FilterRegistrationBean<CatFilter> registration = new FilterRegistrationBean<>();
//        registration.setFilter(catFilter);
//        registration.addUrlPatterns("/*");
//        registration.addInitParameter("exclusions","*.js,*.gif,*.jpg,*.png,*.css,*.ico,/druid/*");
//        registration.setName("cat-filter");
//        registration.setOrder(1);
//        // 暂时隐藏本地CAT加载请求
//        Cat.disable();
//        return registration;
//    }
}
