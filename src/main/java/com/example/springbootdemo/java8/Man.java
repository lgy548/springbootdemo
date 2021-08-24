package com.example.springbootdemo.java8;

public class Man {
    private Godnees godnees;

    public Man() {
    }

    public Godnees getGodnees() {
        return godnees;
    }

    public void setGodnees(Godnees godnees) {
        this.godnees = godnees;
    }

    @Override
    public String toString() {
        return "Man{" +
                "godnees=" + godnees +
                '}';
    }
}
