package com.forsake.myproject.service;

import com.forsake.myproject.entity.ResponseResult;
import com.forsake.myproject.entity.User;

import java.util.Map;

/**
 * @ClassName LoginService
 * @Description
 * @Author QKS
 * @Version v1.0
 * @Create 2023-01-09 17:17
 */
public interface LoginService {
    ResponseResult<Map<String, String>> login(User user);

    ResponseResult<Object> logout();
}
