package ru.job4j.serialization.xml;

import javax.xml.bind.annotation.*;
import java.util.Arrays;

@XmlRootElement(name = "employee")
@XmlAccessorType(XmlAccessType.FIELD)
public class Employee {
    @XmlAttribute
    private String name;
    @XmlAttribute
    private int age;
    @XmlAttribute
    private boolean married;
    private Car car;
    @XmlElementWrapper
    @XmlElement
    private String[] children;

    public Employee() {
    }

    public Employee(String name, int age, boolean married, Car car, String[] children) {
        this.name = name;
        this.age = age;
        this.married = married;
        this.car = car;
        this.children = children;
    }

    @Override
    public String toString() {
        return "Employee{" + "name='" + name + '\'' + ", age=" + age + ", married=" + married + ", car=" + car + ", children=" + Arrays.toString(children) + '}';
    }
}