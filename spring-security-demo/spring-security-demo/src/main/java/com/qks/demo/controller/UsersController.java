package com.qks.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @ClassName Dessert
 * @Description
 * @Author QKS
 * @Version v1.0
 * @Create 2022-06-21 11:00
 */
@Controller
public class UsersController {
    @RequestMapping("/")
    public String showLogin(){
        return "login";
    }
    @RequestMapping("/success")
    public String success(){
        return "success";
    }
    @RequestMapping("/fail")
    public String fail(){
        return "fail";
    }
}
