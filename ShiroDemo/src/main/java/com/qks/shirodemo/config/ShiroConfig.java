package com.qks.shirodemo.config;

import com.qks.shirodemo.handler.MyRealm;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @ClassName ShiroConfig
 * @Description
 * @Author QKS
 * @Version v1.0
 * @Create 2022-11-11 17:00
 */
@Configuration
public class ShiroConfig {

    @Bean
    public ShiroFilterFactoryBean filterFactoryBean(@Qualifier("manager") DefaultWebSecurityManager manager) {
        ShiroFilterFactoryBean factoryBean = new ShiroFilterFactoryBean();
        factoryBean.setSecurityManager(manager);
        return factoryBean;
    }


    @Bean
    public DefaultWebSecurityManager manager(@Qualifier("myRealm") MyRealm myRealm) {
        DefaultWebSecurityManager manager = new DefaultWebSecurityManager();
        manager.setRealm(myRealm);
        return manager;
    }

    @Bean
    public MyRealm myRealm() {
        return new MyRealm();
    }
}