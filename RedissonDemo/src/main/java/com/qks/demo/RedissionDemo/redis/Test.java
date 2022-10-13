package com.qks.demo.RedissionDemo.redis;

/**
 * @ClassName test
 * @Description
 * @Author QKS
 * @Version v1.0
 * @Create 2022-10-13 15:35
 */
public class Test {
    public static void main(String[] args) {
        RedisLock redisLock = new RedisLock();
        String key = redisLock.tryLock("qks", 5000L);
        System.out.println("上锁");
        redisLock.unlock("qks", key);
    }
}
