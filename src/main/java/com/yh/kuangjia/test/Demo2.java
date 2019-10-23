package com.yh.kuangjia.test;


import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class Demo2 {

    @Value("${spring.datasource.username}")
    private String username;


    @Test
    public void test() {
        System.out.println(username);
    }
}
