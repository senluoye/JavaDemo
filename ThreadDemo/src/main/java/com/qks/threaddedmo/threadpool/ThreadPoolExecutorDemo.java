package com.qks.threaddedmo.threadpool;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @ClassName ThreadPoolExecutor
 * @Description 最原始的创建线程池的方式，但是需要记住它的 7 个参数
 * @Author QKS
 * @Version v1.0
 * @Create 2022-09-23 12:10
 */
@Slf4j
public class ThreadPoolExecutorDemo {
     public static void main(String[] args) {
          ThreadPoolExecutor threadPool = new ThreadPoolExecutor(5, 10, 100,
                  TimeUnit.SECONDS, new LinkedBlockingDeque<>(10));

          for (int i = 0; i < 10; i++) {
               final int index = i;
               threadPool.execute(() -> {
                    log.info(index + "被执行, 线程名: " + Thread.currentThread().getName());
                    try {
                         Thread.sleep(1000);
                    } catch (InterruptedException e) {
                         e.printStackTrace();
                    }
               });
          }

          threadPool.shutdown();
     }
}
