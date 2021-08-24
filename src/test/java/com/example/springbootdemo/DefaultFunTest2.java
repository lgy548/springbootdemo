package com.example.springbootdemo;

import com.example.springbootdemo.java8.MyDefaultFun;
import com.example.springbootdemo.java8.MyInterface;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class DefaultFunTest2 implements MyDefaultFun, MyInterface {

    @Override
    //如果实现的两个接口中同时存在相同的默认方法时，需要重新实现调用指定方法
    public String getName() {
        return MyInterface.super.getName();
    }

    @Test
    public void test1(){
        DefaultFunTest2 defaultFunTest = new DefaultFunTest2();
        System.out.println(defaultFunTest.getName());
    }
}
