package com.example.springbootdemo;

import com.example.springbootdemo.java8.MyFun;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Comparator;
import java.util.function.Consumer;

@RunWith(SpringRunner.class)
@SpringBootTest
public class Lambdatest1 {

    /*
    * 一、Lambda表达式的基础语法：Java8中引入了一个新的操作符“->”
    * 左侧：Lambda 表达式的参数列表
    * 右侧：Lambda 表达式中所需要执行的功能，即Lambda体
    *
    * 语法一：无参数，无返回值
    * () -> System.out.println("hello lambda");
    *
    * 语法二：有参数，无返回值
    *(x) -> System.out.println(x);
    *若只有一个参数可以省略（）
    * x -> System.out.println(x);
    *
    * 语法三：有两个以上参数，有返回值，并且Lambda体中多条语句
    * Comparator<Integer> comparator = (x, y) -> {
    *       System.out.println(x);
    *       System.out.println(y);
    *       return x.compareTo(y);
    *   };
    * 若 Lambda 体中只有一条语句，return 和 {} 都可以省略
    * Comparator<Integer> comparator = (x, y) ->  x.compareTo(y);
    *
    * 二、Lambda表达式需要“函数式接口”的支持
    * 函数式接口：接口中只有一个抽象方法的接口，可以使用@FunctionalInterface 修饰
    *           可以检查是否是函数式接口
    * */

    @Test
    public void test1(){
        int num = 0;//在java1.7前必须是final,1.7之后默认
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                System.out.println("hello lambda" + num);
            }
        };
        runnable.run();
        System.out.println("--------------------------------------");
        Runnable runnable1 = () -> System.out.println("hello lambda");
        runnable1.run();
    }

    @Test
    public void test2(){
        Consumer consumer = (x) -> System.out.println(x);
        consumer.accept("hello lambda");

        Consumer consumer1 = x -> System.out.println(x);
        consumer1.accept("hello lambda");
    }

    @Test
    public void test3(){
        Comparator<Integer> comparator = (x, y) -> {
            System.out.println(x);
            System.out.println(y);
            return x.compareTo(y);
        };
        System.out.println(comparator.compare(100, 10));
    }

    @Test
    public void test4(){
        Comparator<Integer> comparator = (x, y) ->  x.compareTo(y);
        System.out.println(comparator.compare(100, 10));
    }

    //对一个数进行运算
    public Integer opertation(Integer num, MyFun mf){
        return mf.getValue(num);
    }
    
    @Test
    public void test5(){
        Integer opertation = opertation(100, (x) -> x * x);
        System.out.println(opertation);
        System.out.println(opertation(100, (y) -> y+888));
    }
}
