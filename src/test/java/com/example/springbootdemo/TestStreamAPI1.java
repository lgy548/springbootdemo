package com.example.springbootdemo;

import com.example.springbootdemo.java8.Employee;
import com.example.springbootdemo.java8.Student;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.IntSummaryStatistics;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Stream;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TestStreamAPI1 {
    /**
    * 一、Stream 的三个操作步骤
    * 1、创建Stream
    * 2、中间操作
    * 3、终止操作
    * */

    //创建Stream
    @Test
    public void test1(){
        //1、可以通过Colletion 系列集合提供的stream()或parallelStream()
        List<String> list = new ArrayList<>();
        Stream<String> stream = list.stream();

        //2、通过Arrays中的静态方法stream()获取数组流
        Employee[] emps = new Employee[10];
        Stream<Employee> stream1 = Arrays.stream(emps);

        //3、通过Stream 类中的静态方法 of()
        Stream<String> stream2 = Stream.of("aa", "bb", "cc");

        //4、创建无限流
        //迭代
        Stream<Integer> stream3 = Stream.iterate(0, (x) -> x + 2);
        stream3.limit(10)//中间操作
                .forEach(System.out::println);//终止操作

        //生成
        Stream.generate(() -> Math.random()).limit(5).forEach(System.out::println);
    }

}
