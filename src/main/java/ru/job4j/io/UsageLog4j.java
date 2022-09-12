package ru.job4j.io;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class UsageLog4j {

    private static final Logger LOG = LoggerFactory.getLogger(UsageLog4j.class.getName());

    public static void main(String[] args) {
        char ch = 'P';
        int in = 33;
        double doubl = 3.4;
        float fl = 4.3f;
        boolean bool = true;
        byte bt = 2;
        short sh = 7;
        long lg = 5L;

        LOG.debug("Char: {}, int: {}, double: {}, float: {}, boolean: {}, byte: {}, short: {}, long: {}", ch, in, doubl, fl, bool, bt, sh, lg);
    }
}