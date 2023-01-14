package com.forsake.myproject.handler;

import com.alibaba.fastjson.JSON;
import com.forsake.myproject.entity.ResponseResult;
import com.forsake.myproject.util.WebUtils;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @ClassName AuthenticationEntryPointImpl
 * @Description
 * @Author QKS
 * @Version v1.0
 * @Create 2023-01-14 10:57
 */

@Component
public class AuthenticationEntryPointImpl implements AuthenticationEntryPoint {
    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) {
        ResponseResult result = new ResponseResult(HttpStatus.UNAUTHORIZED.value(), "认证失败请重新登录");
        String json = JSON.toJSONString(result);
        WebUtils.renderString(response, json);
    }
}

