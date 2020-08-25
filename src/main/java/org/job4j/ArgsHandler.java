package org.job4j;

import java.util.HashMap;
import java.util.Map;

/**
 *
 */

public class ArgsHandler {
    private Map<String, String> args = new HashMap<>();
    private final String[] rawArgs;
    private String[] validArgs = {
            "-h", "-d", "-n", "-m", "-f", "-r", "-o"
    };

    public ArgsHandler(String[] rawArgs) {
        this.rawArgs = rawArgs;
    }

    public boolean isValid() {
        parseArgs();
        return false; //TODO args validation
    }

    public Map<String, String> getArgs() {
        parseArgs();
        return args;
    }

    public void showConsoleHelp() {
        //TODO show help;
    }

    private void parseArgs() throws IllegalArgumentException {
        //TODO parse args
        String par = "";
        throw new IllegalArgumentException(String.format("Unknown parameter: %s", par));
    }
}
