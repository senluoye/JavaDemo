package com.qks.demo.springbootsecurity.controller;

import com.qks.demo.springbootsecurity.entity.UserInfo;
import com.qks.demo.springbootsecurity.service.UserInfoService;
import com.qks.demo.springbootsecurity.vo.PasswordChangeVO;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.Map;

/**
 * @ClassName UserInfoController
 * @Description
 * @Author QKS
 * @Version v1.0
 * @Create 2022-11-07 11:35
 */
@RestController
@RequestMapping("/hello")
public class HelloController {
    @Resource
    private UserInfoService userInfoService;

    @GetMapping("/get-user")
    public UserInfo getUser(@RequestParam String username) {
        return userInfoService.getUserInfo(username);
    }


    @PreAuthorize("hasAnyRole('user')") // 只能user角色才能访问该方法
    @GetMapping("/user")
    public String user() {
        return "user角色访问";
    }

    @PreAuthorize("hasAnyRole('admin')") // 只能admin角色才能访问该方法
    @GetMapping("/admin")
    public String admin() {
        return "admin角色访问";
    }

    /**
     * 模拟注册用户
     *
     * @param userInfo
     * @return
     */
    @PostMapping("/add-user")
    public int addUser(@RequestBody UserInfo userInfo) {
        return userInfoService.insertUser(userInfo);
    }

    @PutMapping("/update")
    public int updatePwd(@RequestBody PasswordChangeVO passwordChangeVO) {
        return userInfoService.updatePwd(passwordChangeVO.getOldPassword(), passwordChangeVO.getNewPassword());
    }
}

