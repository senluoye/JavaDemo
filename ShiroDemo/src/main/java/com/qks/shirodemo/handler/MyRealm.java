package com.qks.shirodemo.handler;

import com.qks.shirodemo.enrity.Account;
import com.qks.shirodemo.service.AccountService;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;

import javax.annotation.Resource;

/**
 * @ClassName MyRealm
 * @Description
 * @Author QKS
 * @Version v1.0
 * @Create 2022-11-11 16:57
 */
public class MyRealm extends AuthorizingRealm {

    @Resource
    private AccountService accountService;

    /**
     * 授权
     *
     * @param principalCollection
     * @return
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        return null;
    }

    /**
     * 认证
     *
     * @param authenticationToken
     * @return
     * @throws AuthenticationException
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        UsernamePasswordToken token = (UsernamePasswordToken) authenticationToken;
        Account account = accountService.findByUsername(token.getUsername());
        if (account != null) {
            return new SimpleAuthenticationInfo(account, account.getPassword(), getName());
        }
        return null;
    }
}
