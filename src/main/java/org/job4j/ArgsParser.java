package org.job4j;

import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

/**
 *
 */

public class ArgsParser {
    private Map<String, String> args = new HashMap<>();
    private final String[] rawArgs;
    private HashSet<String> validArgs = new HashSet<>(Arrays.asList(
            "-h", "-d", "-n", "-m", "-f", "-r", "-o"
    ));

    public ArgsParser(String[] rawArgs) {
        this.rawArgs = rawArgs;
    }

    public Map<String, String> getArgs() throws IllegalArgumentException {
        parseArgs();
        return args;
    }

    private void parseArgs() throws IllegalArgumentException {
        //TODO parse args
        if (rawArgs.length == 0) {
            throw new IllegalArgumentException("Error: empty parameters");
        }
        for (String rawArg : rawArgs) {
//            if (rawArg.length() < 3) {
//                throw new IllegalArgumentException(String.format("Error: invalid parameter: %s", rawArg));
//            }
//            String arg = rawArg.substring(0, 3);
//            String value = rawArg.substring(3);
//            if (validArgs.contains(arg)) {
//                args.put(rawArg, value);
//            } else {
//                throw new IllegalArgumentException(String.format("Error: unknown parameter: %s", rawArg));
//            }
        }
    }
}
