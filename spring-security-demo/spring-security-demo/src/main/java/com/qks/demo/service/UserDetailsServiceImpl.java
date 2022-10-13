package com.qks.demo.service;

import com.qks.demo.mapper.UsersMapper;
import com.qks.demo.pojo.Users;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @ClassName Dessert
 * @Description
 * @Author QKS
 * @Version v1.0
 * @Create 2022-06-21 10:35
 */
@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Resource
    private UsersMapper usersMapper;

    @Override
    public UserDetails loadUserByUsername(String username) {
        Users users = usersMapper.selectByUsername(username);

        if (users == null) {
            throw new UsernameNotFoundException("用户名不存在");
        }

        User user = new User(username, users.getPassword(), AuthorityUtils.commaSeparatedStringToAuthorityList("admin"));
        System.out.println(user);
        return user;
    }
}
