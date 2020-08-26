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
        String[] args = {"-d", "/test/path", "-o", "filename.log", "-m", "-r", "-n", "*.txt"};
        Map<Args, String> result = ArgsParser.parse(args);
        Map<Args, String> expect = new HashMap<>();
        expect.put(Args.ROOT, "/test/path");
        expect.put(Args.OUTPUT, "filename.log");
        expect.put(Args.MASK_SEARCH, "");
        expect.put(Args.REGEX_SEARCH, "");
        expect.put(Args.SEARCH_PATTERN, "*.txt");
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

    @Test
    public void whenAbsentMandatoryParameter() {
        String un = "-o";
        exception.expect(IllegalArgumentException.class);
        exception.expectMessage("Error: not found mandatory parameter: " + un);
        String[] args = {"-d", "/test/path", "-m", "-r","-n", "*.txt"};
        ArgsParser.parse(args);
    }

    @Test
    public void whenTooManyOptions() {
        exception.expect(IllegalArgumentException.class);
        exception.expectMessage("Error: to many options");
        String[] args = {"-d", "/test/path", "-m", "-r","-n", "*.txt", "-o", "output.txt"};
        ArgsParser.parse(args);
    }
}