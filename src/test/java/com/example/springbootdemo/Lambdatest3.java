package com.example.springbootdemo;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;

@RunWith(SpringRunner.class)
@SpringBootTest
public class Lambdatest3 {
/*
*java8 内置四大核心函数式接口
*
*Consumer<T> : 消费型接口
*  void accept(T t);
*
*Supplier<T> : 供给型接口
*  T get();
*
*Function<R,T> : 函数型接口
*  R apply(T t);
*
*Predicate<T> : 断言型接口
*  boolean test(T t);
*
* */

    //Consumer<T> : 消费型接口
    @Test
    public void test1(){
        deal(9999.99,(x) -> System.out.println("100平米的房只要"+x+"元"));
    }

    public void deal(double num, Consumer<Double> con){
        con.accept(num);
    }

    //Supplier<T> : 供给型接口
    //生产指定个数的的整数，放入集合中
    @Test
    public void test2(){
        List<Integer> numList = getNumList(10, () -> (int)(Math.random() * 100));
        numList.stream().forEach((x) -> System.out.println(x));
    }

    public List<Integer> getNumList(Integer num, Supplier<Integer> su){
        List<Integer> list = new ArrayList<>();
        for (int i = 0; i < num; i++) {
            list.add(su.get());
        }
        return list;
    }

    //Function<R,T> : 函数型接口
    //用于护理字符串
    @Test
    public void test3(){
        //去掉空格
        String newStr = strHandler("\t\t\t\t我想买房子", (x) -> x.trim());
        System.out.println(newStr);
        //截取字符串
        String newStr2 = strHandler("我要年薪百万", (x) -> x.substring(4, 6));
        System.out.println(newStr2);
    }

    public String strHandler(String str, Function<String,String> fu){
        return fu.apply(str);
    }

    //Predicate<T> : 断言型接口
    //将满足条件的字符串放到集合中去
    @Test
    public void test4(){
        List<String > list = Arrays.asList("qwe","sdasda","nbmbn","sa");
        List<String> newList = filterStr(list, (x) -> x.length() > 3);
        newList.stream().forEach(System.out::println);
    }

    public List<String > filterStr(List<String> list , Predicate<String> pre){
        List<String> strList = new ArrayList<>();
        for (String str: list) {
            if (pre.test(str)){
                strList.add(str);
            }
        }
        return strList;
    }

}
