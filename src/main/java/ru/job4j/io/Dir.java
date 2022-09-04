package ru.job4j.io;

import java.io.File;

public class Dir {
    public static void main(String[] args) {
        File file = new File("J:\\job4j\\job4j_design");
        if (!file.exists()) {
            throw new IllegalArgumentException(String.format("Not exist %s", file.getAbsoluteFile()));
        }
        if (!file.isDirectory()) {
            throw new IllegalArgumentException(String.format("Not directory %s", file.getAbsoluteFile()));
        }
        System.out.println(String.format("size : %s", file.getTotalSpace()));
        for (File subfile : file.listFiles()) {
            if (!subfile.isDirectory()) {
                System.out.printf("Файл - %s,  размер - %.2f Кб \n", subfile.getAbsoluteFile().getName(), subfile.getAbsoluteFile().length() / 1024.00);
            }

        }
    }
}