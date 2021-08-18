package com.example.springbootdemo;

import com.example.springbootdemo.java8.Employee;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TestStreamAPIAndLambda {

    /**
     * 给定一个数字列表，如何返回一个由每个数字的平方构成的列表？
     * 给定【1，2，3，4，5】，返回【1，4，9，16，25】
     */
    @Test
    public void test1(){
        List<Integer> list = Arrays.asList(1,2,3,4,5);
        list.stream()
                .map(e -> e*e)
                .forEach(System.out::println);
    }

    /**
     * 怎么用map和reduce数一数流中有多少个employees
     */
    List<Employee> employees = Arrays.asList(
            new Employee("王力",30,14000.00, Employee.Status.FREE),
            new Employee("蒲大坤",27,13000.00, Employee.Status.BUSY),
            new Employee("刘高杨",23,12000.00, Employee.Status.VOCATION),
            new Employee("高帅杰",25,11000.00, Employee.Status.FREE),
            new Employee("张成",8,0.00, Employee.Status.BUSY)
    );

    @Test
    public void test2(){
        Optional<Integer> sum = employees.stream()
                .map(e -> 1)
                .reduce(Integer::sum);

        System.out.println(sum.get());
        System.out.println(employees.size());
    }
}
