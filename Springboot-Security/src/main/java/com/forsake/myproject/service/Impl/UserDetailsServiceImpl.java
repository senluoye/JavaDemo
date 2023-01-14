package com.forsake.myproject.service.Impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.forsake.myproject.dao.UserMapper;
import com.forsake.myproject.entity.LoginUser;
import com.forsake.myproject.entity.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

/**
 * @ClassName UserDetailsServiceImpl
 * @Description
 * @Author QKS
 * @Version v1.0
 * @Create 2023-01-09 15:33
 */
@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Resource
    private UserMapper userMapper;

    /**
     * 查询用户信息
     *
     * @param username
     * @return
     * @throws UsernameNotFoundException
     */
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        //根据用户名查询用户信息
        LambdaQueryWrapper<User> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(User::getUserName, username);
        User user = userMapper.selectOne(wrapper);

        //如果查询不到数据就通过抛出异常来给出提示
        if (Objects.isNull(user)) {
            // 这个异常会被过滤链上的异常捕获其捕获到，当然也可以自定义全局异常处理
            throw new RuntimeException("用户名或密码错误");
        }

        //TODO 根据用户查询权限信息 添加到LoginUser中
        List<String> permissions = Arrays.asList("test", "admin");

        //封装成UserDetails对象返回
        return new LoginUser(user, permissions);
    }
}
