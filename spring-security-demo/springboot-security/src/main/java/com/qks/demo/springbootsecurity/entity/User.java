package com.qks.demo.springbootsecurity.entity;

import lombok.Data;

/**
 * @ClassName User
 * @Description
 * @Author QKS
 * @Version v1.0
 * @Create 2022-11-07 15:08
 */
@Data
public class User {
    private Integer uid;
    private String username;
    private String password;
}

