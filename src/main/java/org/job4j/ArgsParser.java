package org.job4j;

import java.util.HashMap;
import java.util.Map;

/**
 *
 */

public class ArgsParser {
    private Map<String, String> args = new HashMap<>();
    private final String[] rawArgs;
    private String[] validArgs = {
            "-h", "-d", "-n", "-m", "-f", "-r", "-o"
    };

    public ArgsParser(String[] rawArgs) {
        this.rawArgs = rawArgs;
    }

    public Map<String, String> getArgs() throws IllegalArgumentException {
        parseArgs();
        return args;
    }

    private void parseArgs() throws IllegalArgumentException {
        //TODO parse args
        boolean isEnough = false;
        String par = "";
        if (rawArgs.length == 0) {
            throw new IllegalArgumentException("Error: empty parameters");
        }

        if (!isEnough) {
            throw new IllegalArgumentException("Error: not enough parameters");
        }

        throw new IllegalArgumentException(String.format("Error: unknown parameter: %s", par));
    }
}
