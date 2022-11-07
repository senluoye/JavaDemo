package com.atguigu.distributed.lock.lock;

import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * @ClassName DistributedLockClient
 * @Description 一个工厂类
 * @Author QKS
 * @Version v1.0
 * @Create 2022-10-17 20:00
 */
@Component
public class DistributedLockClient {

    @Resource
    private StringRedisTemplate StringRedisTemplate;


    public DistributedLockClient() {
    }

    public DistributedRedisLock getRedisLock(String lockName) {
        return new DistributedRedisLock(StringRedisTemplate, lockName);
    }
}