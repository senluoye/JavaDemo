package com.forsake.myproject.controller;

import com.forsake.myproject.entity.ResponseResult;
import com.forsake.myproject.entity.User;
import com.forsake.myproject.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * @ClassName LoginController
 * @Description
 * @Author QKS
 * @Version v1.0
 * @Create 2023-01-09 17:16
 */
@RestController
public class LoginController {

    @Autowired
    private LoginService loginService;

    @PostMapping("/user/login")
    public ResponseResult<Map<String, String>> login(@RequestBody User user) {
        return loginService.login(user);
    }

    @GetMapping("user/logout")
    public ResponseResult<Object> logout() {
        return loginService.logout();
    }
}
