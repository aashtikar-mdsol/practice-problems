package com.questionfive;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class BinaryTree {

    private Node root;

    private ExecutorService es = Executors.newCachedThreadPool();

    private void add(Node current, Integer c) {
        if (c < current.getData() && current.getLeft() == null) {
            current.setLeft(new Node(c, null, null));
        } else if (c < current.getData()) {
            add(current.getLeft(), c);
        } else if (c > current.getData() && current.getRight() == null) {
            current.setRight(new Node(c, null, null));
        } else {
            add(current.getRight(), c);
        }
    }

    public void addNode(Integer c) {
        if (root == null) {
            root = new Node(c, null, null);
            return;
        }
        add(root, c);
    }

    public Integer getMax() {
        var current = root;
        while (current.getRight() != null) {
            current = current.getRight();
        }
        return current.getData();
    }

    public Integer calculateDepth(Node current) throws ExecutionException, InterruptedException {
        if (current.getLeft() == null && current.getRight() == null) {
            return 0;
        }
        Callable<Integer> right;
        Callable<Integer> left;
        if (current.getRight() != null) {
            right = () -> 1 + calculateDepth(current.getRight());
        } else {
            right = () -> -1;
        }
        if (current.getLeft() != null) {
            left = () -> 1 + calculateDepth(current.getLeft());
        } else {
            left = () -> -1;
        }
        var leftFuture = es.submit(right);
        var rightFuture = es.submit(left);
        var leftDepth = leftFuture.get();
        var rightDepth = rightFuture.get();
        return Math.max(leftDepth, rightDepth);
    }

    public Integer getDepth() throws ExecutionException, InterruptedException {
        var current = root;
        return calculateDepth(current);
    }

    public void close(){
        es.shutdownNow();
    }
}
