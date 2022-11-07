package com.qks.aop.annotation;

import com.qks.aop.annotation.service.BankService;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @ClassName SpringAopAnnotationApp
 * @Description
 * @Author QKS
 * @Version v1.0
 * @Create 2022-09-13 15:48
 */
public class SpringAopAnnotationApp {

    public static void main(String[] args) {
        ClassPathXmlApplicationContext ctx = new ClassPathXmlApplicationContext("classpath:spring-aop-annotation.xml");
        BankService bankService = ctx.getBean(BankService.class);
        bankService.transfer("jordan", "kobe", 2000);
        System.out.println("*********************");
        bankService.transfer("jordan", "kobe", 0);
        ctx.close();
    }

}
