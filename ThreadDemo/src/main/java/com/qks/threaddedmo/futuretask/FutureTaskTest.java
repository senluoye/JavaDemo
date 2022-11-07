package com.qks.threaddedmo.futuretask;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.FutureTask;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import lombok.extern.slf4j.Slf4j;

/**
 * @ClassName Dessert
 * @Description
 * @Author QKS
 * @Version v1.0
 * @Create 2022-07-03 16:19
 */
@Slf4j
public class FutureTaskTest {
    public static void main(String[] args) throws InterruptedException, ExecutionException {

        /**
         * Callable 或 Runable 对象表示要执行的任务，可以直接放进FutureTask里，也可以放进
         * FutureTask 表示这些任务返回的结果的封装
         * submit() 方法表示执行这些任务
         * get() 表示阻塞式获取这些任务的返回结果
         */
        long starttime = System.currentTimeMillis();
        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(2, 2, 2,
                java.util.concurrent.TimeUnit.SECONDS, new java.util.concurrent.LinkedBlockingQueue<>());

        // input1生成，需要耗费2秒
        /**
         * 如果传毒的是FutureTask对象，内部还是会再封装成另一个FutureTask对象（？
         */
        FutureTask<Integer> inputFuturetaskOne = new FutureTask<>(() -> {
            Thread.sleep(2000);
            return 3;
        });
        threadPoolExecutor.submit(inputFuturetaskOne);

        // input2生成， 需要耗费3秒
        /**
         * 如果传入的是callable对象，ThreadPoolExecutor的submit方法会生成一个FetureTask对象并执行
         */
        Future<Integer> inputFuturetaskTwo = threadPoolExecutor.submit(() -> {
            TimeUnit.SECONDS.sleep(3);
            return 5;
        });

        Integer integer2 = inputFuturetaskTwo.get();
        Integer integer1 = inputFuturetaskOne.get();
        Integer result = algorithm(integer1, integer2);
        log.info(result.toString());

        long endtime = System.currentTimeMillis();
        log.info("用时：" + String.valueOf(endtime - starttime));
    }

    /**
     * 执行的算法
     * 
     * @param input
     * @param input2
     * @return
     */
    public static int algorithm(int input, int input2) {
        return input + input2;
    }
}
