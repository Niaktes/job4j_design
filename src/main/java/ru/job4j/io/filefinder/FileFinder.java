package ru.job4j.io.filefinder;

import ru.job4j.io.ArgsName;
import ru.job4j.io.SearchFiles;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.function.Predicate;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class FileFinder {

    private static List<Path> getPaths(ArgsName arguments) throws IOException {
        String type = arguments.get("t");
        String name = arguments.get("n");
        Path root = Path.of(arguments.get("d"));
        Predicate<Path> condition;
        if ("mask".equals(type)) {
            Pattern pattern = Pattern.compile(name.replace('?', '.').replace("*", ".*"));
            condition =  p -> {
                Matcher matcher = pattern.matcher(p.toFile().getName());
                return matcher.find();
            };
        } else if ("name".equals(type)) {
            condition =  p -> p.toFile().getName().equals(name);
        } else {
            Pattern pattern = Pattern.compile(name);
            condition =  p -> {
                Matcher matcher = pattern.matcher(p.toFile().getName());
                return matcher.find();
            };
        }
        SearchFiles searcher = new SearchFiles(condition);
        Files.walkFileTree(root, searcher);
        return searcher.getPaths();
    }

    public static void main(String[] args) {
       if (args.length != 4) {
           throw new IllegalArgumentException("Please use next arguments pattern: \"-d= -n= -t= -o=\".");
       }
       ArgsName arguments = ArgsName.of(args);
       ArgumentsValidator.validate(arguments);
       ResultRecorder recorder = new ResultRecorder();
       try {
           List<Path> paths = getPaths(arguments);
           recorder.recordSearchResult(paths, arguments.get("o"));
       } catch (IOException e) {
           e.printStackTrace();
       }
    }
}