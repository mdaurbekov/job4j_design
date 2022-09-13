package ru.job4j.serialization.json;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class Main {
    public static void main(String[] args) {
        Employee employee = new Employee("Ivan", 30, true, new Car("Ford"), new String[]{"Maksim", "Oleg"});

        Gson gson = new GsonBuilder().create();
        String strJson = gson.toJson(employee);
        System.out.println(strJson);

        Employee employeeMod = gson.fromJson(strJson, Employee.class);
        System.out.println(employeeMod);
    }
}