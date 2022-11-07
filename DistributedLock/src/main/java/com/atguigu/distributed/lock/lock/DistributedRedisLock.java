package com.atguigu.distributed.lock.lock;

import org.jetbrains.annotations.NotNull;
import org.springframework.core.io.ClassPathResource;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.script.DefaultRedisScript;
import org.springframework.scripting.support.ResourceScriptSource;

import java.util.Collections;
import java.util.Timer;
import java.util.TimerTask;
import java.util.UUID;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;

/**
 * @ClassName DistributedRedisLock
 * @Description Redis 实现分布式锁
 * @Author QKS
 * @Version v1.0
 * @Create 2022-10-17 20:01
 */
public class DistributedRedisLock implements Lock {

    private final StringRedisTemplate redisTemplate;

    private final String lockName;

    private final String uuid;

    private long expire = 30;

    public DistributedRedisLock(StringRedisTemplate redisTemplate, String lockName) {
        this.redisTemplate = redisTemplate;
        this.lockName = lockName;
        this.uuid = UUID.randomUUID().toString();
    }

    @Override
    public void lock() {
        tryLock();
    }

    @Override
    public void lockInterruptibly() {
    }

    @Override
    public boolean tryLock() {
        try {
            return tryLock(-1L, TimeUnit.SECONDS);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return false;
    }

    /**
     * 加锁方法
     *
     * @param time
     * @param unit
     * @return
     * @throws InterruptedException
     */
    @Override
    public boolean tryLock(long time, @NotNull TimeUnit unit) throws InterruptedException {
        if (time != -1) {
            expire = unit.toSeconds(time);
        }

        DefaultRedisScript<Long> lockScript = new DefaultRedisScript<>();
        lockScript.setScriptSource(new ResourceScriptSource(new ClassPathResource("lock.lua")));
        lockScript.setResultType(Long.class);

        while (true) {
            Long ans = redisTemplate.execute(lockScript, Collections.singletonList(lockName), getId(), String.valueOf(expire));
            if (ans != null && ans == 1) {
                break;
            }

            Thread.sleep(50);
        }

        renewExpire();

        return true;
    }

    /**
     * 解锁方法
     */
    @Override
    public void unlock() {
        DefaultRedisScript<Long> lockScript = new DefaultRedisScript<>();
        lockScript.setScriptSource(new ResourceScriptSource(new ClassPathResource("unlock.lua")));
        lockScript.setResultType(Long.class);
        Long ans = redisTemplate.execute(lockScript, Collections.singletonList(lockName), getId(), String.valueOf(expire));
        System.out.println(ans);
        if (ans == null) {
            throw new IllegalMonitorStateException("this lock doesn't belong to you!");
        }
    }

    @Override
    public Condition newCondition() {
        return null;
    }

    /**
     * 给线程拼接唯一标识
     *
     * @return
     */
    String getId() {
        return uuid;
    }

    /**
     * 利用 Timer 类简单实现一个定时任务
     */
    private void renewExpire() {
        new Timer().schedule(new TimerTask() {
            @Override
            public void run() {
                System.out.println("re new");
                DefaultRedisScript<Boolean> reNewScript = new DefaultRedisScript<>();
                reNewScript.setScriptSource(new ResourceScriptSource(new ClassPathResource("renew.lua")));
                reNewScript.setResultType(Boolean.class);
                Boolean ans = redisTemplate.execute(reNewScript, Collections.singletonList(lockName), uuid, String.valueOf(expire));
                // 一旦重新设置成功，再次生成一个定时器
                if (Boolean.TRUE.equals(ans)) {
                    renewExpire();
                }
            }
        }, this.expire * 1000 / 3, this.expire * 1000 / 3);
    }
}