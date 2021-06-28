package com.example.springbootdemo;

import com.example.springbootdemo.java8.Employee;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
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

    //中间操作
    List<Employee> employees = Arrays.asList(
            new Employee("王力",30,14000.00),
            new Employee("蒲大坤",27,13000.00),
            new Employee("刘高杨",23,12000.00),
            new Employee("高帅杰",25,11000.00),
            new Employee("张成",8,0.00),
            new Employee("张成",8,0.00),
            new Employee("张成",8,0.00)
    );
    /**
     * 筛选与切片
     * filter-接收Lambda ，从流中排除某些元素
     * limit(n)-截断流，使其元素不超过给定数量
     * skip(n)-跳过元素，返回一个扔掉了前n个元素的流。若流中元素不足n个，则返回一个空流，与limit(n) 互补
     * distinct-筛选，通过流所生成元素的hashcode()和equals() 去除重复元素
     */
    //内部迭代：迭代操作由 Stream API 完成
    @Test
    public void test2(){
        //中间操作：不会执行任何操作
        Stream<Employee> employeeStream = employees.stream()
                .filter((x) -> {
                    System.out.println("执行中间操作");
                    return x.getAge() > 23;
        });
        //终止操作：一次性执行全部内容，即“惰性求值”
        employeeStream.forEach(System.out::println);
    }

    //外部迭代
    @Test
    public void test3(){
        Iterator<Employee> iterator = employees.iterator();
        while (iterator.hasNext()){
            System.out.println(iterator.next());
        }
    }

    @Test
    public void test4(){
        employees.stream()
                .filter((x) -> {
                    System.out.println("短路！！！！！！！！");//&& ||
                    return x.getSalary()>5000.00;
                })
                .limit(2)
                .forEach(System.out::println);
    }

    @Test
    public void test5(){
        employees.stream()
                .skip(2)
                .forEach(System.out::println);
    }

    @Test
    public void test6(){
        employees.stream()
                .distinct()
                .forEach(System.out::println);
    }
}
