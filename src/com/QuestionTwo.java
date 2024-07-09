package com;

public class QuestionTwo {

    public static int solution(String S) {
        int N = S.length();
        int[] prefix_A = new int[N + 1];
        int[] suffix_B = new int[N + 1];

        // Compute prefix_A
        for (int i = 0; i < N; i++) {
            prefix_A[i + 1] = prefix_A[i] + (S.charAt(i) == 'A' ? 1 : 0);
        }

        // Compute suffix_B
        for (int i = N - 1; i >= 0; i--) {
            suffix_B[i] = suffix_B[i + 1] + (S.charAt(i) == 'B' ? 1 : 0);
        }

        // Calculate the minimum deletions
        int minDeletions = Integer.MAX_VALUE;
        for (int i = 0; i <= N; i++) {
            int deletions = prefix_A[i] + suffix_B[i];
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
