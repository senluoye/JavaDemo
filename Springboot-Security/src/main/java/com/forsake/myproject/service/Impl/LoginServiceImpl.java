package com.forsake.myproject.service.Impl;

import com.forsake.myproject.entity.LoginUser;
import com.forsake.myproject.entity.ResponseResult;
import com.forsake.myproject.entity.User;
import com.forsake.myproject.service.LoginService;
import com.forsake.myproject.util.JwtUtil;
import com.forsake.myproject.util.RedisCache;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

/**
 * @ClassName LoginServiceImpl
 * @Description
 * @Author QKS
 * @Version v1.0
 * @Create 2023-01-09 17:17
 */
@Service
public class LoginServiceImpl implements LoginService {

    @Resource
    private AuthenticationManager authenticationManager;

    @Resource
    private RedisCache redisCache;

    @Override
    public ResponseResult<Map<String, String>> login(User user) {
        // 使用 AuthenticationManager 的 authenticate 方法进行认证
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(user.getUserName(), user.getPassword());
        // 在进行验证的时候，如果用户输入的密码错误，就会抛出 InternalAuthenticationServiceException 异常，可以在全局异常捕获中处理
        Authentication authenticate = authenticationManager.authenticate(authenticationToken);
        System.out.println("authenticate: " + authenticate);

        // 如果认证通过了，使用userid生成token
        LoginUser loginUser = (LoginUser) authenticate.getPrincipal();
        String userId = loginUser.getUser().getId().toString();
        String jwt = JwtUtil.createJWT(userId);

        // authenticate存入redis
        redisCache.setCacheObject("login:" + userId, loginUser);

        // 把token响应给前端
        HashMap<String, String> map = new HashMap<>();
        map.put("token", jwt);

        return new ResponseResult<>(200, "登陆成功", map);
    }

    @Override
    public ResponseResult<Object> logout() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        LoginUser loginUser = (LoginUser) authentication.getPrincipal();
        Long userid = loginUser.getUser().getId();
        redisCache.deleteObject("login:" + userid);
        return new ResponseResult<>(200, "退出成功");
    }
}
