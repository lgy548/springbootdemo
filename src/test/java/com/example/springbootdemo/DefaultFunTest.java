package com.example.springbootdemo;

import com.example.springbootdemo.java8.MyClass;
import com.example.springbootdemo.java8.MyDefaultFun;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class DefaultFunTest extends MyClass implements MyDefaultFun {

    @Test
    public void test1(){
        DefaultFunTest defaultFunTest = new DefaultFunTest();
        //如果继承的class中和实现的interface中有相同的方法，默认使用class中的方法
        System.out.println(defaultFunTest.getName());
    }

}
