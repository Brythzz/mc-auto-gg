package dev.bryth.autogg.util;

public class NumUtil {
    public static Integer tryParseInt(String number) {
        try {
            return Integer.parseInt(number);
        } catch (NumberFormatException e) {
            return null;
        }
    }
}
