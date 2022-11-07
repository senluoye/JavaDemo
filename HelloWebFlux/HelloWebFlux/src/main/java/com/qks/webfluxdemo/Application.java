package com.qks.webfluxdemo;

import com.qks.webfluxdemo.client.GreetingWebClient;

import lombok.extern.slf4j.Slf4j;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @ClassName Dessert
 * @Description
 * @Author QKS
 * @Version v1.0
 * @Create 2022-06-23 13:34
 */
@SpringBootApplication
@Slf4j
public class Application {
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
        GreetingWebClient greetingWebClient = new GreetingWebClient();
        log.info(greetingWebClient.getResult());
    }
}
