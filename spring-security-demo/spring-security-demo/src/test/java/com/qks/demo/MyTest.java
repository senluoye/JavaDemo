package com.qks.demo;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @ClassName Dessert
 * @Description
 * @Author QKS
 * @Version v1.0
 * @Create 2022-06-21 10:23
 */
@SpringBootTest
@RunWith(SpringRunner.class)
public class MyTest {
    @Test
    public void test(){
        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

        String password = passwordEncoder.encode("123456");
        System.out.println(password);

        boolean matches = passwordEncoder.matches("123456", password);
        System.out.println(matches);
    }
}
