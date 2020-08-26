package org.job4j;

public enum Args {
    ROOT("-d"),
    SEARCH_PATTERN("-n"),
    OUTPUT("-o"),
    MASK_SEARCH("-m"),
    FULL_NAME_SEARCH("-f"),
    REGEX_SEARCH("-r"),
    HELP("-h");


    private final String value;

    Args(String key) {
        this.value = key;
    }

    public String getValue() {
        return value;
    }

    @Override
    public String toString() {
        return "Args{" +
                "value='" + value + '\'' +
                '}';
    }
}
