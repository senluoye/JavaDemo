package com.example.springactuatordemo.controller;

import com.example.springactuatordemo.service.UserService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/user")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @RequestMapping(value = "", method = RequestMethod.GET)
    private String getHomepage(HttpServletRequest httpServletRequest) {
        return userService.getHomepage(httpServletRequest.getHeader("token"));
    }
}
