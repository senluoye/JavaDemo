package com.forsake.myproject.config;

import com.forsake.myproject.handler.FastJsonRedisSerializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.StringRedisSerializer;

/**
 * @ClassName RedisConfig
 * @Description Redis 配置类
 * @Author QKS
 * @Version v1.0
 * @Create 2023-01-09 11:18
 */
@Configuration
public class RedisConfig {

    @Bean
    @SuppressWarnings(value = {"unchecked", "rawtypes"})
    public RedisTemplate<Object, Object> redisTemplate(RedisConnectionFactory connectionFactory) {
        RedisTemplate<Object, Object> template = new RedisTemplate<>();
        template.setConnectionFactory(connectionFactory);

        FastJsonRedisSerializer serializer = new FastJsonRedisSerializer(Object.class);

        // 使用 StringRedisSerializer 来序列化和反序列化 redis 的 key 值
        template.setKeySerializer(new StringRedisSerializer());
        // 使用自定义的 FastJsonRedisSerializer 来序列化和反序列化 redis 的 value 值
        template.setValueSerializer(serializer);

        // Hash 的 key 也采用 StringRedisSerializer 的序列化方式
        template.setHashKeySerializer(new StringRedisSerializer());
        template.setHashValueSerializer(serializer);

        template.afterPropertiesSet();
        return template;
    }
}