package org.job4j;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Map;
import java.util.function.Predicate;

public class Search {
    public static List<Path> fileSearch(Map<Args, String> args) throws IOException {
        Predicate<Path> searchPredicate = null;
        Path path = Paths.get(args.get(Args.ROOT));
        if (args.containsKey(Args.MASK_SEARCH)) {
            //TODO Predicate for mask
        } else if (args.containsKey(Args.FULL_NAME_SEARCH)) {
            //TODO Predicate for full name search
        } else if (args.containsKey(Args.REGEX_SEARCH)) {
            //TODO Predicate to regex search
        }
        FileSearcher searcher = new FileSearcher(searchPredicate);
        Files.walkFileTree(path, searcher);
        return searcher.getSearchResult();
    }
}
