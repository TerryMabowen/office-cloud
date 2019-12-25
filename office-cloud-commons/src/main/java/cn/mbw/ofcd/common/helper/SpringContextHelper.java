package cn.mbw.ofcd.common.helper;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

/**
 * @author Mabowen
 * @date 2019-12-25 13:36
 */
public class SpringContextHelper implements ApplicationContextAware {
    private static ApplicationContext context = null;

    public SpringContextHelper() {
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        SpringContextHelper.context = applicationContext;
    }

    /**
     * @return the context
     */
    public static ApplicationContext getContext() {
        return context;
    }
}
