package com.qks.demo.RedissionDemo.redission.config;

import org.redisson.Redisson;
import org.redisson.api.RedissonClient;
import org.redisson.codec.JsonJacksonCodec;
import org.redisson.config.Config;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @ClassName RedisConfig
 * @Description
 * @Author QKS
 * @Version v1.0
 * @Create 2022-10-13 15:13
 */
@Configuration
public class RedissionConfig {
    @Value("${spring.redis.host}")
    private String redisHost;

    @Bean
    public RedissonClient getRedisson() {
        Config config = new Config();
        config.useSingleServer().
                setAddress("redis://" + redisHost + ":" + 6379);
        config.setCodec(new JsonJacksonCodec());
        return Redisson.create(config);
    }
}