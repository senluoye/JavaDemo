package com.qks.demo.springbootsecurity.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.qks.demo.springbootsecurity.entity.UserInfo;
import com.qks.demo.springbootsecurity.mapper.UserInfoMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @ClassName UserInfoService
 * @Description
 * @Author QKS
 * @Version v1.0
 * @Create 2022-11-07 11:35
 */
@Service
public class UserInfoService extends ServiceImpl<UserInfoMapper, UserInfo> {
    @Resource
    private UserInfoMapper userInfoMapper;

    @Resource
    private PasswordEncoder passwordEncoder;

    public UserInfo getUserInfo(String username) {
        return userInfoMapper.getUserInfoByUsername(username);
    }

    public int insertUser(UserInfo userInfo) {
        // 加密密码
        userInfo.setPassword(passwordEncoder.encode(userInfo.getPassword()));
        return userInfoMapper.insertUserInfo(userInfo);
    }

    public int updatePwd(String oldPwd, String newPwd) {
        // 获取当前登录用户信息(注意：没有密码的)
        // SecurityContextHolder 是一个工具类，用于保存当前程序使用人的安全上下文，默认通过ThreadLocal实现
        UserDetails principal = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String username = principal.getUsername();

        // 通过用户名获取到用户信息（获取密码）
        UserInfo userInfo = userInfoMapper.getUserInfoByUsername(username);

        // 判断输入的旧密码是正确
        if (passwordEncoder.matches(oldPwd, userInfo.getPassword())) {
            // 不要忘记加密新密码
            return userInfoMapper.updatePwd(username, passwordEncoder.encode(newPwd));
        }
        return 0;
    }
}
