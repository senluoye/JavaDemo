package com.qks.threaddedmo.threadpool;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @ClassName Dessert
 * @Description SingleThreadExecutor是只有一个线程的线程池，常用于需要让线程顺序执行，并且在任意时间，只能有一个任务被执行，而不能有多个线程同时执行的场景。
 * 因为阻塞队列使用的是LinkedBlockingQueue，因此和FixedThreadPool一样，maximumPoolSize，keepAliveTime都是无效的。
 * corePoolSize为1，因此最多只能创建一个线程，SingleThreadPool的工作流程大概如下：
 * <p>当前核心线程池总线程数量小于corePoolSize（1），那么创建线程并执行任务；</p>
 * <p>如果当前线程数量等于corePoolSize，那么把 任务添加到阻塞队列中；</p>
 * <p>如果线程池中的线程执行完任务，那么获取阻塞队列中的任务并执行；</p>
 *
 * 最常用的特点就是这个线程池可以保证先进先出的顺序
 *
 * @Author QKS
 * @Version v1.0
 * @Create 2022-07-04 22:42
 */
public class SingleThreadExecutorDemo {
    public static void main(String[] args) {
        ExecutorService singleThreadExecutor = Executors.newSingleThreadExecutor();

        int i = 0;
        while (i++ < 5) {
            final int index = i;
            singleThreadExecutor.execute(() ->
                    System.out.println(Thread.currentThread().getName() + "执行" + "No." + index)
            );
        }

        singleThreadExecutor.shutdown();
    }
}
