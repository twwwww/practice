package com.tww.test.jdk.reactive_stream;

import java.util.Objects;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.SubmissionPublisher;
import java.util.stream.IntStream;

public class NumberPrinter {

    public static void main(String[] args) {
        SubmissionPublisher<Integer> publisher = new SubmissionPublisher();
        CompletableFuture<Void> future = null;
        try (publisher) {
            future = publisher.consume(System.out::println);
            IntStream.range(0, 10).forEach(publisher::submit);
        }
        if (Objects.nonNull(future)) {
            try {
                future.get();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
