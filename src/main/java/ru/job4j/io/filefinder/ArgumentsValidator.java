package ru.job4j.io.filefinder;

import ru.job4j.io.ArgsName;

import java.io.File;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.regex.PatternSyntaxException;

public class ArgumentsValidator {

    private ArgumentsValidator() {

    }

    public static void validate(ArgsName args) {
        File dir = new File(args.get("d"));
        if (!dir.exists() || !dir.isDirectory()) {
            throw new IllegalArgumentException("\"-d=\" argument key must be existed directory.");
        }
        String type = args.get("t");
        if (!"mask".equals(type) && !"name".equals(type) && !"regex".equals(type)) {
            throw new IllegalArgumentException("\"-t=\" argument must be \"mask\", \"name\" or \"regex\" only.");
        }
        String name = args.get("n");
        validateName(type, name);
        String output = args.get("o");
        if (output.isEmpty()) {
            throw new IllegalArgumentException("\"-o=\" argument must contain name of file to write search result");
        }
    }

    private static void validateName(String type, String value) {
        Pattern pattern;
        if (type.equals("mask")) {
            pattern = Pattern.compile("[^a-zA-Z0-9*.?]");
            Matcher matcher = pattern.matcher(value);
            if (matcher.find()) {
                throw new IllegalArgumentException("Wrong file mask. Please, use \"-n=*.?xt\" pattern for this argument");
            }
        } else if (type.equals("name")) {
            pattern = Pattern.compile("[^a-zA-Z0-9.]");
            Matcher matcher = pattern.matcher(value);
            if (matcher.find()) {
                throw new IllegalArgumentException("Wrong argument. Please, use file name for \"-n=\" argument");
            }
        } else {
            try {
                Pattern.compile(value);
            } catch (PatternSyntaxException e) {
                throw new IllegalArgumentException("Wrong argument. Please, use regular expression for \"-n=\" argument");
            }
        }
    }

}