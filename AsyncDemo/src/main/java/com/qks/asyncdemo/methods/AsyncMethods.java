package com.qks.asyncdemo.methods;

import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.AsyncResult;
import org.springframework.stereotype.Component;

import java.util.concurrent.Future;

/**
 * @ClassName Dessert
 * @Description
 * @Author QKS
 * @Version v1.0
 * @Create 2022-07-03 15:36
 */
@Slf4j
@Component
public class AsyncMethods {

    /**
     * @Description 没有返回值的异步方法
     */
    @Async
    public void asyncMethod() {
        try {
            Thread.sleep(1000);
            log.info("asyncMethod: " + Thread.currentThread().getName());
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    /**
     * @Description 有返回值的异步方法
     * @return
     */
    @Async
    public Future<String> asyncMethodWithReturn() {
        try {
            Thread.sleep(1000);
            log.info("asyncMethodWithReturn: " + Thread.currentThread().getName());
            return new AsyncResult<>("asyncMethodWithReturn");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return null;
    }
}
