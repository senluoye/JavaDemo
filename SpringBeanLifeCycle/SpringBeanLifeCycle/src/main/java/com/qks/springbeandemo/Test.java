package com.qks.springbeandemo;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @ClassName Dessert
 * @Description
 * @Author QKS
 * @Version v1.0
 * @Create 2022-06-26 10:39
 */
public class Test {
    public static void main(String[] args) {

        System.out.println("现在开始初始化容器");

        try (ClassPathXmlApplicationContext factory = new ClassPathXmlApplicationContext("beans.xml")) {
            System.out.println("容器初始化成功");
            Person person = factory.getBean("person", Person.class);
            System.out.println(person);

            System.out.println("现在开始关闭容器！");
            factory.registerShutdownHook();
        }
    }
}
