package com.qks.threaddedmo.lock;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * ？
 */
public class lockInterruptiblyDemo {
    private Lock lock = new ReentrantLock();

    public static void main(String[] args) {
        lockInterruptiblyDemo demo = new lockInterruptiblyDemo();

        MyThread thread1 = new MyThread(demo);
        MyThread thread2 = new MyThread(demo);

        thread1.start();
        thread2.start();

        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        thread1.interrupt();
    }

    public void insert(Thread thread) throws InterruptedException {
        lock.lockInterruptibly();
        try {
            System.out.println(thread.getName() + "get lock");
            long startTime = System.currentTimeMillis();
            for (;;) {
                if (System.currentTimeMillis() - startTime >= Integer.MAX_VALUE) {
                    break;
                }
            }
        } finally {
            System.out.println(Thread.currentThread().getName() + "excute finally");
            lock.unlock();
            System.out.println(thread.getName() + "ralease lock");
        }
    }
}

class MyThread extends Thread {
    private lockInterruptiblyDemo demo = null;

    public MyThread(lockInterruptiblyDemo demo) {
        this.demo = demo;
    }

    @Override
    public void run() {
        try {
            demo.insert(Thread.currentThread());
        } catch (InterruptedException e) {
            System.out.println(Thread.currentThread().getName() + "stop");
        }
    }
}