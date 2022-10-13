package com.qks.asyncdemo.controller;

import com.qks.asyncdemo.methods.AsyncMethods;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

/**
 * @ClassName Dessert
 * @Description
 * @Author QKS
 * @Version v1.0
 * @Create 2022-07-03 15:35
 */
@RestController
@Slf4j
public class TestController {

    @Resource
    private AsyncMethods asyncMethods;

    @GetMapping("/test")
    public String test() throws ExecutionException, InterruptedException {
        log.info("test: " + Thread.currentThread().getName());

        /**
         * 下面的方法将在一秒后执行完毕
         */
        asyncMethods.asyncMethod();
        Future<String> future = asyncMethods.asyncMethodWithReturn();

        future.get();
        return "test";
    }
}
