package com.qks.shirodemo.enrity;

import lombok.Data;

/**
 * @ClassName Account
 * @Description
 * @Author QKS
 * @Version v1.0
 * @Create 2022-11-07 16:09
 */
@Data
public class Account {
    private Integer id;
    private String username;
    private String password;
    private String perms;
    private String role;
}
