package com;

public class QuestionTwo {

    public static int solution(String input) {
        var strLen = input.length();
        var prefixB = new int[strLen + 1];
        var suffixA = new int[strLen + 1];

        for (int i = 0; i < strLen; i++) {
            prefixB[i + 1] = prefixB[i] + (input.charAt(i) == 'B' ? 1 : 0);
        }
        for (int i = strLen - 1; i >= 0; i--) {
            suffixA[i] = suffixA[i + 1] + (input.charAt(i) == 'A' ? 1 : 0);
        }
        int minDeletions = Integer.MAX_VALUE;
        for (int i = 0; i <= strLen; i++) {
            int deletions = prefixB[i] + suffixA[i];
            minDeletions = Math.min(minDeletions, deletions);
        }
        return minDeletions;
    }

    public static void main(String[] args) {
        System.out.println(solution("BAAABAB"));
        System.out.println(solution("BBABAA"));
        System.out.println(solution("AABBBB"));
    }
}
