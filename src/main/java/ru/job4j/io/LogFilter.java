package ru.job4j.io;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.List;
import java.util.stream.Collectors;

public class LogFilter {
    public List<String> filter(String file) {
        List<String> rezult = null;
        try (BufferedReader in = new BufferedReader(new FileReader(file))) {
            rezult = in.lines().filter(s -> s.matches("(.*) 404 (\\d*)$")).collect(Collectors.toList());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return rezult;
    }

    public static void main(String[] args) {
        LogFilter logFilter = new LogFilter();
        List<String> log = logFilter.filter("log.txt");
        log.forEach(System.out::println);

    }
}