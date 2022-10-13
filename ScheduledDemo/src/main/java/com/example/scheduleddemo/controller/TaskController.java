package com.example.scheduleddemo.controller;

import com.example.scheduleddemo.task.Task;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @ClassName Dessert
 * @Description
 * @Author QKS
 * @Version v1.0
 * @Create 2022-07-07 08:58
 */
@RestController
public class TaskController {

    @Resource
    private Task task;

    @RequestMapping("/task")
    public String task() {
        task.excute();
        return "task excute";
    }
}
