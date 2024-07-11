package com.questionfive;

import java.util.concurrent.ExecutionException;

public class QuestionFive {

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        var bTree = new BinaryTree();
        bTree.construct();
        System.out.println("MAX:" + bTree.getMax());
        System.out.println("Depth:" + bTree.getDepth());
        bTree.close();
    }
}
