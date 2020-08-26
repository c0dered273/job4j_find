package org.job4j;

import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * This class performs common validate and parse command line arguments.
 */
public class ArgsParser {
    /**
     * Regex pattern to check that is a key parameter and not a value.
     */
    private static final String KEY_PATTERN = "^-[a-zA-Z]$";

    /**
     * Valid command line key parameters.
     */
    private static final HashSet<String> validArgs = new HashSet<>(Arrays.asList(
            "-h", "-d", "-n", "-m", "-f", "-r", "-o"
    ));

    /**
     * Method gets arguments to map or throws exception with error message
     * @param args command line arguments
     * @return HashMap with result
     * @throws IllegalArgumentException if unknown key parameter or two values in a row
     */
    public static Map<String, String> parse(String[] args) throws IllegalArgumentException {
        Map<String, String> result = new HashMap<>();
        if (args.length == 0) {
            throw new IllegalArgumentException("Error: empty parameters");
        }
        Pattern p = Pattern.compile(KEY_PATTERN);
        Matcher m;
        String emptyArg = null;
        for (String arg : args) {
            m = p.matcher(arg);
            if (m.matches()) {
                if (!validArgs.contains(arg)) {
                    throw new IllegalArgumentException(String.format("Error: unknown parameter: %s", arg));
                }
                result.put(arg, "");
                emptyArg = arg;
            } else if (emptyArg != null) {
                result.put(emptyArg, arg);
                emptyArg = null;
            } else {
                throw new IllegalArgumentException(String.format("Error: invalid parameter: %s", arg));
            }
        }
        return result;
    }
}
