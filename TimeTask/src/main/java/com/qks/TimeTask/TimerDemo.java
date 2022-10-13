package com.qks.TimeTask;

import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

/**
 * @ClassName Dessert
 * @Description
 * @Author QKS
 * @Version v1.0
 * @Create 2022-06-27 15:39
 */
public class TimerDemo {
    public static void main(String[] args) {
        // 示例代码：
        TimerTask task = new TimerTask() {
            public void run() {
                System.out.println("当前时间: " + new Date() + "n" +
                        "线程名称: " + Thread.currentThread().getName());
            }
        };
        System.out.println("当前时间: " + new Date() + "n" +
                "线程名称: " + Thread.currentThread().getName());
        Timer timer = new Timer("Timer");
        long delay = 1000L;
        timer.schedule(task, delay);
    }
}
