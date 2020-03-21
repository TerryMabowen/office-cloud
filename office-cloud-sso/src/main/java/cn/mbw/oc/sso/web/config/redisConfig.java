package cn.mbw.oc.sso.web.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

/**
 * TODO
 *
 * @author Mabowen
 * @date 2020-03-17 20:50
 */
@Configuration
public class redisConfig {
    @Value("${spring.redis.maxTotal}")
    public Integer maxTotal;

    @Value("${spring.redis.host}")
    public String host;

    @Value("${spring.redis.port}")
    public Integer port;

    @Value("${spring.redis.password}")
    public String password;

    /**
     * redis连接池
     * @author Mabowen
     * @date 20:56 2020-03-17
     */
    @Bean
    public JedisPoolConfig jedisPoolConfig() {
        JedisPoolConfig jedisPoolConfig = new JedisPoolConfig();
        jedisPoolConfig.setMaxTotal(maxTotal);
        return jedisPoolConfig;
    }

    @Bean
    public JedisPool jedisPool() {
        JedisPool jedisPool = new JedisPool(jedisPoolConfig(), host, port, 30, password);
        return jedisPool;
    }
}
