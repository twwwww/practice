package com.tww.test.jdk.completableFuture;

import java.util.concurrent.CompletableFuture;

public class CompletableFutureTest {
    public static void main(String[] args) {
        test1();
    }

    private static void test1() {
        CompletableFuture<String> cf = CompletableFuture.completedFuture("complete");
        CompletableFuture future = cf.thenApply(String::toUpperCase);
    }
}
