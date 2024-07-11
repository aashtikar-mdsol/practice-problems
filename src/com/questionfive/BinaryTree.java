package com.questionfive;

import java.util.ArrayList;
import java.util.concurrent.*;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class BinaryTree {

    private Node root;

    private ExecutorService es = Executors.newCachedThreadPool();

    private Lock maxLock = new ReentrantLock();

    private Integer max = Integer.MIN_VALUE;

    public void construct() {
        root = new Node(5,
                new Node(3,
                        new Node(10, null, null),
                        new Node(11, null, null)
                ),
                new Node(2, null, null)
        );
    }

    private Runnable constructRunnable(Node current) {
        return () -> {
            try {
                findMax(current);
            } catch (InterruptedException | ExecutionException e) {
                throw new RuntimeException(e);
            }
        };
    }

    private void findMax(Node current) throws InterruptedException, ExecutionException {
        try {
            maxLock.lock();
            if (current.getData() > max) {
                max = current.getData();
            }
        } finally {
            maxLock.unlock();
        }
        if (current.getLeft() == null && current.getRight() == null) {
            return;
        }
        var futures = new ArrayList<Future>();
        if (current.getRight() != null) {
            futures.add(es.submit(constructRunnable(current.getRight())));
        }
        if (current.getLeft() != null) {
            futures.add(es.submit(constructRunnable(current.getLeft())));
        }
        for (Future future : futures) {
            future.get();
        }
    }

    public Integer getMax() {

        try {
            var future = es.submit(constructRunnable(root));
            future.get();
        } catch (InterruptedException | ExecutionException e) {
            throw new RuntimeException(e);
        }
        return max;
    }

    private Integer calculateDepth(Node current) throws ExecutionException, InterruptedException {
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

    public void close() {
        es.shutdownNow();
    }
}
