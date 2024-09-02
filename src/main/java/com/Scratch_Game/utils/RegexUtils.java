package com.Scratch_Game.utils;

import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public final class RegexUtils {

    private RegexUtils() {
    }

    public static Map.Entry<String, Object> getPropertyByRegex(String input, String regex) {
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(input);

        if (!matcher.find()) return null;

        return Map.entry(matcher.group(1), matcher.group(2));
    }
}
