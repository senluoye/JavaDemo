package com.qks.threaddedmo.threadpool;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

/**
 * @Description FixedThreadPool，也就是可重用固定线程数的线程池
 *              它的基本执行过程如下
 *              1， 如果当前运行的线程数少于corePoolSize， 会立刻创建新线程执行任务。
 *              2，当线程数到达corePoolSize后，将任务加入到LinkedBlockingQueue中。
 *              3，当线程执行完任务后，会循环从LinkedBlockingQueue中获取任务来执行。
 *
 *              FixedThreadPool使用了LinkedBlockingQueue，
 *              也就是无界队列（队列最大可容纳Integer.MAX_VALUE）， 因此会造成以下影响：
 *              a: 线程池线程数到达corePoolSize后，任务会被存放在LinkedBlockingQueue中
 *              b:
 *              因为无界队列，运行中(未调用shutdown()或者shutdownNow()方法)的不会拒绝任务（队列无界，可以放入"无限"任务）
 *
 * @author 15998
 */
@Slf4j
public class FixedThreadPoolDemo {
    public static void main(String[] args) throws InterruptedException {
        ThreadPoolExecutor threadPoolExecutor = (ThreadPoolExecutor) Executors.newFixedThreadPool(4);
        for (int i = 0; i < 10; i++) {
            MyTask myTask = new MyTask("Task " + i);
            if (i == 2) {
                TimeUnit.SECONDS.sleep(2);
            }
            int activeCount = threadPoolExecutor.getActiveCount();
            long taskCount = threadPoolExecutor.getTaskCount();
            BlockingQueue<Runnable> blockingQueue = threadPoolExecutor.getQueue();
            System.out.printf("A new task has been added %s, activeCount=%d, taskCount=%d, queue=%d\n",
                    myTask.getName(), activeCount, taskCount, blockingQueue.size());
            threadPoolExecutor.execute(myTask);
        }
    }
}

@Slf4j
@Data
@AllArgsConstructor
class MyTask implements Runnable {
    private String name;

    @Override
    public void run() {
        try {
            System.out.println("Doing a task during : " + name + ", threadId=" + Thread.currentThread().getId());
            TimeUnit.SECONDS.sleep(1);
            System.out.println("done a task during : " + name + ", threadId=" + Thread.currentThread().getId());
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}