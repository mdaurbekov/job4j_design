package ru.job4j.io;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.function.Predicate;


public class Search {
    public static void main(String[] args) throws IOException {
        checkParametrs(args);
        Path start = Paths.get(args[0]);
        search(start, p -> p.toFile().getName().endsWith(args[1])).forEach(System.out::println);

    }

    public static List<Path> search(Path root, Predicate<Path> condition) throws IOException {
        SearchFiles searcher = new SearchFiles(condition);
        Files.walkFileTree(root, searcher);
        return searcher.getPaths();
    }

    public static void checkParametrs(String[] parameters) {
        if (parameters.length < 2) {
            throw new IllegalArgumentException("Недостаточно параметров");
        }
        if (!parameters[1].matches("\\.[^\\.]+")) {
            throw new IllegalArgumentException("Неверное расширение файла");
        }
        Path path = Paths.get(parameters[0]);
        if (!Files.exists(path) & !Files.isDirectory(path)) {
            throw new IllegalArgumentException("Каталог отсутствует");
        }

    }
}