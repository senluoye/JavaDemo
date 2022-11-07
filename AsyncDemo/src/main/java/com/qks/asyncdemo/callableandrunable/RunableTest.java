package com.qks.asyncdemo.callableandrunable;

import java.util.concurrent.TimeUnit;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class RunableTest implements Runnable {

    private String string;

    public RunableTest(String string) {
        this.string = string;
    }

    @Override
    public void run() {
        try {
            TimeUnit.SECONDS.sleep(2);
        } catch (InterruptedException e) {
            log.info(e.getMessage());
            Thread.currentThread().interrupt();
        }
        log.info(string);
    }

    public static void main(String[] args) {
        RunableTest runableTest = new RunableTest("Hello World");
        long begin = System.currentTimeMillis();
        new Thread(runableTest).start();
        long end = System.currentTimeMillis();
        log.info("耗时：" + (end - begin) + "ms");
    }
}