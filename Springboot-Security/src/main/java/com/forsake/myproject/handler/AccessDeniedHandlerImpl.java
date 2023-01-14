package com.forsake.myproject.handler;

import com.alibaba.fastjson.JSON;
import com.forsake.myproject.entity.ResponseResult;
import com.forsake.myproject.util.WebUtils;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @ClassName AccessDeniedHandlerImpl
 * @Description
 * @Author QKS
 * @Version v1.0
 * @Create 2023-01-14 10:56
 */
@Component
public class AccessDeniedHandlerImpl implements AccessDeniedHandler {
    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response, AccessDeniedException accessDeniedException) {
        ResponseResult result = new ResponseResult(HttpStatus.FORBIDDEN.value(), "权限不足");
        String json = JSON.toJSONString(result);
        WebUtils.renderString(response, json);

    }
}
