package ru.job4j.io;

import java.io.*;
import java.util.concurrent.atomic.AtomicBoolean;

public class Analizy {
    public void unavailable(String source, String target) {
        try (BufferedReader read = new BufferedReader(new FileReader(source));
             PrintWriter out = new PrintWriter(new FileOutputStream(target))) {
            AtomicBoolean statusOk = new AtomicBoolean(true);
            read.lines().forEach(s -> {
                String[] strings = s.split(" ");
                int statusCode = Integer.parseInt(strings[0]);
                boolean checkStatus = statusCode == 400 || statusCode == 500;
                if (checkStatus == statusOk.get()) {
                    out.append(strings[1]).append(statusOk.get() ? ";" : System.lineSeparator());
                    statusOk.set(!statusOk.get());
                }

            });

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public static void main(String[] args) {
        /*
        try (PrintWriter out = new PrintWriter(new FileOutputStream("unavailable.csv"))) {
            out.println("15:01:30;15:02:32");
            out.println("15:10:30;23:12:32");
        } catch (Exception e) {
            e.printStackTrace();
        }

         */
        new Analizy().unavailable("log.txt", "unavailable.csv");
    }
}