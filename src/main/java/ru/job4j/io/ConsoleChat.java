package ru.job4j.io;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Scanner;
import java.util.stream.Collectors;

public class ConsoleChat {
    private static final String OUT = "закончить";
    private static final String STOP = "стоп";
    private static final String CONTINUE = "продолжить";
    private final String path;
    private final String botAnswers;

    public ConsoleChat(String path, String botAnswers) {
        this.path = path;
        this.botAnswers = botAnswers;
    }


    public void run() {
        String input;
        boolean checkPause = false;
        List<String> list = readPhrases();
        List<String> log = new ArrayList<>();

        do {
            System.out.print("User: ");
            input = new Scanner(System.in).nextLine();
            log.add(input);
            if (Objects.equals(input, STOP)) {
                checkPause = true;
            }
            if (Objects.equals(input, CONTINUE)) {
                checkPause = false;
            }
            if (!checkPause & list.size() != 0) {
                String outPC = list.get((int) (Math.random() * list.size()));
                log.add(outPC);
                System.out.println("PC: " + outPC);
            }

        } while ((!Objects.equals(input, OUT)));
        saveLog(log);
    }

    private List<String> readPhrases() {
        List<String> list;
        try (BufferedReader read = new BufferedReader(new FileReader(this.botAnswers))) {
            list = read.lines().collect(Collectors.toList());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return list;
    }

    private void saveLog(List<String> log) {

        try (BufferedWriter write = new BufferedWriter(new PrintWriter(this.path))) {

            log.forEach(s -> {
                try {
                    write.write(s + System.lineSeparator());
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            });

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static void main(String[] args) {
        ConsoleChat cc = new ConsoleChat("J:/log.txt", "J:/answers.txt");
        cc.run();
    }
}