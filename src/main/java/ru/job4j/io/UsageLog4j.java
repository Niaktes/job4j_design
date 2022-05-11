package ru.job4j.io;

import org.slf4j.LoggerFactory;
import org.slf4j.Logger;

public class UsageLog4j {

    private static final Logger LOG = LoggerFactory.getLogger(UsageLog4j.class.getName());

    public static void main(String[] args) {
        char c = 'c';
        byte b = 62;
        short s = 22;
        int i = 1;
        long l = 123;
        float f = 1.1f;
        double d = 1.0d;
        boolean bool = true;

        LOG.debug("char = {}, byte = {}, short = {}, int = {}, long = {}, float = {}, double = {}, boolean = {}",
                c, b, s, i, l, f, d, bool);
    }

}
