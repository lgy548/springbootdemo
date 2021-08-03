package com.example.springbootdemo;

import com.example.springbootdemo.java8.Employee;
import com.example.springbootdemo.java8.Employee.Status;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;
import java.util.Comparator;
import java.util.DoubleSummaryStatistics;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TestStreamAPI3 {
    //终止操作
    List<Employee> employees = Arrays.asList(
            new Employee("王力",30,14000.00, Status.FREE),
            new Employee("蒲大坤",27,13000.00, Status.BUSY),
            new Employee("刘高杨",23,12000.00, Status.VOCATION),
            new Employee("高帅杰",25,11000.00, Status.FREE),
            new Employee("张成",8,0.00, Employee.Status.BUSY)
    );
    /**
     * 查找与匹配
     * allMatch-检查是否匹配到所有元素
     * anyMatch-检查是否至少匹配到一个元素
     * noneMatch-检查是否没有匹配到所有元素
     * findFirst-返回第一个元素
     * findAny-返回当前流的任意元素
     * count-返回流中元素总个数
     * max-返回流中最大值
     * min-返回流中最小值
     */
    @Test
    public void test1(){
        boolean b1 = employees.stream()
                .allMatch((x) -> Employee.Status.BUSY.equals(x.getStatus()));
        System.out.println(b1);

        boolean b2 = employees.stream()
                .anyMatch((x) -> Employee.Status.BUSY.equals(x.getStatus()));
        System.out.println(b2);

        boolean b3 = employees.stream()
                .noneMatch((x) -> Employee.Status.BUSY.equals(x.getStatus()));
        System.out.println(b3);

        Optional<Employee> first = employees.stream()
                .sorted((e1, e2) -> -Double.compare(e1.getSalary(), e2.getSalary()))
                .findFirst();
        System.out.println(first.get());

        Optional<Employee> any = employees.parallelStream()
                .filter((e) -> Employee.Status.FREE.equals(e.getStatus()))
                .findAny();
        System.out.println(any.get());

        long count = employees.stream()
                .count();
        System.out.println(count);

        Optional<Employee> max = employees.stream()
                .max(Comparator.comparingDouble(Employee::getSalary));
        System.out.println(max.get());

        Optional<Double> min = employees.stream()
                .map(Employee::getSalary)
                .min(Double::compare);
        System.out.println(min.get());
    }

    /**
     * 归约
     * reduce(T identity, BinaryOperator)/reduce(BinaryOperator) -- 可以将流中的元素反复结合起来，得到一个值。
     */
    @Test
    public void test2(){
        List<Integer> list = Arrays.asList(1,2,3,4,5,6,7,8,9,10);
        Integer sum = list.stream()
                .reduce(0, (x, y) -> x + y);
        System.out.println(sum);

        System.out.println("---------------------------------------------------");

        Optional<Double> op = employees.stream()
                .map(Employee::getSalary)
                .reduce(Double::sum);
        System.out.println(op.get());
    }

    /**
     * 收集
     * collect ——将流转换为其它形式。接收一个collector接口的实现，用于给Stream中元素做汇总的方法。
     */
    @Test
    public void test3(){
        List<String> list = employees.stream()
                .map(Employee::getName)
                .collect(Collectors.toList());
        list.forEach(System.out::println);

        System.out.println("---------------------------");

        Set<String> set = employees.stream()
                .map(Employee::getName)
                .collect(Collectors.toSet());
        set.forEach(System.out::println);

        System.out.println("---------------------------");

        HashSet<String> hashSet = employees.stream()
                .map(Employee::getName)
                .collect(Collectors.toCollection(HashSet::new));
        hashSet.forEach(System.out::println);
    }

    @Test
    public void test4(){
        //总数
        Long count = employees.stream()
                .collect(Collectors.counting());
        System.out.println(count);

        System.out.println("---------------------------");

        //平均数
        Double avg = employees.stream()
                .collect(Collectors.averagingDouble(Employee::getSalary));
        System.out.println(avg);

        System.out.println("---------------------------");

        //总和
        Double sum = employees.stream()
                .collect(Collectors.summingDouble(Employee::getSalary));
        System.out.println(sum);

        System.out.println("---------------------------");

        //最大值
        Optional<Employee> max = employees.stream()
                .collect(Collectors.maxBy((x, y) -> Double.compare(x.getSalary(), y.getSalary())));
        System.out.println(max.get());

        System.out.println("---------------------------");

        //最小值
        Optional<Double> min = employees.stream()
                .map(Employee::getSalary)
                .collect(Collectors.minBy(Double::compare));
        System.out.println(min.get());
    }

    @Test
    public void test5(){
        //分组
        Map<Status, List<Employee>> statusListMap = employees.stream()
                .collect(Collectors.groupingBy(Employee::getStatus));

        System.out.println(statusListMap);

        System.out.println("---------------------------");

        //多级分区
        Map<Status, Map<String, List<Employee>>> statusMapMap = employees.stream()
                .collect(Collectors.groupingBy(Employee::getStatus, Collectors.groupingBy((e) -> {
                    if (e.getAge() < 30) {
                        return "青年";
                    } else if (e.getAge() < 50) {
                        return "中年";
                    } else {
                        return "老年";
                    }
                })));
        System.out.println(statusMapMap);

        System.out.println("---------------------------");

        //分区
        Map<Boolean, List<Employee>> booleanListMap = employees.stream()
                .collect(Collectors.partitioningBy((e) -> e.getSalary() > 9000.00));

        System.out.println(booleanListMap);
    }

    @Test
    public void test6(){
        //扩展汇总方式
        DoubleSummaryStatistics doubleSummaryStatistics = employees.stream()
                .collect(Collectors.summarizingDouble(Employee::getSalary));
        System.out.println(doubleSummaryStatistics.getCount());
        System.out.println(doubleSummaryStatistics.getSum());
        System.out.println(doubleSummaryStatistics.getAverage());
        System.out.println(doubleSummaryStatistics.getMax());
        System.out.println(doubleSummaryStatistics.getMin());

        System.out.println("---------------------------");

        //字符串拼接
        String collect = employees.stream()
                .map(Employee::getName)
                .collect(Collectors.joining(",", "----", "----"));
        System.out.println(collect);
    }
}
