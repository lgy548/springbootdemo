package com.example.springbootdemo.java8;

public class FilterEmployeeBySalary implements MyPredicate<Employee>{
    @Override
    public boolean test(Employee employee) {
        return employee.getSalary() < 12000.00;
    }
}
