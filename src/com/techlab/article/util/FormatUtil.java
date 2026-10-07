package com.techlab.article.util;

public final class FormatUtil {
    private FormatUtil() {}
    public static String capitalizeString(String chain) {
        if (chain == null || chain.isBlank()) {
            return "";
        }
        chain = chain.toLowerCase().trim();
        String[] words = chain.split("\\s+");
        StringBuilder stringBuilder = new StringBuilder();
        for (String word : words) {
            if (!word.isEmpty()) {
                String firstLetter = word.substring(0, 1).toUpperCase();
                String rest = word.substring(1);
                stringBuilder.append(firstLetter).append(rest).append(" ");
            }
        }
        chain = stringBuilder.toString().trim();
        return chain;
    }
}
