package com;

public class QuestionSix {
    public static int convertAmount(String amount) {
        var cleanedAmount = amount.replaceAll("[$,a-zA-Z/]", "");
        return Integer.parseInt(cleanedAmount);
    }

    public static void main(String[] args) {
        String amount = "$180,240/y";
        int amountInt = convertAmount(amount);
        System.out.println("The amount is: " + amountInt);
    }
}
