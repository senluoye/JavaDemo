package com.qks.httpclientdemo.controller;

import java.util.Map;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSONObject;

/**
 * @ClassName controller.TestController
 * @Description
 * @Author QKS
 * @Version v1.0
 * @Create 2022-11-15 17:13
 */
@RestController
public class TestController {
    @PostMapping("/api/test")
    public void test(@RequestParam Map<String, Object> object) {
        System.out.println(object);
    }
}
