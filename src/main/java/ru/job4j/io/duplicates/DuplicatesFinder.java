package ru.job4j.io.duplicates;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.Map;

public class DuplicatesFinder {
    public static void main(String[] args) throws IOException {
        DuplicatesVisitor duplicatesVisitor = new DuplicatesVisitor();
        Files.walkFileTree(Path.of("./"), duplicatesVisitor);
        Map<FileProperty, List<String>> map = duplicatesVisitor.getPropertyies();
        map.values().stream()
                .filter(e -> e.size() > 1)
                .forEach(e -> e.forEach(System.out::println));
        System.out.println();
    }
}