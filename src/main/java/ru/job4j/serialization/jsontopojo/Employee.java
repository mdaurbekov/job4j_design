package ru.job4j.serialization.jsontopojo;


import java.util.Arrays;

public class Employee {
    private String name;
    private int age;
    private boolean married;
    private Car car;
    private String[] children;


    public Employee(String name, int age, boolean married, Car car, String[] children) {
        this.name = name;
        this.age = age;
        this.married = married;
        this.car = car;
        this.children = children;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public boolean isMarried() {
        return married;
    }

    @Override
    public String toString() {
        return "Employee{" + "name='" + name + '\'' + ", age=" + age + ", married=" + married + ", car=" + car + ", children=" + Arrays.toString(children) + '}';
    }
}