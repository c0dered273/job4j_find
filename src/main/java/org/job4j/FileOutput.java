package org.job4j;

import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.List;

/**
 *
 */
public class FileOutput {
    public static void write(List<Path> list, Path outputFile) throws IOException {
        try (BufferedWriter writer = Files.newBufferedWriter(outputFile, StandardOpenOption.CREATE)) {
            for (Path path : list) {
                writer.write(path.toString());
                writer.write(System.lineSeparator());
            }
        }
    }
}
