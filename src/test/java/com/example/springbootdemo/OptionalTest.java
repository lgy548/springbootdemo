package com.example.springbootdemo;

import com.example.springbootdemo.java8.Employee;
import com.example.springbootdemo.java8.Godnees;
import com.example.springbootdemo.java8.Man;
import com.example.springbootdemo.java8.NewMan;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Optional;

@RunWith(SpringRunner.class)
@SpringBootTest
public class OptionalTest {
    /**
     * Optional.of(T t) ： 创建一个Optional实例
     * Optional.empty() ： 创建一个空的Optional实例
     * Optional.ofNullable(T t) ： 若t不为null，创建Optional实例，否则创建空实例
     * isPresent() ： 判断是否包含值
     * orElse(T t) ： 如果调用对象包含值，返回该值，否则返回t
     * orElseGet(Supplier s) ： 如果调用对象包含值，返回该值，否则返回s获取的值
     * map(Function f) ： 如果有值对其处理，并返回处理后的Optional，否则返回Optional.empty()
     * flatMap(Function mapper) ： 与map类似，要求返回值必须是Optional
     */

    @Test
    public void test1(){
        Optional<Employee> op = Optional.of(new Employee());
//        创建不能传入空值，可以快速定位对象为空
//        Optional<Employee> op = Optional.of(null);
        Employee employee = op.get();
        System.out.println(employee);
    }

    @Test
    public void test2(){
        Optional<Employee> op = Optional.empty();
        //创建一个空对象，下面输出会报null指针异常
        System.out.println(op.get());
    }

    @Test
    public void test3(){
        //此创建方法为上述两的总和，根据判断分别走上述两个方法，可看源码
        Optional<Employee> op = Optional.ofNullable(new Employee());
//        Optional<Employee> op = Optional.ofNullable(null);
        //判断是否为空
        if (op.isPresent()){
            System.out.println(op.get());
        }
    }

    @Test
    public void test4(){
        Optional<Employee> op = Optional.ofNullable(new Employee());
//        Optional<Employee> op = Optional.ofNullable(null);
        Employee employee = op.orElse(new Employee("张三", 21, 1000));
        System.out.println(employee);
    }

    @Test
    public void test5(){
        Optional<Employee> op = Optional.ofNullable(new Employee());
//        Optional<Employee> op = Optional.ofNullable(null);
        Employee employee = op.orElseGet(() -> new Employee("张三", 21, 1000));
        System.out.println(employee);
    }

    @Test
    public void test6(){
        Optional<Employee> op = Optional.ofNullable(new Employee("张三", 21, 1000));
        Optional<String> str = op.map(e -> e.getName());
        //返回类型必须为Optional
        Optional<String> str2 = op.flatMap(e -> Optional.ofNullable(e.getName()));
        System.out.println(str);
        System.out.println(str2);
    }

    //例题
    @Test
    public void test7(){
        /*Man man = new Man();
        String godneesName = getGodneesName(man);
        System.out.println(godneesName);*/

        Optional<NewMan> op = Optional.ofNullable(null);
//        Optional<NewMan> op = Optional.ofNullable(new NewMan());
        /*Optional<Godnees> gn = Optional.ofNullable(null);
        Optional<NewMan> op = Optional.ofNullable(new NewMan(gn));*/
        /*Optional<Godnees> gn = Optional.ofNullable(new Godnees("波多老师"));
        Optional<NewMan> op = Optional.ofNullable(new NewMan(gn));*/
        String godnessName2 = getGodnessName2(op);
        System.out.println(godnessName2);
    }

    public String getGodnessName2(Optional<NewMan> man){
        return man.orElse(new NewMan()).getGodnees().orElse(new Godnees("苍老师")).getName();
    }

    //需求获取一个男人心中女神的名字
    public String getGodneesName(Man man){
        if (null != man){
            Godnees godnees = man.getGodnees();
            if (null != godnees){
                return godnees.getName();
            }
        }
        return "苍老师";
    }
}
