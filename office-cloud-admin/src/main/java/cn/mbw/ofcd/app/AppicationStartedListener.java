/**
 * 
 */
package cn.mbw.ofcd.app;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Service;


/**
 * 应用加载之后，初始化相关配置
 * @author mabowen
 * 复制自 dinghq
 */
@Service
@Slf4j
public class AppicationStartedListener implements ApplicationListener<ContextRefreshedEvent> {
	@Value("${app.logdir}")
	private String appLogDir = "/data/";

	@Value("${app.cat.enabled}")
	private Boolean catEnabled = false;

	@Value("${spring.redis.host}")
	private String redisHostName;

	@Override
	public void onApplicationEvent(ContextRefreshedEvent event) {
		
	}
}
