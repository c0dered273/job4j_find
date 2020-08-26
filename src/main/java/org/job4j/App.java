package org.job4j;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Simple cli file search util
 * Args - contains program parameters.
 */

public class App {
    static final String MAIN_HELP_FILENAME = "main_help.txt";

    public static void main(String[] args) {
        Map<Args, String> parsedArgs = new HashMap<>();
        List<Path> searchResult = new ArrayList<>();
        try {
            parsedArgs = ArgsParser.parse(args);
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
            exitWithHelp();
        }
        if (parsedArgs.containsKey(Args.HELP)) {
            exitWithHelp();
        }
        try {
            searchResult = Search.fileSearch(parsedArgs);
        } catch (IOException e) {
            System.out.println("Error: can`t read files");
            e.printStackTrace();
        }
        searchResult.forEach(System.out::println);
        try {
            FileOutput.write(searchResult, Paths.get(parsedArgs.get(Args.OUTPUT)));
        } catch (IOException e) {
            System.out.println("Error: can`t write file");
            e.printStackTrace();
        }
    }

    private static void exitWithHelp() {
        HelpViewer.showConsoleHelp(MAIN_HELP_FILENAME);
        System.exit(64);
    }
}
