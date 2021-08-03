package com.example.springbootdemo;

import com.example.springbootdemo.java8.Student;
import org.junit.Test;

import java.util.Arrays;
import java.util.IntSummaryStatistics;
import java.util.List;

public class MyselfTest {

    @Test
    public void test7(){
        List<Student> students = Arrays.asList(
                new Student(1,23),
                new Student(0,25),
                new Student(1,24));

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
}
