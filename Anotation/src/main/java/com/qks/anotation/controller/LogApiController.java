package com.qks.anotation.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSON;
import com.qks.anotation.annotarion.LogApi;

@RestController
public class LogApiController {

    @LogApi
    @RequestMapping("/log/{id}")
    public String test(@PathVariable("id") String id) {
        Map<String, Object> result = new HashMap<>();
        result.put("id", id);
        result.put("name", "关之琳");
        result.put("经典角色", "十三姨");

        return JSON.toJSONString(result);
    }
}
