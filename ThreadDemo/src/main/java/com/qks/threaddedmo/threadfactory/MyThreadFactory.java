package com.qks.threaddedmo.threadfactory;

import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @ClassName Dessert
 * @Description 自定义线程工厂
 * <p>线程工厂可以在实例化线程池的时候传入，主要是为了管理线程。比如线程的名字等等</p>
 * @Author QKS
 * @Version v1.0
 * @Create 2022-07-04 16:19
 */

@Slf4j
public class MyThreadFactory implements ThreadFactory{

    private AtomicInteger counter;
    private final String name;
    private final List<String> states;

    public MyThreadFactory(String name) {
        this.counter = new AtomicInteger();
        this.name = name;
        states = new ArrayList<>();
    }

    @Override
    public Thread newThread(Runnable run) {
        Thread thread = new Thread(run, name + "-Thread-" + counter);
        counter.getAndIncrement();
        states.add(String.format("Create thread %d with name %s on %s\n",
                thread.getId(), thread.getName(), new Date()));

        return thread;
    }

    public String getStates() {
        StringBuilder stringBuffer = new StringBuilder();
        for (String state : this.states) {
            stringBuffer.append(state);
        }
        return stringBuffer.toString();
    }

    public static void main(String[] args) {
        MyThreadFactory myThreadFactory = new MyThreadFactory("MyThreadFactory");
        Task task = new Task();
        Thread thread;
        for (int i = 0; i < 10; i ++) {
            thread = myThreadFactory.newThread(task);
            thread.start();
        }
        System.out.println("Factory stats:");
        System.out.printf("%s\n", myThreadFactory.getStates());
    }
}
@Slf4j
class Task implements Runnable {
    @Override
    public void run() {
        try {
            System.out.println(Thread.currentThread().getName() + "-Task is running");
            TimeUnit.SECONDS.sleep(2);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
