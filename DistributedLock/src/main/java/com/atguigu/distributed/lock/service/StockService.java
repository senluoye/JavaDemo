package com.atguigu.distributed.lock.service;

import com.atguigu.distributed.lock.lock.DistributedLockClient;
import com.atguigu.distributed.lock.lock.DistributedRedisLock;
import com.atguigu.distributed.lock.mapper.StockMapper;
import com.atguigu.distributed.lock.pojo.Stock;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.springframework.core.io.ClassPathResource;
import org.springframework.dao.DataAccessException;
import org.springframework.data.redis.core.RedisOperations;
import org.springframework.data.redis.core.SessionCallback;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.script.DefaultRedisScript;
import org.springframework.scripting.support.ResourceScriptSource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Collections;
import java.util.List;
import java.util.UUID;

@Service
public class StockService {

    @Resource
    private StockMapper stockMapper;

    @Resource
    private StringRedisTemplate template;

    @Resource
    private DistributedLockClient client;

    @Resource
    private RedissonClient redissonClient;

    public void checkAndLock() {
        RLock lock = redissonClient.getLock("lock");
        lock.lock();

        try {
            String stock = template.opsForValue().get("stock");
            if (stock != null) {
                int st = Integer.parseInt(stock);
                if (st > 0) {
                    template.opsForValue().set("stock", String.valueOf(--st));
                }
            }
        } finally {
            lock.unlock();
        }
    }

    public void checkAndLock4() {
        DistributedRedisLock lock = client.getRedisLock("lock");
        lock.lock();
        try {
            // 获取库存
            String stock = template.opsForValue().get("stock");
            if (stock != null) {
                int st = Integer.parseInt(stock);
                if (st > 0) {
                    template.opsForValue().set("stock", String.valueOf(--st));
                }
            }
            reen(lock);
        } finally {
            lock.unlock();
        }
    }

    public void reen(DistributedRedisLock lock) {
        lock.lock();
        System.out.println("可重入成功");
        lock.unlock();
    }

    public void checkAndLock3() {
        String uuid = UUID.randomUUID().toString();
        while (Boolean.FALSE.equals(template.opsForValue().setIfAbsent("lock", uuid))) {
            try {
                Thread.sleep(50);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        try {
            // 获取库存
            String stock = template.opsForValue().get("stock");
            if (stock != null) {
                int st = Integer.parseInt(stock);
                if (st > 0) {
                    template.opsForValue().set("stock", String.valueOf(--st));
                }
            }
        } finally {
            DefaultRedisScript<Boolean> unlockScript = new DefaultRedisScript<>();
            unlockScript.setScriptSource(new ResourceScriptSource(new ClassPathResource("lock.lua")));
            // 需要设置resultType，否则会报错
            unlockScript.setResultType(Boolean.class);
            template.execute(unlockScript, Collections.singletonList("lock"), uuid);
        }
    }


    @Transactional
    public void checkAndLock2() {
        template.execute(new SessionCallback<Object>() {
            @Override
            public Object execute(RedisOperations operations) throws DataAccessException {
                operations.watch("stock");
                Object stock = operations.opsForValue().get("stock");
                int st = 0;
                if (stock != null && (st = Integer.parseInt(stock.toString())) > 0) {
                    operations.multi();

                    // 扣减数据库库存

                    operations.opsForValue().set("stock", String.valueOf(--st));
                    List exec = operations.exec();
                    if (exec == null || exec.size() == 0) {
                        try {
                            Thread.sleep(50);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        checkAndLock();
                    }
                    return exec;
                }
                return null;
            }
        });
    }

    @Transactional
    public void checkAndLock1() {
        Stock stock = stockMapper.getCountByFOrUpdate("1001");
        if (stock != null && stock.getCount() > 0) {
            stock.setCount(stock.getCount() - 1);
            stockMapper.updateById(stock);
        }
    }
}
