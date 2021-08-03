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
public class TestStreamAPI2 {
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

    /**
     * 映射
     * map-接收Lambda,将元素转换成其它形式或提取信息，接收一个函数作为参数，改函数会被应用到每个元素上，并将其映射成一个新的元素
     * flatMap-接收一个函数作为参数，将流转每一个值都换成另一个流，然后把所有流都链接成一个流
     */


    @Test
    public void test7(){
        List<String> list = Arrays.asList("aaa","bbb","ccc");
        list.stream()
                .map((str) -> str.toUpperCase())
                .forEach(System.out::println);

        System.out.println("---------------------------------------------------");

        employees.stream()
                .map(Employee::getName)
                .forEach(System.out::println);

        System.out.println("---------------------------------------------------");

        Stream<Stream<Character>> streamStream = list.stream()
                .map(TestStreamAPI2::filterCharacter);//{{a,a,a},{b,b,b}}
        streamStream.forEach((sm) -> sm.forEach(System.out::println));

        System.out.println("---------------------------------------------------");

        list.stream()
                .flatMap(TestStreamAPI2::filterCharacter)//{a,a,a,b,b,b}
                .forEach(System.out::println);
    }

    public static Stream<Character> filterCharacter(String str){
        List<Character> list = new ArrayList<>();
        for (Character character : str.toCharArray()){
            list.add(character);
        }
        return list.stream();
    }

    /**
     * 排序
     * sorted()-自然排序(comparable)
     * sorted(comparator com)-定制排序(comparator)
     */
    @Test
    public void test8(){
        List<String> list = Arrays.asList("ddd","eee","aaa","bbb","ccc");
        list.stream()
                .sorted()
                .forEach(System.out::println);

        System.out.println("---------------------------------------------------");

        employees.stream()
                .sorted((x1,x2) -> {
                    if (x1.getAge().equals(x2.getAge())){
                        return x1.getName().compareTo(x2.getName());
                    }else {
                        return -x1.getAge().compareTo(x2.getAge());
                    }
                }).forEach(System.out::println);
    }
}
