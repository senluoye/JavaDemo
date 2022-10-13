package com.qks.threaddedmo.semaphore;

import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

/**
 * @ClassName Dessert
 * @Description 通过信号量Semaphore实现停车场
 * <p>1、停车场容纳总停车量10。
 * <p>2、当一辆车进入停车场后，显示牌的剩余车位数响应的减1.
 * <p>3、每有一辆车驶出停车场后，显示牌的剩余车位数响应的加1。
 * <p>4、停车场剩余车位不足时，车辆只能在外面等待。
 * @Author QKS
 * @Version v1.0
 * @Create 2022-07-05 16:53
 */
public class SemaphoreDemo {
    private static Semaphore semaphore = new Semaphore(10);

    public static void main(String[] args) {
        // 模拟100辆车进入停车场
        for (int i = 0; i < 100; i++) {
            new Thread(() -> {
                try {
                    System.out.println("====" + Thread.currentThread().getName() + "来到停车场"+"====");
                    if (semaphore.availablePermits() == 0) {
                        System.out.println("车位不足，请耐心等待");
                    }
                    // 获取令牌，模拟获取车位
                    semaphore.acquire();
                    System.out.println(Thread.currentThread().getName()+"成功进入停车场");
                    // 模拟停车时间
                    TimeUnit.SECONDS.sleep(1);
                    System.out.println(Thread.currentThread().getName()+"驶出停车场");
                    // 释放令牌，模拟驶出车位
                    semaphore.release();
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }, i + "号车").start();
        }
    }
}
