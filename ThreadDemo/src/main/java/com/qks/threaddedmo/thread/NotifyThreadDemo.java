package com.qks.threaddedmo.thread;

import lombok.extern.slf4j.Slf4j;

/**
 * @ClassName NotifyThreadDemo
 * @Description 线程的 等待/通知 机制
 * @Author QKS
 * @Version v1.0
 * @Create 2022-09-23 18:01
 */
@Slf4j
public class NotifyThreadDemo {
    private static final Object lock = new Object();

    public static void main(String[] args) {
        Thread t1 = new Thread(new Runnable1(lock));
        Thread t2 = new Thread(new Runnable2(lock));
        Thread t3 = new Thread(new Runnable3(lock));
        t1.start();
        t2.start();
        t3.start();
    }
}

record Runnable1(Object lock) implements Runnable {

    @Override
    public void run() {
        System.out.println("run on Runnable1");
        synchronized (lock) {
            System.out.println("lock in Runnable1");
            try {
                lock.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("lock continue to run on Runnable1");
        }
    }
}

class Runnable2 implements Runnable {

    private final Object lock;

    public Runnable2(Object obj) {
        this.lock = obj;
    }

    @Override
    public void run() {
        System.out.println("run on Runnable2");
        try {
            System.out.println("sleep 3 sec");
            Thread.sleep(3 * 1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        synchronized (lock) {
            System.out.println("notify lock on Runnable2");
            lock.notify();
        }
    }
}

class Runnable3 implements Runnable {

    private final Object lock;

    public Runnable3(Object obj) {
        this.lock = obj;
    }

    @Override
    public void run() {
        System.out.println("run on Runnable3");
        synchronized (lock) {
            System.out.println("obj to wait on Runnable3");
            try {
                lock.wait(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("obj continue to run on Runnable3");
        }
    }
}