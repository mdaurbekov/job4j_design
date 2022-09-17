package ru.job4j.io.find;

import ru.job4j.io.ArgsName;
import ru.job4j.io.SearchFiles;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.function.Predicate;

public class SearchEngine {

    public static void checkArgSearch(ArgsName argsName) {
        if (!Files.isDirectory(Paths.get(argsName.get("d")))) {
            throw new IllegalArgumentException("Неверный формат директории");
        }
        if (!argsName.get("n").matches("(.*).[^\\.]+")) {
            throw new IllegalArgumentException("Неверное имя файла/расширение");
        }
        String type = argsName.get("t");
        if (!("mask".equals(type) || "name".equals(type) || "regex".equals(type))) {
            throw new IllegalArgumentException("Неверное значение типа поиска");
        }
        if (!argsName.get("o").matches("(.*).txt")) {
            throw new IllegalArgumentException("Неверное расширение файла для записи");
        }

    }

    public static Predicate<Path> getPredicate(ArgsName argsName) {
        Predicate<Path> rezult = null;
        if ("mask".equals(argsName.get("t"))) {
            String valueName = argsName.get("n").replace(".", "[.]").replace("?", ".{1}").replace("*", "(.*)");
            rezult = p -> p.toFile().getName().matches(valueName);
        } else {
            rezult = p -> p.toFile().getName().matches(argsName.get("n"));
        }

        return rezult;
    }

    public static void saveToFile(List<Path> list, String file) {
        try (PrintWriter out = new PrintWriter(new BufferedOutputStream(new FileOutputStream(file)))) {
            list.forEach(out::println);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }

    }

    public static void main(String[] args) throws IOException {
        if (args.length != 4) {
            throw new IllegalArgumentException("Неверное количество параметров");
        }
        ArgsName argsName = ArgsName.of(args);
        checkArgSearch(argsName);
        Predicate<Path> condition = getPredicate(argsName);
        SearchFiles searcher = new SearchFiles(condition);
        Files.walkFileTree(Paths.get(argsName.get("d")), searcher);
        saveToFile(searcher.getPaths(), argsName.get("o"));

    }
}
