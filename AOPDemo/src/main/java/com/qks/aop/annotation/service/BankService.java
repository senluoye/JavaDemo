package com.qks.aop.annotation.service;

/**
 * @ClassName BankService
 * @Description
 * @Author QKS
 * @Version v1.0
 * @Create 2022-09-13 15:42
 */
public interface BankService {
    boolean transfer(String from, String to, int mount);
}
