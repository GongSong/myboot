package com.yh.kuangjia.test;

import com.yh.kuangjia.KuangjiaApplication;
import javafx.application.Application;
import org.springframework.boot.SpringApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.core.env.Environment;

public class Demo {
    public static void main(String[] args) {
        ConfigurableApplicationContext context = SpringApplication.run(KuangjiaApplication.class, args);
        Environment environment = context.getBean(Environment.class);
        String property = environment.getProperty("spring.datasource.username");
        System.out.println(property);
    }
}
