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

    private void parse(String[] args) {
        if (args.length < 1) {
            throw new IllegalArgumentException();
        }
        for (String str : args) {
            String[] strings = str.replaceFirst("-", "").split("=", 2);
            if (strings.length < 2 || strings[0].isBlank() || strings[1].isBlank()) {
                throw new IllegalArgumentException();
            }
            values.put(strings[0], strings[1]);
        }
        System.out.println();
    }

    public static ArgsName of(String[] args) {
        ArgsName names = new ArgsName();
        names.parse(args);
        return names;
    }

    public static void main(String[] args) {
        ArgsName jvm = ArgsName.of(new String[]{"-Xmx=512", "-encoding=UTF-8"});
        System.out.println(jvm.get("Xmx"));

        ArgsName zip = ArgsName.of(new String[]{"-out=project.zip", "-encoding=UTF-8"});
        System.out.println(zip.get("out"));
    }
}