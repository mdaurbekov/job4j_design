package ru.job4j.io;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import java.io.*;
import java.nio.file.Path;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class AnalizyTest {

    @Test
    public void checkTheServer(@TempDir Path tempDir) throws IOException {
        File source = tempDir.resolve("log1.txt").toFile();
        File target = tempDir.resolve("unavailable1.csv").toFile();
        try (PrintWriter out = new PrintWriter(source)) {
            out.println("200 10:56:01");
            out.println("500 10:57:01");
            out.println("400 10:58:01");
            out.println("200 10:59:01");
            out.println("500 11:01:02");
            out.println("200 11:02:02");

        }
        Analizy firstTest = new Analizy();
        firstTest.unavailable(source.getAbsolutePath(), target.getAbsolutePath());
        StringBuilder rsl = new StringBuilder();
        try (BufferedReader readerIn = new BufferedReader(new FileReader(target))) {
            readerIn.lines().forEach(rsl::append);
        }
        assertThat("10:57:01;10:59:0111:01:02;11:02:02").isEqualTo(rsl.toString());

    }
}