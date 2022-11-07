package com.qks.threaddedmo.another;

import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.TimeUnit;

/**
 * @ClassName Dessert
 * @Description SynchronousQueue 的特点是没有容量， 没有线程来取是放不进去
 * @Author QKS
 * @Version v1.0
 * @Create 2022-07-05 11:06
 */
public class SynchronousQueueDemo {
    public static void main(String[] args) throws InterruptedException {
        SynchronousQueue<Integer> queue = new SynchronousQueue<>();
        new Thread(() -> {
            try {
                System.out.println("start to put 1");
                queue.put(1);
                System.out.println("1 is putted");

                System.out.println("start to put 2");
                queue.put(2);

                System.out.println("2 is putted");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }, "t1").start();

        TimeUnit.SECONDS.sleep(1);

        new Thread(() -> {
            try {
                System.out.println("start to take first time");
                TimeUnit.SECONDS.sleep(3);
                int a = queue.take();
                System.out.println(a + " is taked");

                System.out.println("start to take second time");
                a = queue.take();

                System.out.println(a + " is taked");
                queue.take();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }, "t2").start();
    }
}
