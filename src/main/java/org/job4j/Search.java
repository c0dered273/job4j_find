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
     * @throws IOException if file walk error e.g. wrong search directory
     */
    public static List<Path> fileSearch(Map<Args, String> args) throws IOException {
        Path path = Paths.get(args.get(Args.ROOT));
        String searchString = args.get(Args.SEARCH_PATTERN);
        Predicate<Path> searchPredicate;
        if (args.containsKey(Args.REGEX_SEARCH)) {
            searchPredicate = regexSearch(searchString);
        } else if (args.containsKey(Args.FULL_NAME_SEARCH)) {
            searchPredicate = fullNameSearch(searchString);
        } else {
            searchPredicate = maskSearch(searchString); //Default search with empty parameters
        }
        FileSearcher searcher = new FileSearcher(searchPredicate);
        Files.walkFileTree(path, searcher);
        return searcher.getSearchResult();
    }

    private static Predicate<Path> maskSearch(String searchString) {
        int dotIndex = searchString.indexOf('.');
        String name = searchString.substring(0, dotIndex);
        String ext = searchString.substring(dotIndex + 1);
        Predicate<Path> rsl;
        if (name.equals("*")) {
            rsl = p -> p.getFileName().toString().endsWith(ext);
        } else if (ext.contains("*")) {
            rsl = p -> {
                String fileName = p.getFileName().toString();
                String clearName = fileName.substring(0, fileName.indexOf('.'));
                return clearName.endsWith(name);
            };
        } else {
            rsl = p -> p.getFileName().toString().equals(searchString);
        }
        return rsl;
    }

    private static Predicate<Path> regexSearch(String searchString) {
        Pattern pattern = Pattern.compile(searchString);
        return p -> pattern.matcher(p.getFileName().toString()).find();
    }

    private static Predicate<Path> fullNameSearch(String searchString) {
        return p -> p.getFileName().toString().equals(searchString);
    }
}
