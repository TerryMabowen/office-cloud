package cn.mbw.oc.listener;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationListener;
import org.springframework.security.web.session.HttpSessionCreatedEvent;
import org.springframework.stereotype.Component;

/**
 * 监听session创建对象
 *
 * @author Mabowen
 * @date 2019-12-26 09:53
 */
@Slf4j
@Component
public class HttpSessionCreatedEventListener implements ApplicationListener<HttpSessionCreatedEvent> {
    @Override
    public void onApplicationEvent(HttpSessionCreatedEvent event) {
        log.info("新建session:{}", event.getSession().getId());
        try {
            // 保存 session
        } catch (Exception e) {
            log.info(String.format("添加session:[%s]出现异常.", event.getSession().getId()), e);
        }
    }
}
