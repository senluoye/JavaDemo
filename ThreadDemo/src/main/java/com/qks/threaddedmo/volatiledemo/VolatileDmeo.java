package com.qks.threaddedmo.volatiledemo;

import java.util.concurrent.TimeUnit;

/**
 * @ClassName Dessert
 * @Description volatile的作用主要有两点:
 *              <p>
 *              ①防止指令重排序
 *              </p>
 *              <p>
 *              ②保证变量的内存可见性
 *              </p>
 * @Author QKS
 * @Version v1.0
 * @Create 2022-07-07 09:55
 */
public class VolatileDmeo {
    public static void main(String[] args) {
        MyThread myThread = new MyThread();
        myThread.start();

        for (;;) {
            if (myThread.isFlag()) {
                System.out.println(Thread.currentThread() + "flag = " + myThread.isFlag());
                break;
            }
        }
    }
}

class MyThread extends Thread {
    // 加上volatile可以保证变量的内存可见性，简单点理解就是主线程可以看到子线程的变量值
    private volatile boolean flag = false;

    @Override
    public void run() {
        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        flag = true;
        System.out.println(Thread.currentThread() + "flag = " + flag);
    }

    public boolean isFlag() {
        return flag;
    }

    public void setFlag(boolean flag) {
        this.flag = flag;
    }
}
