package com.qks.aop.xml.service.Impl;

import com.qks.aop.xml.service.UserService;

/**
 * @ClassName UserserviceImpl
 * @Description
 * @Author QKS
 * @Version v1.0
 * @Create 2022-09-13 15:30
 */
public class UserServiceImpl implements UserService {
    @Override
    public String login(String username, String passowrd) {
        System.out.println("login username:" + username + ",passowrd:" + passowrd);
        if (username == null) {
            throw new NullPointerException("username is null");
        }
        return "OK";
    }

    @Override
    public String register(String username, String passowrd) {
        System.out.println("register username:" + username + ",passowrd:" + passowrd);
        return "OK";
    }
}
