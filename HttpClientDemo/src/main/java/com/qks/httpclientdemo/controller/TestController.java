package com.qks.httpclientdemo.controller;

import com.alibaba.fastjson.JSONObject;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

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
    public void test(@RequestBody JSONObject object) {
        System.out.println(object);
    }
}
