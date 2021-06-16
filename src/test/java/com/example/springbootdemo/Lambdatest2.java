package com.example.springbootdemo;

import com.example.springbootdemo.java8.Employee;
import com.example.springbootdemo.java8.MyFunction;
import com.example.springbootdemo.java8.MyFunction2;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class Lambdatest2 {
    List<Employee> employees = Arrays.asList(
            new Employee("王力",30,14000.00),
            new Employee("蒲大坤",27,13000.00),
            new Employee("刘高杨",23,12000.00),
            new Employee("高帅杰",25,11000.00),
            new Employee("张成",8,0.00)
    );
    //调用 Collections.sort() 方法，通过定制排序比较两个Employee（年龄比较，年龄相同工资比较）
    @Test
    public void test1(){
        Collections.sort(employees,(x,y) -> {
            if (x.getAge() == y.getAge()){
                return Double.compare(x.getSalary(),y.getSalary());
            }else {
              return Integer.compare(x.getAge(), y.getAge());
            }
        });
        for (Employee employee : employees){
            System.out.println(employee);
        }
    }

    //1.声明函数式接口，接口中声明抽象方法，public String getValue(String str);
    //2.测试类中编写方法使用接口作为参数，将字符串转为大写，并作为方法返回值。
    //3.再将字符串的第2个和第4个索引位置进行截取字符串，
    @Test
    public void test2(){
        String s = strHandler("qwertyuio", (str) -> str.toUpperCase());
        System.out.println(s);
        System.out.println(strHandler(s,str -> str.substring(2,4)));
    }

    public String strHandler(String str, MyFunction mf){
        return mf.getValue(str);
    }

    //1.声明一个带两个泛型的函数式接口，声明泛型类型为<T,R> T为参数，R为返回值
    //2.接口中声明对应的抽象方法
    //3.在测试类中声明方法，使用接口作为参数，计算两个long型参数的和。
    //4.再计算两个参数的乘积。
    @Test
    public void test3(){
        op(100L,200L,(x,y) -> x+y);
        op(100L,200L,(x,y) -> x*y);
    }

    public void op(Long l1, Long l2, MyFunction2<Long,Long> mf){
        System.out.println(mf.getValue(l1,l2));
    }
}
