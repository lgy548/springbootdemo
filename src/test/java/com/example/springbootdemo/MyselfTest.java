package com.example.springbootdemo;

import com.example.springbootdemo.java8.Student;
import org.apache.logging.log4j.util.PropertySource;
import org.junit.Test;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashSet;
import java.util.IntSummaryStatistics;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.TreeSet;
import java.util.stream.Collectors;

public class MyselfTest {

    List<Student> students = Arrays.asList(
            new Student("张三",1,23),
            new Student("张三",1,25),
            new Student("张三",1,25),
            new Student("",1,25),
            new Student("王五",0,25),
            new Student("赵麻子",0,28),
            new Student("周杰伦",1,25),
            new Student("李四",1,24));
    @Test
    public void test1(){


        IntSummaryStatistics summaryStatistics =students.stream()
                .filter(x -> x.getSex() == 1
                ).mapToInt(x -> x.getAge()).summaryStatistics();
        System.out.println(summaryStatistics.getSum());

        int sum = students.stream()
                .filter(x -> x.getSex() == 1
                ).mapToInt(Student::getAge).sum();

        Integer reduce = students.stream()
                .filter(x -> x.getSex() == 1
                ).map(Student::getAge).reduce(0, Integer::sum);
    }

    /**
     * 要求出sex = 1的哪个年龄人数最多,另外数据可能会存在重复，你还需要使用人名去去重
     */
    @Test
    public void test2(){

        students.stream()
                .filter(e -> Objects.equals(e.getSex(), 1))
                .filter(t -> !StringUtils.isEmpty(t.getName()))
                .collect(Collectors.groupingBy(Student::getAge,
                        Collectors.groupingBy(Student::getName, Collectors.counting())))
                .entrySet()
//                .forEach(System.out::println);
                .stream()
                .collect(Collectors.toMap(Map.Entry::getKey, e -> e.getValue().size()))
                .entrySet()
//                .forEach(System.out::println);
                .stream()
                .max(Comparator.comparingInt(Map.Entry::getValue))
                .ifPresent(System.out::println);


        /*students.stream()
                .filter(t -> t.getSex() == 1)
                .filter(t -> !StringUtils.isEmpty(t.getName()))
                *//*.collect(Collectors.collectingAndThen(
                        Collectors.toCollection(() -> new TreeSet<>(Comparator.comparing(Student::getName))), ArrayList::new)
                ).stream()*//*
                .collect(Collectors.groupingBy(Student::getAge))
                .entrySet()
                .stream()
                .collect(Collectors.collectingAndThen(
                        Collectors.toCollection(() -> new TreeSet<>(Comparator.comparing(Student::getName))), ArrayList::new))*/


    }
}
