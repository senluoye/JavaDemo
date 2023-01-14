package com.forsake.myproject.service;

import com.forsake.myproject.dao.UserMapper;
import com.forsake.myproject.entity.User;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @ClassName UserService
 * @Description
 * @Author QKS
 * @Version v1.0
 * @Create 2023-01-09 12:45
 */
@Service
public class UserService {

    @Resource
    private UserMapper userMapper;


    public List<User> getUserList() {
        return userMapper.selectList(null);
    }
}
