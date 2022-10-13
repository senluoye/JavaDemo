package com.qks.threaddedmo.sync;

import java.util.concurrent.TimeUnit;

/**
 * @ClassName Dessert
 * @Description 锁实现 SynchronousQueue (同步/阻塞队列，队列中只允许有一个任务被执行，所以所有任务是同步被执行的)
 *              <p>
 *              锁实现的方式较为低效
 *              <p>
 *              demo: {@link com.qks.threaddedmo.another.SynchronousQueueDemo}
 * @Author QKS
 * @Version v1.0
 * @Create 2022-07-05 16:10
 */
public class NativeSynchronousQueue<E> {
    boolean putting = false;
    E item = null;

    /**
     * 当任务不为空时被唤醒，唤醒 put 线程，并提取任务
     * 
     * @return
     * @throws InterruptedException
     */
    public synchronized E take() throws InterruptedException {
        while (item == null) {
            wait();
        }
        E e = item;
        item = null;
        notifyAll();
        return e;
    }

    /**
     * put 方法在放入任务时会唤醒 take 线程，此时会阻塞，知道 take 线程成功 take 任务之后才会被唤醒
     * 
     * @param e
     * @throws InterruptedException
     */
    public synchronized void put(E e) throws InterruptedException {
        if (e == null) {
            return;
        }

        // 是否存在正在放入的任务
        while (putting) {
            wait();
        }

        putting = true;
        item = e;
        notifyAll();

        // 当前存在放入的任务，put线程等待
        while (item != null) {
            wait();
        }

        putting = false;
        notifyAll();
    }

    public static void main(String[] args) throws InterruptedException {
        final NativeSynchronousQueue<Integer> queue = new NativeSynchronousQueue<>();
        Thread putThread = new Thread(() -> {
            System.out.println("put thread start");
            try {
                queue.put(1);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            System.out.println("put thread end");
        });

        Thread takeThread = new Thread(() -> {
            System.out.println("take thread start");
            try {
                System.out.println(queue.take());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("take thread end");
        });

        putThread.start();
        TimeUnit.SECONDS.sleep(1);
        takeThread.start();
    }
}
