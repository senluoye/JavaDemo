package com.qks.demo.RedissionDemo.redission;

import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;

import javax.annotation.Resource;
import java.util.concurrent.TimeUnit;

/**
 * @ClassName Test
 * @Description
 * @Author QKS
 * @Version v1.0
 * @Create 2022-10-13 15:27
 */
public class Test {

    @Resource
    private RedissonClient redissonClient;

    public void lock(String lockName, Long expireTime) {
        RLock rLock = redissonClient.getLock(lockName);
        try {
            boolean isLocked = rLock.tryLock(expireTime, TimeUnit.MILLISECONDS);
            if (isLocked) {
                System.out.println("上锁");
            }
        } catch (Exception e) {
            rLock.unlock();
        }
    }
}
