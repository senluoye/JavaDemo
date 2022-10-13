package com.qks.threaddedmo.threadpool;

import java.text.SimpleDateFormat;
import java.util.concurrent.Executors;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @ClassName Dessert
 * @Description 会根据任务数量创建相对应的线程数，不过CachedThreadPool的核心线程数默认为0。
 *  所以可想而知，这些创建出来的线程对应的都是最大线程数，这些线程会被缓存以试图能被重复使用，
 *  不过默认60秒没使用的话，就会被回收，所以这个类型的线程适合用于在短时间内处理大量任务。
 *
 * <p><br/>首先执行SynchronizedQueue.offer(  )把任务提交给阻塞队列，如果这时候正好在线程池中有空闲的线程执行SynchronizedQueue.poll( )，那么offer操作和poll操作配对，线程执行任务；
 * 如果执行SynchronizedQueue.offer(  )把任务提交给阻塞队列时maximumPoolSize=0.或者没有空闲线程来执行SynchronizedQueue.poll( )，那么步骤1失败，那么创建一个新线程来执行任务；
 * 如果当前线程执行完任务则循环从阻塞队列中获取任务，如果当前队列中没有提交（offer）任务，那么线程等待keepAliveTime时间，在CacheThreadPool中为60秒，在keepAliveTime时间内如果有任务提交则获取并执行任务，如果没有则销毁线程，因此最后如果一直没有任务提交了，线程池中的线程数量最终为0。
 * @Author QKS
 * @Version v1.0
 * @Create 2022-07-05 20:37
 */
public class CachedPoolExecutorDemo {
    private static final SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    private final AtomicInteger atomicInteger = new AtomicInteger(0);

    public static void main(String[] args) {
        ExecutorService executorService = Executors.newCachedThreadPool();

        // 可以看到这6个任务是并发执行的，说明每个任务都有对应的一个线程去执行
        for (int i = 0; i < 6; i++) {
            executorService.execute(() -> {
                try {
                    System.out.println("线程名称：" + Thread.currentThread().getName()
                            + "，线程ID：" + Thread.currentThread().getId()
                            + "，当前时间：" + simpleDateFormat.format(System.currentTimeMillis()));
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            });
        }

        System.out.println("当前时间:" + simpleDateFormat.format(System.currentTimeMillis()));

        try {
            // 通知线程池结束信号
            executorService.shutdown();

            // 如果等待了 5 秒还没有结束，则强制结束所有线程
             if (!executorService.awaitTermination(2, TimeUnit.SECONDS)) {
                 executorService.shutdownNow();
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
            executorService.shutdownNow();
        }
    }
}
