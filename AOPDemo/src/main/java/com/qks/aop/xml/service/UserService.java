package com.qks.aop.xml.service;

/**
 * @ClassName UserService
 * @Description
 * @Author QKS
 * @Version v1.0
 * @Create 2022-09-13 15:29
 */
public interface UserService {
    String login(String username, String password);

    String register(String username, String password);
}
