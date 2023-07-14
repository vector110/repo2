package com.qf.Initializer;

import org.springframework.context.ApplicationContextInitializer;
import org.springframework.context.ConfigurableApplicationContext;

public class  MyApplicationContextInitializer implements ApplicationContextInitializer {

    public void initialize(ConfigurableApplicationContext applicationContext) {
        System.out.println("ApplicationContextInitializer,,,,,");
    }
}
