package com.forsake.myproject.config.filter;

import com.forsake.myproject.entity.LoginUser;
import com.forsake.myproject.excaption.ServiceException;
import com.forsake.myproject.util.JwtUtil;
import com.forsake.myproject.util.RedisCache;
import io.jsonwebtoken.Claims;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Objects;

/**
 * @ClassName JwtAuthenticationTokenFilter
 * @Description
 * @Author QKS
 * @Version v1.0
 * @Create 2023-01-10 15:11
 */
@Component
public class JwtAuthenticationTokenFilter extends OncePerRequestFilter {

    @Autowired
    private RedisCache redisCache;

    /**
     * 处理链里的异常没有办法被全局异常捕获到
     *
     * @param request
     * @param response
     * @param filterChain
     * @throws ServletException
     * @throws IOException
     * @throws ServiceException
     */
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException, ServiceException {
        // 获取token
        String token = request.getHeader("token");
        System.out.println("token: " + token);
        if (!StringUtils.hasText(token)) {
            // 放行，后面的过滤器检查，没有认证的话就会抛出错误，从而进入到登录界面
            filterChain.doFilter(request, response);
            // 链路执行完之后要 return，避免执行下面的代码
            return;
        }

        //解析token
        String userid;
        try {
            Claims claims = JwtUtil.parseJWT(token);
            userid = claims.getSubject();
        } catch (Exception e) {
            e.printStackTrace();
            throw new ServiceException("token非法");
        }

        //从redis中获取用户信息
        String redisKey = "login:" + userid;
        LoginUser loginUser = redisCache.getCacheObject(redisKey);
        if (Objects.isNull(loginUser)) {
            throw new ServiceException("用户未登录");
        }

        //存入SecurityContextHolder
        //TODO 获取权限信息封装到Authentication中
        UsernamePasswordAuthenticationToken authenticationToken =
                new UsernamePasswordAuthenticationToken(loginUser, null, loginUser.getAuthorities());
        SecurityContextHolder.getContext().setAuthentication(authenticationToken);

        //放行
        filterChain.doFilter(request, response);
    }
}