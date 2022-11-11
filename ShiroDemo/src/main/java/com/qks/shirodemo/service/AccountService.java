package com.qks.shirodemo.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.IService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.qks.shirodemo.enrity.Account;
import com.qks.shirodemo.mapper.AccountMapper;

import javax.annotation.Resource;

/**
 * @ClassName AccountService
 * @Description
 * @Author QKS
 * @Version v1.0
 * @Create 2022-11-07 16:13
 */
public class AccountService extends ServiceImpl<AccountMapper, Account> {
    @Resource
    private AccountMapper accountMapper;

    public Account findByUsername(String username) {
        return accountMapper.selectOne(new QueryWrapper<Account>().eq("username", username));
    }
}
