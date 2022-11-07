package com.qks.threaddedmo.threadpool;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @ClassName WorkStealingPool
 * @Description 创建一个抢占式执行的线程池（任务执行顺序不确定），此方法只有在 JDK1.8+ 的版本中才能使用
 * @Author QKS
 * @Version v1.0
 * @Create 2022-09-23 12:04
 */
@Slf4j
public class WorkStealingPoolDemo {
    public static void main(String[] args) {
        ExecutorService threadPool = Executors.newWorkStealingPool();

        for (int i = 0; i < 10; i++) {
            final int index = i;
            threadPool.execute(() -> {
                log.info(index + "被执行, 线程名: " + Thread.currentThread().getName());
            });
        }

        // 确保任务能被执行
        while (!threadPool.isTerminated()){}
    }
}
