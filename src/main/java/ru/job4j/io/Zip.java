package ru.job4j.io;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class Zip {

    public void packFiles(List<Path> sources, File target) {
        try (ZipOutputStream zip = new ZipOutputStream(new BufferedOutputStream(new FileOutputStream(target)))) {
            for (Path source : sources) {
                zip.putNextEntry(new ZipEntry(source.toFile().getPath()));
                try (BufferedInputStream out = new BufferedInputStream(new FileInputStream(source.toFile()))) {
                    zip.write(out.readAllBytes());
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void packSingleFile(File source, File target) {
        try (ZipOutputStream zip = new ZipOutputStream(new BufferedOutputStream(new FileOutputStream(target)))) {
            zip.putNextEntry(new ZipEntry(source.getPath()));

            try (BufferedInputStream out = new BufferedInputStream(new FileInputStream(source))) {
                zip.write(out.readAllBytes());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void checkArgZip(ArgsName argsName) {
        Path path = Paths.get(argsName.get("d"));
        if (!Files.isDirectory(path)) {
            throw new IllegalArgumentException("Неверный формат директории");
        }

        if (!argsName.get("e").matches("\\.[^\\.]+")) {
            throw new IllegalArgumentException("Неверное расширение");
        }

        if (!argsName.get("o").matches("(.*).zip")) {
            throw new IllegalArgumentException("Неверный формат архива");
        }

    }

    public static void main(String[] args) throws IOException {
        if (args.length != 3) {
            throw new IllegalArgumentException("Неверное количество параметров");
        }
        ArgsName argsName = ArgsName.of(args);
        checkArgZip(argsName);
        Zip zip1 = new Zip();
        Path path = Paths.get(ArgsName.of(args).get("d"));
        SearchFiles searchFiles = new SearchFiles(p -> !p.toFile().getName().endsWith(ArgsName.of(args).get("e")));
        Files.walkFileTree(path, searchFiles);
        zip1.packFiles(searchFiles.getPaths(), new File(ArgsName.of(args).get("o")));

    }
}