package com.questionfive;

import java.util.concurrent.ExecutionException;

public class QuestionFive {

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        var bTree = new BinaryTree();
        bTree.addNode(5);
        bTree.addNode(3);
        bTree.addNode(2);
        bTree.addNode(10);
        bTree.addNode(11);
        System.out.println(bTree.getMax());
        System.out.println(bTree.getDepth());
        bTree.close();
    }
}
