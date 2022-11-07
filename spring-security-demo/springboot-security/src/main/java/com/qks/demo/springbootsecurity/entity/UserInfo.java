package com.qks.demo.springbootsecurity.entity;

import lombok.Data;

/**
 * @ClassName UserInfo
 * @Description
 * @Author QKS
 * @Version v1.0
 * @Create 2022-11-07 11:33
 */
@Data
public class UserInfo {
    private int id;
    private String username;
    private String password;
    private String role;
}
