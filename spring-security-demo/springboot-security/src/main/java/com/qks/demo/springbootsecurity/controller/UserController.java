package com.qks.demo.springbootsecurity.controller;

import com.qks.demo.springbootsecurity.dto.UserDTO;
import com.qks.demo.springbootsecurity.service.UserService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @ClassName UserController
 * @Description 多用户多角色版 Demo
 * @Author QKS
 * @Version v1.0
 * @Create 2022-11-07 15:16
 */
@RestController
public class UserController {
    @Resource
    private UserService userService;

    @GetMapping("/get-user")
    public UserDTO getUser(@RequestParam String username) {
        return userService.getUser(username);
    }
}

