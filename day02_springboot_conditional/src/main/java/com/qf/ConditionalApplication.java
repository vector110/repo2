package com.qf;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
/*
创建User这个bean
条件是环境做必须存在Driver的坐标

Driver是Driver的客户端

 */
@SpringBootApplication
public class ConditionalApplication {
    public static void main(String[] args) {
        ConfigurableApplicationContext context = SpringApplication.run(ConditionalApplication.class);
        Object user = context.getBean("createUser");
        System.out.println(user);
        System.out.println("==========================================》");
        Object bean = context.getBean("createJedis");
        System.out.println(bean);
    }

    /*@Bean
    public User createUser(){
        return new User();
    }*/
}
