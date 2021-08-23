package com.example.springbootdemo;

import com.example.springbootdemo.java8.Student;
import org.apache.logging.log4j.util.PropertySource;
import org.assertj.core.util.Lists;
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
                .filter(t -> t.getSex() == 1)
                .filter(t -> !StringUtils.isEmpty(t.getName()))
                /**
                 * 1.collectingAndThen方法的使用-------先进行结果集的收集，然后将收集到的结果集进行下一步的处理
                 * groupingBy方法通过age分组后，mapping方法将分组后的map中的name元素提取出来放入set中，collectingAndThen方法将得到的set转换为list
                 * 结果集
                 * 23=[张三]
                 * 24=[李四]
                 * 25=[张三, 周杰伦]
                 */
                .collect(Collectors.groupingBy(Student::getAge, Collectors.collectingAndThen(
                        Collectors.mapping(Student::getName,Collectors.toSet()), ArrayList::new)))
                /**
                 * 2.与上个方法类似，将mapping替换成toCollection通用转换方法，通过Comparator.comparing比较排序转换为TreeSet
                 * 结果集
                 * 23=[Student{name='张三', sex=1, age=23}]
                 * 24=[Student{name='李四', sex=1, age=24}]
                 * 25=[Student{name='周杰伦', sex=1, age=25}, Student{name='张三', sex=1, age=25}]
                 */
              /*  .collect(Collectors.groupingBy(Student::getAge, Collectors.collectingAndThen(
                        Collectors.toCollection(() -> new TreeSet<>(Comparator.comparing(Student::getName))), ArrayList::new)))*/
                /**
                 * 3.通过两次分组 并获得第二次分组的key作为去重标志
                 * 结果集
                 * 23={张三=1}
                 * 24={李四=1}
                 * 25={张三=2, 周杰伦=1}
                 */
                /*.collect(Collectors.groupingBy(Student::getAge,
                        Collectors.groupingBy(Student::getName, Collectors.counting())))*/
                .entrySet()
                /*.forEach(System.out::println);*/
                .stream()
                .collect(Collectors.toMap(Map.Entry::getKey, e -> e.getValue().size()))
                .entrySet()
//                .forEach(System.out::println);
                .stream()
                .max(Comparator.comparingInt(Map.Entry::getValue))
                .ifPresent(System.out::println);
    }

    List<List<String>> lists =
            Lists.newArrayList(Lists.newArrayList("1"),
                    Lists.newArrayList("1","2"),
                    Lists.newArrayList("1","2","3"));
    /**
     * 找出这个list里面1出现了几次
     */
    @Test
    public void test3(){
        long count = lists.stream()
                .flatMap(List::stream)
                .filter(t -> t.equals("1"))
                .count();
        System.out.println(count);
    }
}
