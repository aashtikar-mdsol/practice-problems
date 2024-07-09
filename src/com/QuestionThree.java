package com;

import java.util.HashSet;

public class QuestionThree {

    public static int solution(String letters) {
        var lowerSeen = new HashSet<Character>();
        var upperSeen = new HashSet<Character>();
        var finalSet = new HashSet<Character>();
        var disqualifiedSet = new HashSet<Character>();
        for (char ch : letters.toCharArray()) {
            if (Character.isLowerCase(ch)) {
                lowerSeen.add(ch);
                if (upperSeen.contains(Character.toUpperCase(ch))) {
                    disqualifiedSet.add(ch);
                    finalSet.remove(ch);
                }
            } else {
                upperSeen.add(ch);
                if (lowerSeen.contains(Character.toLowerCase(ch)) && (!disqualifiedSet.contains(Character.toLowerCase(ch)))) {
                    finalSet.add(Character.toLowerCase(ch));
                }
            }
        }
        return finalSet.size();
    }

    public static void main(String[] args) {
        System.out.println(solution("aaAbcCABBc"));
        System.out.println(solution("xyzXYZabcABC"));
        System.out.println(solution("ABCabcAefG"));
    }
}
