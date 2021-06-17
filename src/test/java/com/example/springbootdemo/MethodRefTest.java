package com.example.springbootdemo;

import com.example.springbootdemo.java8.Employee;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.PrintStream;
import java.util.Comparator;
import java.util.function.BiPredicate;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;

@RunWith(SpringRunner.class)
@SpringBootTest
public class MethodRefTest {
    /*
    * 一、方法引用：若Lambda 体中的内容有方法已经实现了，我们可以使用“方法引用”
    *         （可以理解为方法引用是Lambda 表达式的另一种表现形式）
    *
    * 主要有三种语法格式：
    *
    * 对像::实例方法名
    *
    * 类::静态方法名
    *
    * 类::实例方法名
    *
    * 注意：
    * 1、lambda体中调用方法的参数列表和返回值类型，要与函数式接口中抽象方法的参数列表和返回值类型相同
    * 2、若lambda 参数列表中的第一个参数是方法调用者，而第二个参数是实例方法的参数时，可以使用类::实例方法名（ClassName::method）
    *
    * 二、构造器引用：
    *
    * 格式：
    * ClassName::new
    *
    * 注意：需要调用构造器的参数列表需要与函数式接口中抽象方法的参数列表保持一致
    *
    * 三、数组引用：
    *
    * 格式：
    * Type::new
    *
    * */

    //对像::实例方法名
    @Test
    public void test1(){
        PrintStream ps = System.out;
        Consumer<String> con = (x) ->ps.println(x);

        PrintStream ps1 = System.out;
        Consumer<String> con1 = ps1::println;

        Consumer<String> con2 = System.out::println;

        con.accept("写法一");
        con1.accept("写法二");
        con2.accept("写法三");

        Employee emp = new Employee();
        emp.setAge(20);
        emp.setName("示例");
        Supplier<String> sup = emp::getName;
        String str = sup.get();
        System.out.println(str);

        Supplier<Integer> sup2 = emp::getAge;
        Integer age = sup2.get();
        System.out.println(age);
    }

    //类::静态方法名
    @Test
    public void test2(){
        Comparator<Integer> cp = (x,y) -> Integer.compare(x,y);
        int flag = cp.compare(1, 2);
        System.out.println(flag);

        Comparator<Integer> cp1 = Integer::compare;
        int flag1 = cp1.compare(2, 1);
        System.out.println(flag1);
    }

    //类::实例方法名
    @Test
    public void test3(){
        BiPredicate<String,String> bp = (x,y) -> x.equals(y);
        System.out.println(bp.test("123","321"));

        BiPredicate<String,String> bp1 = String::equals;
        System.out.println(bp1.test("123","123"));
    }

    //构造器引用
    @Test
    public void test4(){
        Supplier<Employee> sp = () -> new Employee();
        System.out.println(sp.get());
        //构造器引用
        Supplier<Employee> sp1 = Employee::new;
        System.out.println(sp1.get());
    }
    @Test
    public void test5(){
        Function<Integer,Employee> fun = (x) -> new Employee(x);
        System.out.println(fun.apply(55));

        Function<Integer,Employee> fun1 = Employee::new;
        System.out.println(fun1.apply(25));
    }

    //数组引用
    @Test
    public void test6(){
        Function<Integer,String[]> fun = (x) -> new String[x];
        System.out.println(fun.apply(10));

        Function<Integer,String[]> fun1 = String[]::new;
        System.out.println(fun1.apply(20));
    }
}
