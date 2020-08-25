package org.job4j;

import java.util.Map;

/**
 *
 */

public class App
{
    public static void main(String[] args) {
        for (String arg : args) {
            System.out.println(arg);
        }
//        ArgsParser argsParser = new ArgsParser(args);
//        Map<String, String> parsedArgs;
//        try {
//            parsedArgs = argsParser.getArgs();
//        } catch (IllegalArgumentException e) {
//            System.out.println(e.getMessage());
//            HelpViewer.showConsoleHelp();
//            System.exit(64);
//        }
//        System.out.println(argsParser.getArgs());
    }
}
