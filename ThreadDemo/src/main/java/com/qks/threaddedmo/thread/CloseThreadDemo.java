package com.qks.threaddedmo.thread;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

/**
 * @ClassName Demo
 * @Description 优雅的关闭线程
 * @Author QKS
 * @Version v1.0
 * @Create 2022-09-23 10:26
 */
@Slf4j
public class CloseThreadDemo {

    /**
     * 线程结束有两种方法：①线程任务执行完成自然结束；②线程在执行任务的过程中发生异常
     * 优雅地结束线程，就是将其包装到一个类里，通过标志调用线程本身的方法结束线程
     */
    @Test
    public void test1() {

        class SystemMonitor {
            private Thread thread;

            /**
             * 线程结束的标志位
             * 主要是为了防止出现其他异常（例如自定义异常）时无法成功设置线程的中断状态
             * 所以就将判断条件 !Thread.currentThread().isInterrupted() 改为 !stop
             */
            private volatile boolean stop = false;

            public void start() {
                thread = new Thread(() -> {
                   while (!stop) {
                       log.debug("正在监控系统...");
                       try {
                           // 模拟执行其他任务
                           Thread.sleep(3 * 1000);
                           log.debug("任务执行 3 秒");
                           log.debug("监控的系统正常");
                       } catch (InterruptedException e) {
                           /**
                            * 需要重新设置线程的中断状态
                            * thread.interrupt() 方法让处在休眠状态的语句 Thread.sleep(3 * 1000L) 抛出异常，同时被捕获
                            * 此时 JVM 的异常处理会清除线程的中断状态，导致任务一直在执行
                            */
                           Thread.currentThread().interrupt();
                           log.error("任务执行被中断...");
                       }
                   }
                });
                thread.start();
            }

            public void stop() {
                stop = true;
                thread.interrupt();
            }
        }

        SystemMonitor sm = new SystemMonitor();
        sm.start();
        try {
            Thread.sleep(10 * 1000L);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        log.debug("监控任务启动 10 秒后，停止...");
        sm.stop();
    }

}
