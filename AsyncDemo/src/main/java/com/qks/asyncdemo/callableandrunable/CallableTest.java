package com.qks.asyncdemo.callableandrunable;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;
import java.util.concurrent.TimeUnit;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class CallableTest implements Callable<String> {
    private String string;

    public CallableTest(String string) {
        this.string = string;
    }

    @Override
    public String call() throws Exception {
        TimeUnit.SECONDS.sleep(2);
        return string;
    }

    public static void main(String[] args) throws InterruptedException, ExecutionException {
        /**
         * Callable 接口会返回一个结果，这个结果的类型就是实例化的时候指定的泛型
         * 在这里返回的类型就是 FutureTask 的泛型 String
         */
        FutureTask<String> futureTask = new FutureTask<>(new CallableTest("Hello World"));
        long begin = System.currentTimeMillis();

        new Thread(futureTask).start();

        /**
         * get() 方法会等待 FutureTask 的结果，如果结果还没有准备好，那么这个方法会一直阻塞
         */
        String result = futureTask.get();
        log.info(result);

        long end = System.currentTimeMillis();
        log.info("耗时：" + (end - begin) + "ms");
    }
}
