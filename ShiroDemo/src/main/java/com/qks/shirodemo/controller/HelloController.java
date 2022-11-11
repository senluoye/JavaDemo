package com.qks.shirodemo.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.qks.shirodemo.enrity.Account;
import com.qks.shirodemo.mapper.AccountMapper;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @ClassName HelloController
 * @Description
 * @Author QKS
 * @Version v1.0
 * @Create 2022-11-07 16:11
 */
@RestController
@RequestMapping("/")
public class HelloController {

    @Resource
    private AccountMapper accountMapper;

    @RequestMapping("/test")
    public void test(){
        Account account = accountMapper.selectOne(new QueryWrapper<Account>()
                .eq("username","user"));
        System.out.println(account);
    }
}
