package com;

import java.util.HashMap;
import java.util.regex.Pattern;

public class QuestionTwo {

    private static HashMap<String, Integer> cache = new HashMap<>();

    private static boolean isValidFormat(String input) {
        String regex = "^(A+|B+|A+B+)$";
        var pattern = Pattern.compile(regex);
        return pattern.matcher(input).matches();
    }

    private static int solutionDynamic(String input, int current, String constructed) {
        var key = current + ":" + constructed;
        if (cache.containsKey(key)) {
            return cache.get(key);
        }
        if (current >= input.length()) {
            if (isValidFormat(constructed)) {
                return 0;
            } else {
                return 100000;
            }
        }
        var excludingCurrent = 1 + solutionDynamic(input, current + 1, constructed);
        var includingCurrent = solutionDynamic(input, current + 1, constructed + input.charAt(current));
        var optimalSolution = Math.min(excludingCurrent, includingCurrent);
        cache.put(key, optimalSolution);
        return optimalSolution;
    }

    public static void main(String[] args) {
        System.out.println(solutionDynamic("BAAABAB", 0, ""));
        cache = new HashMap<>();
        System.out.println(solutionDynamic("BBABAA", 0, ""));
        cache = new HashMap<>();
        System.out.println(solutionDynamic("AABBBB", 0, ""));
    }
}