package cn.mbw.oc.sso.web.config;

import com.mbw.commons.lang.helper.ApplicationContextHelper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * TODO
 *
 * @author Mabowen
 * @date 2020-03-31 13:47
 */
@Configuration
public class AppConfig {

    @Bean
    public ApplicationContextHelper applicationContextHelper() {
        return new ApplicationContextHelper();
    }
}
