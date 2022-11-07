package com.qks.threaddedmo.lock;

import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;

import java.util.List;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @ClassName LockDemo
 * @Description ReentrantLock可以通过lock方法和unlock方法获取和释放锁
 * <p>tryLock方法只会尝试一次获取锁，不会阻塞</p>
 * <p>需要注意地是：synchronized 和 ReentrantLock 都是可重入锁</p>
 * @Author QKS
 * @Version v1.0
 * @Create 2022-09-24 15:31
 */
@Slf4j
public class LockDemo {
    private final List<Integer> list = new ArrayList<>();


    /**
     * 可重入锁 ReentrantLock 是 Lock 接口的唯一实现
     * 当在方法内实例化时，使用的是它的不同实例化的对象，即每次的锁是不一样的，每次调用不需要等待上一次的锁释放
     * 当放在类中时，每次调用的都是同一个锁，所以下一次调用时需要等待上一次调用的锁释放
     */
    Lock lock = new ReentrantLock();

    public static void main(String[] args) {
        final LockDemo t = new LockDemo();
        new Thread(t::lockAndUnLock).start();
        new Thread(t::lockAndUnLock).start();

        final LockDemo t2 = new LockDemo();
        new Thread(t2::tryLock).start();
        new Thread(t2::tryLock).start();
    }

    /**
     * Lock 的基本使用
     */
    public void lockAndUnLock() {
        lock.lock();
        try {
            log.info(Thread.currentThread() + "get lock");
            for (int i = 0; i < 10; i++) {
                list.add(i);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            log.info(Thread.currentThread() + "release lock");
            lock.unlock();
        }
    }

    /**
     * tryLock的基本使用
     */
    public void tryLock() {
        // tryLock() 会尝试获取锁，返回一个布尔值
        if (lock.tryLock()) {
            try {
                log.info(Thread.currentThread() + "try get lock");
                for (int i = 0; i < 10; i++) {
                    list.add(i);
                }
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                log.info(Thread.currentThread() + "release lock");
                lock.unlock();
            }
        } else {
            log.info(Thread.currentThread() + "can't get lock");
        }
    }

}

