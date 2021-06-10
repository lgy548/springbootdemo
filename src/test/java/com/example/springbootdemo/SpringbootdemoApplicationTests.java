package com.example.springbootdemo;

import com.example.springbootdemo.java8.Employee;
import com.example.springbootdemo.java8.FilterEmployeeByAge;
import com.example.springbootdemo.java8.FilterEmployeeBySalary;
import com.example.springbootdemo.java8.MyPredicate;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SpringbootdemoApplicationTests {

    List<Employee> employees = Arrays.asList(
            new Employee("王力",30,14000.00),
            new Employee("蒲大坤",27,13000.00),
            new Employee("刘高杨",23,12000.00),
            new Employee("高帅杰",25,11000.00),
            new Employee("张成",8,0.00)
    );

    //找出年龄大于等于25
    //找出工资小于12000.00
    public List<Employee> filterEmployeeByAge(List<Employee> list){
        List<Employee> emps = new ArrayList<>();
        for (Employee emp : list) {
            if (emp.getAge() >= 25){
                emps.add(emp);
            }
        }
        return emps;
    }

    public List<Employee> filterEmployeeBySalary(List<Employee> list){
        List<Employee> emps = new ArrayList<>();
        for (Employee emp : list) {
            if (emp.getSalary() < 12000.00){
                emps.add(emp);
            }
        }
        return emps;
    }

    @Test
    public void test() {
        List<Employee> list = filterEmployeeByAge(employees);
        for (Employee emp : list){
            System.out.println(emp);
        }
        System.out.println("---------------------------------------------");
        List<Employee> list2 = filterEmployeeBySalary(employees);
        for (Employee emp : list2){
            System.out.println(emp);
        }
    }

    //优化方案一：策略设计模式
    public List<Employee> filterEmployee(List<Employee> list, MyPredicate<Employee> mp){
        List<Employee> emps = new ArrayList<>();
        for (Employee emp : list) {
            if (mp.test(emp)){
                emps.add(emp);
            }
        }
        return emps;
    }
    @Test
    public void test1() {
        List<Employee> list = filterEmployee(employees,new FilterEmployeeByAge());
        for (Employee emp : list){
            System.out.println(emp);
        }
        System.out.println("---------------------------------------------");
        List<Employee> list2 = filterEmployee(employees,new FilterEmployeeBySalary());
        for (Employee emp : list2){
            System.out.println(emp);
        }
    }

    //优化方案二：匿名内部类
    @Test
    public void test2() {
        List<Employee> list = filterEmployee(employees, new MyPredicate<Employee>() {
            @Override
            public boolean test(Employee employee) {
                return employee.getAge() >= 25;
            }
        });
        for (Employee emp : list){
            System.out.println(emp);
        }
        System.out.println("---------------------------------------------");
        List<Employee> list2 = filterEmployee(employees, new MyPredicate<Employee>() {
            @Override
            public boolean test(Employee employee) {
                return employee.getSalary() < 12000.00;
            }
        });
        for (Employee emp : list2){
            System.out.println(emp);
        }
    }

    //优化方案三：lambda表达式
    @Test
    public void test3() {
        List<Employee> list = filterEmployee(employees,(e) -> e.getAge() >= 25);
        list.forEach(System.out::println);
        System.out.println("---------------------------------------------");
        List<Employee> list2 = filterEmployee(employees,(e) -> e.getSalary() < 12000.00);
        list2.forEach(System.out::println);
    }

    //优化方案四：Stream API
    @Test
    public void test4() {
        employees.stream()
                .filter((e) -> e.getAge() >= 25)
                .forEach(System.out::println);
        System.out.println("---------------------------------------------");
        employees.stream()
                .filter((e) -> e.getSalary() < 12000.00)
                .forEach(System.out::println);
        System.out.println("---------------------------------------------");
        employees.stream()
                .limit(2)//前两行
                .map(Employee::getName)//选择参数
                .forEach(System.out::println);
    }
}
