package ru.job4j.serialization.jsontopojo;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        Car car = new Car("Ford");
        Employee employee = new Employee("Ivan", 30, true, car, new String[]{"Maksim", "Oleg"});
        JSONObject jsonCar = new JSONObject("{\"model\":\"Ford\"}");

        List<String> list = new ArrayList<>();
        list.add("Maksim");
        list.add("Oleg");
        JSONArray jsonChildren = new JSONArray(list);

        JSONObject jsonObject = new JSONObject();
        jsonObject.put("name", employee.getName());
        jsonObject.put("age", employee.getAge());
        jsonObject.put("married", employee.isMarried());
        jsonObject.put("car", jsonCar);
        jsonObject.put("children", jsonChildren);

        System.out.println(jsonObject);

        System.out.println(new JSONObject(employee));


    }
}