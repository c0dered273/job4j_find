package org.job4j;

import java.io.IOException;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
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
            System.out.println("File read error");
            e.printStackTrace();
        }
        //TODO Search result processing
    }

    private static void exitWithHelp() {
        HelpViewer.showConsoleHelp(MAIN_HELP_FILENAME);
        System.exit(64);
    }
}
