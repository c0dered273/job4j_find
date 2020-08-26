package org.job4j;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * This class performs common validate and parse command line arguments.
 */
public class ArgsParser {
    /**
     * Regex pattern to check that is a key parameter (contains two characters: '-' and letter) and not a value.
     */
    private static final String KEY_PATTERN = "^-[a-zA-Z]$";

    /**
     * Mandatory keys.
     */
    private static final Args[] mandatoryArgs = {Args.ROOT, Args.SEARCH_PATTERN, Args.OUTPUT};

    /**
     * Optional keys. Only one key allowed.
     */
    private static final Args[] optionalArgs = {Args.MASK_SEARCH, Args.FULL_NAME_SEARCH, Args.REGEX_SEARCH};

    /**
     * Method gets arguments to map or throws exception with error message
     * @param strArgs command line arguments
     * @return HashMap with result
     * @throws IllegalArgumentException if unknown key parameter or two values in a row
     */
    public static Map<Args, String> parse(String[] strArgs) throws IllegalArgumentException {
        Map<Args, String> result = new HashMap<>();
        if (strArgs.length == 0) {
            throw new IllegalArgumentException("Error: empty parameters");
        }
        Pattern p = Pattern.compile(KEY_PATTERN);
        Matcher m;
        Args emptyArg = null;
        for (String strArg : strArgs) {
            m = p.matcher(strArg);
            if (m.matches()) {
                Args arg = getArgFromString(strArg);
                if (arg == null) {
                    throw new IllegalArgumentException(String.format(
                            "Error: unknown parameter: %s", strArg));
                }
                result.put(arg, "");
                emptyArg = arg;
            } else if (emptyArg != null) {
                result.put(emptyArg, strArg);
                emptyArg = null;
            } else {
                throw new IllegalArgumentException(String.format(
                        "Error: invalid parameter: %s", strArg));
            }
        }
        checkMandatory(result);
        checkOptions(result);
        return result;
    }

    private static void checkMandatory(Map<Args, String> args) throws IllegalArgumentException {
        for (Args mandatory : mandatoryArgs) {
            if (!args.containsKey(mandatory) || args.get(mandatory).isEmpty()) {
                throw new IllegalArgumentException(String.format(
                        "Error: not found mandatory parameter: %s", mandatory.getValue()));
            }
        }
    }

    private static void checkOptions(Map<Args, String> args) throws IllegalArgumentException {
        boolean isOptionPresent = false;
        for (Args option : optionalArgs) {
            if (args.containsKey(option)) {
                if (isOptionPresent) {
                    throw new IllegalArgumentException("Error: to many search options");
                }
                isOptionPresent = true;
            }
        }
    }

    private static Args getArgFromString(String s) {
        Args rsl = null;
        Args[] argsList = Args.values();
        for (Args arg : argsList) {
            if (s.equals(arg.getValue())) {
                rsl = arg;
                break;
            }
        }
        return rsl;
    }
}
