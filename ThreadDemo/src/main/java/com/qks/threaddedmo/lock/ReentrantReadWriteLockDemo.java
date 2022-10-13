package com.qks.threaddedmo.lock;

import lombok.extern.slf4j.Slf4j;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * @ClassName ReentrantReadWriteLockDemo
 * @Description ReentrantReadWriteLock 是 ReadWriteLock 的实现类
 * <p>ReadWriteLock 接口主要提供读写两种锁，从而使多个线程能同时获取读锁，而写锁同一时间只能由一个线程获得</p>
 * <p>主要方法：readLock()和writeLock()</p>
 * <p>需要注意的是：</p>
 * <p>①如果有一个线程已经占用了读锁，则此时其他线程如果要申请写锁，则申请写锁的线程会一直等待释放读锁。</p>
 * <p>②如果有一个线程已经占用了写锁，则此时其他线程如果申请写锁或者读锁，则申请的线程会一直等待释放写锁。</p>
 * @Author QKS
 * @Version v1.0
 * @Create 2022-09-24 15:46
 */
@Slf4j
public class ReentrantReadWriteLockDemo {

    private final Map<String, Object> map = new HashMap<>();

    private final ReentrantReadWriteLock lock = new ReentrantReadWriteLock();

    public static void main(String[] args) {
        final ReentrantReadWriteLockDemo demo = new ReentrantReadWriteLockDemo();
        final String key = "lock";
        final Random r = new Random();

        AtomicInteger a = new AtomicInteger();
        a.getAndIncrement();

        for (int i = 0; i < 5; i++) {
            new Thread(() -> {
                for (int j = 0; j < 5; j++) {
                    log.info("{} read value = {}", Thread.currentThread().getName(), demo.get(key));
                }
            }).start();
            new Thread(() -> {
                for (int j = 0; j < 5; j++) {
                    int value = r.nextInt(1000);
                    demo.set(key, value);
                    log.info("{} write value = {}", Thread.currentThread().getName(), value);
                }
            }).start();
        }
    }

    /**
     * 使用读锁，根据 key 获取 value
     *
     * @param key
     */
    public Object get(String key) {
        Object value = null;
        lock.readLock().lock();
        try {
            Thread.sleep(50L);
            value = map.get(key);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.readLock().unlock();
        }
        return value;
    }

    /**
     * 使用写锁，向 map 中写入数据
     *
     * @param key
     * @param value
     */
    public void set(String key, Object value) {
        lock.writeLock().lock();
        try {
            Thread.sleep(50L);
            map.put(key, value);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.writeLock().unlock();
        }
    }

}

