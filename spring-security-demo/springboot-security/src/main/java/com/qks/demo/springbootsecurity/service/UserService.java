package com.qks.demo.springbootsecurity.service;

import com.qks.demo.springbootsecurity.dto.UserDTO;
import com.qks.demo.springbootsecurity.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @ClassName UserService
 * @Description
 * @Author QKS
 * @Version v1.0
 * @Create 2022-11-07 15:15
 */
@Service
public class UserService {
    @Resource
    private UserMapper userMapper;

    public UserDTO getUser(String username) {
        return userMapper.selectUserByUsername(username);
    }
}

