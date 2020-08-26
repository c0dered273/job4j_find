package org.job4j;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.util.*;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

public class SearchTest {
    File output;
    File file1;
    File file2;
    File file3;
    File file4;

    @Rule
    public TemporaryFolder temporaryFolder = new TemporaryFolder();

    @Before
    public void init() throws IOException {
        output = temporaryFolder.newFile("output.txt");
        file1 = temporaryFolder.newFile("file1.ttt");
        file2 = temporaryFolder.newFile("2file.t2t");
        file3 = temporaryFolder.newFile("file3.txt");
        file4 = temporaryFolder.newFile("file4.ttt");
    }

    @Test
    public void whenSearchRegExp() throws IOException {
        Map<Args, String> args = new HashMap<>();
        args.put(Args.ROOT, temporaryFolder.getRoot().toString());
        args.put(Args.SEARCH_PATTERN, "^2");
        args.put(Args.OUTPUT, output.getPath());
        List<Path> result = Search.fileSearch(args);
        List<Path> expect = Collections.singletonList(file2.toPath());
        assertThat(result, is(expect));
    }

    @Test
    public void whenSearchFullName() throws IOException {
        Map<Args, String> args = new HashMap<>();
        args.put(Args.ROOT, temporaryFolder.getRoot().toString());
        args.put(Args.SEARCH_PATTERN, "file3.txt");
        args.put(Args.OUTPUT, output.getPath());
        List<Path> result = Search.fileSearch(args);
        List<Path> expect = Collections.singletonList(file3.toPath());
        assertThat(result, is(expect));
    }

    @Test
    public void whenSearchMask() throws IOException {
        Map<Args, String> args = new HashMap<>();
        args.put(Args.ROOT, temporaryFolder.getRoot().toString());
        args.put(Args.SEARCH_PATTERN, "*.ttt");
        args.put(Args.MASK_SEARCH, "");
        args.put(Args.OUTPUT, output.getPath());
        List<Path> result = Search.fileSearch(args);
        List<Path> expect;
        String osName = System.getProperty("os.name").toLowerCase();
        if (osName.contains("linux")) {
            expect = Arrays.asList(file4.toPath(), file1.toPath());
        } else {
            expect = Arrays.asList(file1.toPath(), file4.toPath());
        }
        assertThat(result, is(expect));
    }
}