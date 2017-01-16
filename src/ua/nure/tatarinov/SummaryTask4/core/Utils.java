package ua.nure.tatarinov.SummaryTask4.core;

import org.apache.log4j.Logger;

public class Utils {

    public static final Logger LOG = Logger.getLogger(Utils.class);

    public static boolean isDigit(String string) {
        if (string == null || string.length() == 0) return false;

        int i = 0;
        if (string.charAt(0) == '-') {
            if (string.length() == 1) {
                return false;
            }
            i = 1;
        }

        char c;
        for (; i < string.length(); i++) {
            c = string.charAt(i);
            if (!(c >= '0' && c <= '9')) {
                return false;
            }
        }
        return true;
    }

}
