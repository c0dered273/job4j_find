package org.job4j;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Map;
import java.util.function.Predicate;
import java.util.regex.Pattern;

/**
 * Static class provides search files with various parameters
 */
public class Search {

    /**
     * Method gets map of search parameters and selects the appropriate predicate for file walker
     * @param args Map of parsed command line arguments
     * @return List of Path with founded files
     * @throws IOException if file walk error e.g. wrong start directory
     */
    public static List<Path> fileSearch(Map<Args, String> args) throws IOException {
        Path path = Paths.get(args.get(Args.ROOT));
        String searchString = args.get(Args.SEARCH_PATTERN);
        Predicate<Path> searchPredicate = fullNameSearch(searchString); //Default full name search
        if (args.containsKey(Args.MASK_SEARCH)) {
            searchPredicate = maskSearch(searchString);
        } else if (args.containsKey(Args.REGEX_SEARCH)) {
            searchPredicate = regexSearch(searchString);
        }
        FileSearcher searcher = new FileSearcher(searchPredicate);
        Files.walkFileTree(path, searcher);
        return searcher.getSearchResult();
    }

    private static Predicate<Path> fullNameSearch(String searchString) {
        return p -> p.getFileName().toString().equals(searchString);
    }

    private static Predicate<Path> maskSearch(String searchString) {
        String fileEx = searchString.substring(searchString.lastIndexOf('.'));
        return p -> p.getFileName().toString().endsWith(fileEx);
    }

    private static Predicate<Path> regexSearch(String searchString) {
        Pattern pattern = Pattern.compile(searchString);
        return p -> pattern.matcher(p.getFileName().toString()).find();
    }
}
