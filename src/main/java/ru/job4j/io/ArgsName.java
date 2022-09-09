package ru.job4j.io;

import java.util.HashMap;
import java.util.Map;

public class ArgsName {

    private final Map<String, String> values = new HashMap<>();

    public String get(String key) {
        if (!values.containsKey(key)) {
            throw new IllegalArgumentException();
        }
        return values.get(key);
    }

    private boolean checkParameters(String str) {
        if (!str.contains("=")) {
            throw new IllegalArgumentException("Отсутствует символ =");
        }
        String[] strings = str.replaceFirst("-", "").split("=", 2);

        if (strings[0].isBlank()) {
            throw new IllegalArgumentException("Отсутствует ключ");
        }
        if (strings[1].isBlank()) {
            throw new IllegalArgumentException("Отсутствует значение");
        }
        return true;
    }

    private void parse(String[] args) {
        for (String str : args) {
            checkParameters(str);
            String[] strings = str.replaceFirst("-", "").split("=", 2);
            values.put(strings[0], strings[1]);
        }
        System.out.println();
    }

    public static ArgsName of(String[] args) {
        if (args.length < 1) {
            throw new IllegalArgumentException();
        }
        ArgsName names = new ArgsName();
        names.parse(args);
        return names;
    }


    public static void main(String[] args) {
        /*
        ArgsName jvm = ArgsName.of(new String[]{"-Xmx=512", "-encoding=UTF-8"});
        System.out.println(jvm.get("Xmx"));

        ArgsName zip = ArgsName.of(new String[]{"-out=project.zip", "-encoding=UTF-8"});
        System.out.println(zip.get("out"));
         */
    }
}