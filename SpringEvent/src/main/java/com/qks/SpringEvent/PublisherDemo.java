package com.qks.SpringEvent;

import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * @ClassName Dessert
 * @Description
 * @Author QKS
 * @Version v1.0
 * @Create 2022-06-27 09:16
 */
@Component
public class PublisherDemo {

    @Resource
    private ApplicationContext applicationContext;

    public void publishEvent(String msg) {
        applicationContext.publishEvent(new EventDemo(this, msg));
    }

}
