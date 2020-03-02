package cn.mbw.oc.config;

import at.pollux.thymeleaf.shiro.dialect.ShiroDialect;
import cn.mbw.oc.common.utils.SpringUtil;
import cn.mbw.oc.interceptor.SecurityInteceptor;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import lombok.extern.slf4j.Slf4j;
import nz.net.ultraq.thymeleaf.LayoutDialect;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.thymeleaf.spring5.SpringTemplateEngine;
import org.thymeleaf.spring5.templateresolver.SpringResourceTemplateResolver;
import org.thymeleaf.spring5.view.ThymeleafViewResolver;
import org.thymeleaf.templatemode.TemplateMode;
import org.thymeleaf.templateresolver.ITemplateResolver;

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
        registry.addResourceHandler("/layui/**").addResourceLocations("classpath:/static/layui/");
        registry.addResourceHandler("/bootstrap/**").addResourceLocations("classpath:/static/bootstrap/");
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

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new SecurityInteceptor());
    }

    /**
     * 视图解析器
     * @return
     */
    @Bean
    public ViewResolver viewResolver() {
        ThymeleafViewResolver resolver = new ThymeleafViewResolver();
        resolver.setTemplateEngine(templateEngine());
        resolver.setCharacterEncoding("UTF-8");
        resolver.setOrder(1);
        return resolver;
    }


    @Bean
    // 此处返回 *SpringTemplateEngine*
    // 而不是根据[文档](https://www.thymeleaf.org/doc/articles/thymeleaf3migration.html)返回
    // *TemplateEngine* ，否则无法作用。
    public SpringTemplateEngine templateEngine() {
        SpringTemplateEngine engine = new SpringTemplateEngine();
        engine.setEnableSpringELCompiler(true);
        engine.setTemplateResolver(templateResolver());

        engine.addDialect(new ShiroDialect());
        engine.addDialect(new LayoutDialect());
        return engine;
    }

    private ITemplateResolver templateResolver() {
        SpringResourceTemplateResolver resolver = new SpringResourceTemplateResolver();
        resolver.setApplicationContext(SpringUtil.getApplicationContext());
        resolver.setPrefix("classpath:/templates/");
        resolver.setSuffix(".html");
        resolver.setCacheable(false);
        resolver.setCharacterEncoding("UTF-8");
        resolver.setTemplateMode(TemplateMode.HTML);

        return resolver;
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
