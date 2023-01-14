package com.forsake.myproject.controller;

import com.forsake.myproject.entity.User;
import com.forsake.myproject.service.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;


/**
 * @ClassName HelloController
 * @Description
 * @Author QKS
 * @Version v1.0
 * @Create 2023-01-09 10:44
 */
@RestController
public class HelloController {

    @Resource
    private UserService userService;


    @Test
    public void sad() {
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        System.out.println(passwordEncoder.encode("1234"));
    }

    @RequestMapping("/hello")
    @PreAuthorize("hasAuthority('test')")
    public String hello() {
        return "hello";
    }

    @GetMapping("/student")
    public List<User> getUserList() {
        return userService.getUserList();
    }
}
