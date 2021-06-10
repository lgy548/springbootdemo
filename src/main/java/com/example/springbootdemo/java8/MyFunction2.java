package com.example.springbootdemo.java8;

@FunctionalInterface
public interface MyFunction2<T,R> {
    public R getValue(T t1,T t2);
}
