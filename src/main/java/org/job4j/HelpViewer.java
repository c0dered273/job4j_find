package org.job4j;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

/**
 * Shows user help to console.
 */

public class HelpViewer {

    /**
     * Prints text file from resources to console
     * @param fileName file exists in resources
     */
    public static void showConsoleHelp(String fileName) {
        InputStream is = HelpViewer.class.getClassLoader().getResourceAsStream(fileName);
        if (is != null) {
            try (BufferedReader reader = new BufferedReader(new InputStreamReader(is))) {
                reader.lines().forEach(System.out::println);
            } catch (IOException e) {
                System.out.println("Help file read error");
                e.printStackTrace();
            }
        } else {
            System.out.println("Help file not found");
        }
    }
}
