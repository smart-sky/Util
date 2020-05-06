package com.example.myutil;

import lombok.Data;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import java.util.ArrayList;
import java.util.List;

@SpringBootTest
// 下面两个注解是web环境才需要
//@RunWith(SpringRunner.class)
//@WebAppConfiguration
public class MyutilApplicationTests {

    @Test
    public void testGetEntFileById(){
        Assert.assertSame("企业数量有误",500,499);
    }


}
