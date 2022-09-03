package ru.job4j.io;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;

import java.io.*;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class AnalizyTest {
    @Rule
    public TemporaryFolder folder = new TemporaryFolder();

    @Test
    public void checkTheServer() throws IOException {
        File source = new File("log.txt");
        File target = new File("unavailable.csv");
        try (PrintWriter out = new PrintWriter(source)) {
            out.println("200 10:56:01");
            out.println("500 10:57:01");
            out.println("400 10:58:01");
            out.println("200 10:59:01;");
            out.println("500 11:01:02");
            out.println("200 11:02:02");

        }
        Analizy firstTest = new Analizy();
        firstTest.unavailable("log.txt", "unavailable.csv");
        StringBuilder rsl = new StringBuilder();
        try (BufferedReader readerIn = new BufferedReader(new FileReader(target))) {
            readerIn.lines().forEach(rsl::append);
        }
        assertThat("10:57:01;10:59:01;11:01:02;11:02:02").isEqualTo(rsl.toString());
    }
}