//package com.qks.demo.springbootsecurity.config;
//
//import com.qks.demo.springbootsecurity.service.CustomUserDetailsService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.annotation.Bean;
//import org.springframework.http.HttpMethod;
//import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
//import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
//import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//import org.springframework.security.crypto.password.PasswordEncoder;
//
//import javax.annotation.Resource;
//
///**
// * @ClassName WebSecurityConfig
// * @Description
// * @Author QKS
// * @Version v1.0
// * @Create 2022-11-07 12:53
// */
//@EnableWebSecurity
//@EnableGlobalMethodSecurity(prePostEnabled = true) // 开启方法级安全验证
//public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
//
//    @Resource
//    private CustomUserDetailsService userDetailsService;
//
//    /**
//     * 指定加密方式
//     */
//    @Bean
//    public PasswordEncoder passwordEncoder() {
//        // 使用BCrypt加密密码
//        return new BCryptPasswordEncoder();
//    }
//
//    /**
//     * 身份认证接口
//     *
//     * @param auth 身份验证管理生成器
//     * @throws Exception
//     */
//    @Override
//    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//        // 从数据库读取的用户进行身份认证
//        auth.userDetailsService(userDetailsService)
//                .passwordEncoder(passwordEncoder());
//    }
//
//    /**
//     * 安全过滤器链配置方法
//     * <p>Security是通过很多的过滤器实现功能的</p>
//     * <p>这个方法就是把各种需要的过滤器 xxxConfigurer 连接起来</p>
//     * <p>可以通过该方法构建一个安全过滤器链</p>
//     *
//     * @param http
//     * @throws Exception
//     */
//    @Override
//    protected void configure(HttpSecurity http) throws Exception {
//        http.authorizeRequests()
//                // 允许post请求/add-user，无需认证
//                .antMatchers(HttpMethod.POST, "/add-user").permitAll()
////                .antMatchers(HttpMethod.GET, "/get-user").permitAll()
//                // 所有请求都需要验证
//                .anyRequest().authenticated()
//                // 返回 HttpSecurity 表示上一个配置器的配置已经完成
//                .and()
//                // 使用默认的登录页面
//                .formLogin().and()
//                // post请求要关闭csrf验证,不然访问报错；实际开发中开启，需要前端配合传递其他参数
//                .csrf().disable();
//    }
//}
