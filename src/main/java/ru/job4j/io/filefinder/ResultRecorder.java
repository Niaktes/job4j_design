package ru.job4j.io.filefinder;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.nio.file.Path;
import java.util.List;

public class ResultRecorder {

    private static final Logger LOG = LoggerFactory.getLogger(ResultRecorder.class.getName());

    public void recordSearchResult(List<Path> results, String fileName) throws FileNotFoundException {
        if (results.isEmpty()) {
            LOG.info("Files not found. Please, check arguments.");
        } else {
            File outputFile = new File(fileName);
            try (PrintWriter out = new PrintWriter(
                    new BufferedOutputStream(
                            new FileOutputStream(outputFile)))) {
                results.forEach(out::println);
            }
        }
    }

}
