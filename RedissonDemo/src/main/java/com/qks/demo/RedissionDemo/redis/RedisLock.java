package com.qks.demo.RedissionDemo.redis;

import org.springframework.core.io.ClassPathResource;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.script.DefaultRedisScript;
import org.springframework.scripting.support.ResourceScriptSource;

import javax.annotation.Resource;
import java.util.Collections;
import java.util.UUID;

/**
 * @ClassName Simple
 * @Description 原生redis通过lua脚本实现可重入锁
 * 会导致的问题：
 * ① 业务操作时间过长，导致锁释放了业务还在执行，此时有可能出现并发问题
 * ② 存储锁的Redis节点宕机之后，使用锁的资源会出现锁死的状态
 * @Author QKS
 * @Version v1.0
 * @Create 2022-10-13 14:28
 */
public class RedisLock {

    @Resource
    private RedisTemplate<String, Long> redisTemplate;

    private final DefaultRedisScript<Long> lockScript = new DefaultRedisScript<>();

    private final DefaultRedisScript<Object> unlockScript = new DefaultRedisScript<>();

    public RedisLock() {
        lockScript.setScriptSource(new ResourceScriptSource(new ClassPathResource("lock.lua")));
        lockScript.setResultType(Long.class);
        unlockScript.setScriptSource(new ResourceScriptSource(new ClassPathResource("unlock.lua")));
    }

    /**
     * 获取锁
     *
     * @param lockName
     * @param releaseTime
     * @return
     */
    public String tryLock(String lockName, Long releaseTime) {
        String key = UUID.randomUUID().toString();

        Long result = redisTemplate.execute(
                lockScript,
                Collections.singletonList(lockName),
                key + Thread.currentThread().getId(),
                releaseTime
        );

        return result != null && result.intValue() == 1 ? key : null;
    }

    public void unlock(String lockName, String key) {
        redisTemplate.execute(
                unlockScript,
                Collections.singletonList(lockName),
                key + Thread.currentThread().getId()
        );
    }
}
