package com.example.springbootdemo.java8;

public interface MyInterface {
    default String getName(){
        return "呵呵呵";
    }
}
