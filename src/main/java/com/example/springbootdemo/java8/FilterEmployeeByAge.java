package com.example.springbootdemo.java8;

public class FilterEmployeeByAge implements MyPredicate<Employee>{
    @Override
    public boolean test(Employee employee) {
        return employee.getAge() >= 25;
    }
}
