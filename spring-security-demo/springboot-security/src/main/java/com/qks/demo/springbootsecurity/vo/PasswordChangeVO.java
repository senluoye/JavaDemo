package com.qks.demo.springbootsecurity.vo;

import lombok.Data;

/**
 * @ClassName PasswordChangeVO
 * @Description
 * @Author QKS
 * @Version v1.0
 * @Create 2022-11-07 14:52
 */
@Data
public class PasswordChangeVO {
    private String oldPassword;
    private String newPassword;
}
