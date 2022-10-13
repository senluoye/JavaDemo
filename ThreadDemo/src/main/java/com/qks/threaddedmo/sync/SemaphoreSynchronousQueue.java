package com.qks.threaddedmo.sync;

import java.util.concurrent.Semaphore;

/**
 * @ClassName Dessert
 * @Description 信号量实现同步队列 SynchronousQueue
 * @Author QKS
 * @Version v1.0
 * @Create 2022-07-05 16:44
 */
public class SemaphoreSynchronousQueue<E> {
    E item;
    Semaphore sync = new Semaphore(0);
    Semaphore send = new Semaphore(1);
    Semaphore receive = new Semaphore(1);

    public E take() throws InterruptedException {
        receive.acquire();
        E x = item;
        sync.release();
        send.release();
        return x;
    }

    public void put(E e) throws InterruptedException {
        send.acquire();
        item = e;
        receive.release();
        sync.acquire();
    }

    public static void main(String[] args) throws InterruptedException {
        final SemaphoreSynchronousQueue<String> queue = new SemaphoreSynchronousQueue<>();
        Thread putThread = new Thread(() -> {
            System.out.println("put thread start");
            try {
                queue.put("1");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("put thread end");
        });

        Thread takeThread = new Thread(() -> {
            System.out.println("take thread start");
            try {
                System.out.println("take from putThread: " + queue.take());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("take thread end");
        });

        putThread.start();
        Thread.sleep(1000);
        takeThread.start();
    }
}
