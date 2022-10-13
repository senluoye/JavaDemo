package com.qks.demo.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @ClassName Dessert
 * @Description
 * @Author QKS
 * @Version v1.0
 * @Create 2022-06-21 09:52
 */
@RestController
public class test {
    @RequestMapping("/test")
    public String test(){
        return "test";
    }
}
