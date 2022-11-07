package com.qks.TimeTask;

import lombok.SneakyThrows;

import java.util.Date;
import java.util.TimerTask;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * @ClassName Dessert
 * @Description
 * @Author QKS
 * @Version v1.0
 * @Create 2022-06-27 15:43
 */
public class ScheduledThreadPoolExecutorDemo {
    public static void main(String[] args) throws InterruptedException {
        TimerTask repeatedTask = new TimerTask() {
            @SneakyThrows
            public void run() {
                System.out.println("当前时间: " + new Date() + "n" +
                        "线程名称: " + Thread.currentThread().getName());
            }
        };
        System.out.println("当前时间: " + new Date() + "n" +
                "线程名称: " + Thread.currentThread().getName());
        ScheduledExecutorService executor = Executors.newScheduledThreadPool(3);
        long delay  = 1000L;
        long period = 1000L;
        executor.scheduleAtFixedRate(repeatedTask, delay, period, TimeUnit.MILLISECONDS);
        Thread.sleep(delay + period * 5);
        executor.shutdown();
    }
}
