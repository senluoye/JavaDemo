package com.qks.threaddedmo.threadpool;

import lombok.extern.slf4j.Slf4j;

import java.util.Date;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * @ClassName Dessert
 * @Description 创建一个可以执行延迟任务的线程池
 * @Author QKS
 * @Version v1.0
 * @Create 2022-07-06 08:55
 */
@Slf4j
public class ScheduleThreadPoolDemo {
    public static void main(String[] args) {
        ScheduledExecutorService threadPool = Executors.newScheduledThreadPool(5);
        log.info("添加任务, 时间: {}", new Date());
        threadPool.schedule(() -> {
            log.info("任务被执行, 时间: {}", new Date());
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }, 1, TimeUnit.SECONDS);

        threadPool.shutdown();
    }
}
