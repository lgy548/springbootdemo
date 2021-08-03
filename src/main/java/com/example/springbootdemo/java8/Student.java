package com.example.springbootdemo.java8;

public class Student {

    private int sex;
    private int age;

    public int getSex() {
        return sex;
    }

    public void setSex(int sex) {
        this.sex = sex;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public Student(int sex, int age) {
        this.sex = sex;
        this.age = age;
    }

    public Student() {
    }
}
