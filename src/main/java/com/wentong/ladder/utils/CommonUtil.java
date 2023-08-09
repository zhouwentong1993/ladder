package com.wentong.ladder.utils;

public class CommonUtil {


    public static String makeFirstLetterLowerCase(String input) {
        if (input == null || input.isEmpty()) {
            return input;
        }

        char firstChar = input.charAt(0);
        if (Character.isUpperCase(firstChar)) {
            char lowerFirstChar = Character.toLowerCase(firstChar);
            return lowerFirstChar + input.substring(1);
        }

        return input;
    }

}
