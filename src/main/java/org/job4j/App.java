package org.job4j;

import java.util.Map;

/**
 *
 */

public class App {
    static final String MAIN_HELP_FILENAME = "main_help.txt";

    public static void main(String[] args) {
        Map<String, String> parsedArgs = null;
        try {
            parsedArgs = ArgsParser.parse(args);
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
            HelpViewer.showConsoleHelp(MAIN_HELP_FILENAME);
            System.exit(64);
        }
        parsedArgs.forEach((key, value) -> System.out.println(key + " : " + value));
    }
}
