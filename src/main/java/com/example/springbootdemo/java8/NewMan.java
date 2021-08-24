package com.example.springbootdemo.java8;

import java.util.Optional;

public class NewMan {
    private Optional<Godnees> godnees = Optional.empty();

    public NewMan() {
    }

    public NewMan(Optional<Godnees> godnees) {
        this.godnees = godnees;
    }

    public Optional<Godnees> getGodnees() {
        return godnees;
    }

    public void setGodnees(Optional<Godnees> godnees) {
        this.godnees = godnees;
    }

    @Override
    public String toString() {
        return "NewMan{" +
                "godnees=" + godnees +
                '}';
    }
}
