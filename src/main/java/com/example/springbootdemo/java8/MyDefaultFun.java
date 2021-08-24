package com.example.springbootdemo.java8;

public interface MyDefaultFun {
    default String getName(){
        return "哈哈哈";
    }
}
