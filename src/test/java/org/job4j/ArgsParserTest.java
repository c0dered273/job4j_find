package org.job4j;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import java.util.HashMap;
import java.util.Map;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

public class ArgsParserTest {
    @Rule
    public ExpectedException exception = ExpectedException.none();

    @Test
    public void whenParseValidArgs() {
        String[] args = {"-d", "/test/path", "-o", "filename.log", "-m", "-r","-n", "*.txt"};
        Map<String, String> result = ArgsParser.parse(args);
        Map<String, String> expect = new HashMap<>();
        expect.put("-d", "/test/path");
        expect.put("-o", "filename.log");
        expect.put("-m", "");
        expect.put("-r", "");
        expect.put("-n", "*.txt");
        assertThat(result, is(expect));
    }

    @Test
    public void whenUnknownParameter() {
        String un = "-q";
        exception.expect(IllegalArgumentException.class);
        exception.expectMessage("Error: unknown parameter: " + un);
        String[] args = {"-d", "/test/path", un};
        ArgsParser.parse(args);
    }

    @Test
    public void whenInvalidParameter() {
        String un = "invalid";
        exception.expect(IllegalArgumentException.class);
        exception.expectMessage("Error: invalid parameter: " + un);
        String[] args = {"-d", "/test/path", un};
        ArgsParser.parse(args);
    }
}