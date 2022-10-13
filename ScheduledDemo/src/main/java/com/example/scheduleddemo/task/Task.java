package com.example.scheduleddemo.task;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;

/**
 * @ClassName Dessert
 * @Description
 * @Author QKS
 * @Version v1.0
 * @Create 2022-07-07 08:55
 */
@Component
public class Task {

    /**
     * @Description 启动应用后每过五秒执行一次，假如此时访问该方法，则立即执行一次，同时重新开始计时
     */
    @Scheduled(cron = "0/5 * * * * ?")
    public void excute() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        System.out.println("task excute: " + sdf.format(System.currentTimeMillis()));
    }
}
