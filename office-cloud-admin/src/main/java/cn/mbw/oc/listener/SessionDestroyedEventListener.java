package cn.mbw.oc.listener;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationListener;
import org.springframework.security.web.session.HttpSessionDestroyedEvent;
import org.springframework.stereotype.Component;

/**
 * 监听session失效事件
 *
 * @author Mabowen
 * @date 2019-12-26 09:55
 */
@Slf4j
@Component
public class SessionDestroyedEventListener implements ApplicationListener<HttpSessionDestroyedEvent> {
    @Override
    public void onApplicationEvent(HttpSessionDestroyedEvent event) {
        log.info("失效session:{}", event.getSession().getId());
        try {
            // 移除session
        } catch (Exception e) {
            log.error(String.format("失效session:[%s]发生异常.", event.getId()), e);
        }
    }
}
