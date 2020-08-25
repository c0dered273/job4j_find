package org.job4j;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class HelpViewer {
    private static final String fileName = "help.txt";

    private HelpViewer() {
    }

    static void showConsoleHelp() {
        InputStream is = HelpViewer.class.getClassLoader().getResourceAsStream(fileName);
        if (is != null) {
            try (BufferedReader reader = new BufferedReader(new InputStreamReader(is))) {
                reader.lines().forEach(System.out::println);
            } catch (IOException e) {
                System.out.println("Help file not found");
                e.printStackTrace();
            }
        }
    }
}
