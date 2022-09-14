package ru.job4j.serialization.xml;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "car")
public class Car {
    @XmlAttribute
    private String model;

    public Car() {
    }

    public Car(String model) {
        this.model = model;
    }

    @Override
    public String toString() {
        return "Car{" + "phone='" + model + '\'' + '}';
    }
}