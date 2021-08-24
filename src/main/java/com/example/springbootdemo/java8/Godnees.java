package com.example.springbootdemo.java8;

public class Godnees {

    private String name;

    public Godnees() {
    }

    public Godnees(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Godnees{" +
                "name='" + name + '\'' +
                '}';
    }
}
