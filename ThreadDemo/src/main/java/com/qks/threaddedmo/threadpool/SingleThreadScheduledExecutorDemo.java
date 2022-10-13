package com.qks.threaddedmo.threadpool;

import lombok.extern.slf4j.Slf4j;

import java.util.Date;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * @ClassName SingleThreadScheduledExecutorDemo
 * @Description 创建一个单线程的可以执行延迟任务的线程池
 * <p>其实就是 SingleThreadExecutor 和 ScheduleThreadPool 的结合体</p>
 * @Author QKS
 * @Version v1.0
 * @Create 2022-09-23 11:58
 */
@Slf4j
public class SingleThreadScheduledExecutorDemo {
    public static void main(String[] args) {
        ScheduledExecutorService threadPool = Executors.newSingleThreadScheduledExecutor();
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
