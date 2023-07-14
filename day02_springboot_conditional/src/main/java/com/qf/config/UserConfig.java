package com.qf.config;

import com.mysql.jdbc.Driver;
import com.qf.anno.MyConditional;
import com.qf.condition.MyConditionalClass;
import com.qf.pojo.User;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Conditional;
import org.springframework.context.annotation.Configuration;

@Configuration
public class UserConfig {

    @Bean
    //@Conditional(value = MyConditionalClass.class)
    //@MyConditional("com.mysql.jdbc.Driver")
    //@ConditionalOnClass(Driver.class)
    @ConditionalOnMissingBean(name = "createUser")
    public User createUser(){
        System.out.println("is hehehehehehe....");
        return new User();
    }
}
