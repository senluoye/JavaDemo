package com.qks.threaddedmo.sync;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @ClassName SyncDemo
 * @Description 让三个线程依次执行的实现方案
 * <p>①直接让线程睡眠不同的时间</p>
 * <p>②在线程中创建实例</p>
 * <p>③通过single线程池，依次执行三个任务</p>
 * <p>④通过join方法强制同步</p>
 * @Author QKS
 * @Version v1.0
 * @Create 2022-09-20 17:29
 */
public class SyncDemo {

    @Test
    public void first() throws InterruptedException {
        Thread thread1 = new Thread(() -> {
            try {
                Thread.sleep(1000);
                System.out.println("线程一执行完毕");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        Thread thread2 = new Thread(() -> {
            try {
                Thread.sleep(500);
                System.out.println("线程二执行完毕");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        Thread thread3 = new Thread(() -> {
            try {
                Thread.sleep(100);
                System.out.println("线程三执行完毕");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        thread1.start();
        thread2.start();
        thread3.start();
        Thread.sleep(1500);
    }

    @Test
    public void two() throws InterruptedException {
        class T1 extends Thread {
            @Override
            public void run() {
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("线程一执行完毕");
            }
        }

        class T2 extends Thread {
            @Override
            public void run() {
                try {
                    Thread.sleep(200);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("线程二执行完毕");
                new T1().start();
            }
        }

        class T3 extends Thread {
            @Override
            public void run() {
                try {
                    Thread.sleep(300);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("线程三执行完毕");
                new T2().start();
            }
        }

        T3 t = new T3();
        t.start();
        Thread.sleep(1000);
    }

    @Test
    public void three() throws InterruptedException {
        Thread t1 = new Thread(() -> {
            try {
                Thread.sleep(300);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("线程一执行完毕");
        });
        Thread t2 = new Thread(() -> {
            try {
                Thread.sleep(200);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("线程二执行完毕");
        });
        Thread t3 = new Thread(() -> {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("线程三执行完毕");
        });
        ExecutorService executorService = Executors.newSingleThreadExecutor();
        executorService.submit(t1);
        executorService.submit(t2);
        executorService.submit(t3);

        executorService.shutdown();
        Thread.sleep(700);
    }

    @Test
    public void four() throws InterruptedException {
        Thread t1 = new Thread(() -> {
            try {
                Thread.sleep(300);
                System.out.println("线程一执行完毕");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        Thread t2 = new Thread(() -> {
            try {
                t1.join(); // 当前线程阻塞，等到 t1 线程执行官完毕
                Thread.sleep(200);
                System.out.println("线程二执行完毕");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        Thread t3 = new Thread(() -> {
            try {
                t2.join();
                Thread.sleep(100);
                System.out.println("线程三执行完毕");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        t3.start();
        t2.start();
        t1.start();

        Thread.sleep(700);
    }
}
