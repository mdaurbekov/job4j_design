package ru.job4j.io.duplicates;

import java.io.IOException;
import java.nio.file.FileVisitResult;
import java.nio.file.Path;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DuplicatesVisitor extends SimpleFileVisitor<Path> {
    private Map<FileProperty, List<String>> propertyies = new HashMap<>();

    @Override
    public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
        FileProperty fileProperty = new FileProperty(file.toFile().length(), file.getFileName().toString());
        String filePath = file.toAbsolutePath().toString();
        propertyies.computeIfAbsent(fileProperty, v -> new ArrayList<>()).add(filePath);
        return super.visitFile(file, attrs);
    }

    public Map<FileProperty, List<String>> getPropertyies() {
        return propertyies;
    }
}