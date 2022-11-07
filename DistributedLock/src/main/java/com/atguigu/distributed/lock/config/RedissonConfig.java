package com.atguigu.distributed.lock.config;

import org.redisson.Redisson;
import org.redisson.api.RedissonClient;
import org.redisson.config.Config;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @ClassName RedissonConfig
 * @Description
 * @Author QKS
 * @Version v1.0
 * @Create 2022-10-18 19:31
 */
@Configuration
public class RedissonConfig {
    @Bean
    public RedissonClient redissonClient() {
        Config config = new Config();
        config.useSingleServer()
                .setAddress("redis://127.0.0.1:6379")
                .setDatabase(0) // 设置数据库，默认是0
                //.setUsername("") // 用户名和密码
                //.setPassword("")
                .setConnectionMinimumIdleSize(10) // 连接池最小空闲连接数
                .setConnectionPoolSize(50) // 连接池最大线程数
                .setIdleConnectionTimeout(60) // 空闲连接最大存活时间，单位毫秒
                .setConnectTimeout(1000) // 客户端程序获取Redis连接的超时时间
                .setTimeout(1000) // 响应超时时间
        ;
        return Redisson.create(config);
    }
}
