package com.example.springbootdemo;

import com.example.springbootdemo.java8.ForkJoinCalculate;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.Duration;
import java.time.Instant;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ForkJoinTask;
import java.util.stream.LongStream;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ForkJoinTest {

    /**
     * ForkJoin 框架
     */
    @Test
    public void test1(){
        Instant start = Instant.now();
        ForkJoinPool pool = new ForkJoinPool();
        ForkJoinTask<Long> task = new ForkJoinCalculate(0,100000000L);
        Long sum = pool.invoke(task);
        System.out.println(sum);
        Instant end = Instant.now();
        System.out.println("执行时间为："+ Duration.between(start,end).toMillis());//执行时间为：95
    }

    /**
     * for循环
     */
    @Test
    public void test2(){
        Instant start = Instant.now();
        long sum = 0;
        for (long i = 0; i <= 100000000L; i++) {
            sum += i;
        }
        System.out.println(sum);
        Instant end = Instant.now();
        System.out.println("执行时间为："+ Duration.between(start,end).toMillis());//执行时间为：155
    }

    /**
     * java8 并行流
     */
    @Test
    public void test3(){
        Instant start = Instant.now();
        long sum = LongStream.rangeClosed(0, 100000000L)
                .parallel()
                .reduce(0, Long::sum);
        System.out.println(sum);
        Instant end = Instant.now();
        System.out.println("执行时间为："+ Duration.between(start,end).toMillis());//执行时间为：50
    }
}
