package com.qks.aop.xml;

import com.qks.aop.xml.service.UserService;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @ClassName SpringAopXmlApp
 * @Description 基于XML实现的AOP案例
 * @Author QKS
 * @Version v1.0
 * @Create 2022-09-13 15:37
 */
public class SpringAopXmlApp {
    public static void main(String[] args) {
        ClassPathXmlApplicationContext ctx = new ClassPathXmlApplicationContext("classpath:spring-aop-xml.xml");
        UserService userService = (UserService) ctx.getBean("userService");
        System.out.println("***********************");
        userService.login(null, "123456");
        ctx.close();
    }
}
