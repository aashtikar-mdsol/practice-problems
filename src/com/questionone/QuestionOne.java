package com.questionone;


import java.util.HashMap;

public class QuestionOne {

    public static void main(String[] args) {
        int[] inputOne = new int[]{130, 191, 200, 10};
        System.out.println(solution(inputOne));

        int[] inputTwo = new int[]{405, 45, 300, 300};
        System.out.println(solution(inputTwo));

        int[] inputThree = new int[]{50, 222, 49, 52, 25};
        System.out.println(solution(inputThree));

        int[] inputFour = new int[]{30, 909, 3190, 99, 3990, 9009};
        System.out.println(solution(inputFour));
    }

    private static int solution(int[] input) {
        var map = new HashMap<CustomKey, Integer>();
        int max = -1;
        for (int current : input) {
            var customKey = new CustomKey(current + "");
            if (map.containsKey(customKey)) {
                var previousInt = map.get(customKey);
                if (previousInt + current > max) {
                    max = previousInt + current;
                }
            } else {
                map.put(customKey, current);
            }
        }
        return max;
    }
}
