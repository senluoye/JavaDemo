package com.qks.aop.annotation.service.Impl;

import com.qks.aop.annotation.service.BankService;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

/**
 * @ClassName BankServiceImpl
 * @Description
 * @Author QKS
 * @Version v1.0
 * @Create 2022-09-13 15:43
 */
@Service
public class BankServiceImpl implements BankService {

    @Override
    public boolean transfer(String from, String to, int amount) {
        if (amount < 1) {
            throw new IllegalArgumentException("transfer amount must be a positive number");
        }
        System.out.println("[" + from + "]向[" + to + "]转账金额" + amount);
        return false;
    }
}
