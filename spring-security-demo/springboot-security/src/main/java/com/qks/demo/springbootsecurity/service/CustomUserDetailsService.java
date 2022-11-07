package com.qks.demo.springbootsecurity.service;

import com.qks.demo.springbootsecurity.entity.UserInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName CustomUserDetailsService
 * @Description Spring Security 基于数据库验证
 * <p>要从数据库读取用户信息进行身份认证，需要新建类实现 UserDetailService 接口重写 loadUserByUsername 方法</p>
 * @Author QKS
 * @Version v1.0
 * @Create 2022-11-07 12:39
 */
@Component
public class CustomUserDetailsService implements UserDetailsService {
    @Resource
    private UserInfoService userInfoService;

    /**
     * 需新建配置类注册一个指定的加密方式Bean，或在下一步Security配置类中注册指定
     */
    @Resource
    private PasswordEncoder passwordEncoder;

    /**
     * 直接加载用户的方式——通过username
     * <p>这个方法主要用于验证用户的存在，存在就返回该用户的信息，不存在就报错提示</p>
     *
     * @param username
     * @return
     * @throws UsernameNotFoundException
     */
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        // 通过用户名从数据库获取用户信息
        UserInfo userInfo = userInfoService.getUserInfo(username);
        if (userInfo == null) {
            throw new UsernameNotFoundException("用户不存在");
        }

        // 得到用户角色
        String role = userInfo.getRole();

        // 角色集合
        List<GrantedAuthority> authorities = new ArrayList<>();
        // 角色必须以`ROLE_`开头，数据库中没有，则在这里加
        authorities.add(new SimpleGrantedAuthority("ROLE_" + role));

        return new User(
                userInfo.getUsername(),
                // 因为数据库是明文，所以这里需加密密码
//                passwordEncoder.encode(userInfo.getPassword()),
                // 如果在业务中写了对数据库加密的相关配置，那么这里就直接拿就可以了
                userInfo.getPassword(),
                authorities
        );
    }
}

