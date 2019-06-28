package com.tww.test.jdk.reactive_stream;

import com.tww.test.utils.DelayUtils;
import org.apache.commons.lang3.math.NumberUtils;

import java.util.Objects;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.DelayQueue;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executors;
import java.util.concurrent.Flow.Subscriber;
import java.util.concurrent.Flow.Processor;
import java.util.concurrent.Flow.Publisher;
import java.util.concurrent.Flow.Subscription;
import java.util.concurrent.SubmissionPublisher;
import java.util.concurrent.TimeUnit;
import java.util.stream.IntStream;

public class NumberPrinter {

    /**
     * @param args
     */
    public static void main(String[] args) {
        test2();
    }

    private static void test1() {
        CompletableFuture<Void> future;
        try (SubmissionPublisher<Integer> publisher = new SubmissionPublisher<>()) {
            future = publisher.consume(System.out::println);
            IntStream.range(0, 10).forEach(publisher::submit);
            DelayUtils.sleep(5L);
            publisher.closeExceptionally(new RuntimeException("sdfsf"));
        }
        if (Objects.nonNull(future)) {
            try {
                future.join();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    private static void test2() {
        CustomerSubscriber subscriber = new CustomerSubscriber();

        SubmissionPublisher<String> publisher = new SubmissionPublisher<>(Executors.newFixedThreadPool(10),2);
        publisher.subscribe(subscriber);
        CompletableFuture.runAsync(() -> {
            IntStream.rangeClosed(1, 20).forEach(i -> {
                DelayUtils.sleep(300L, TimeUnit.MILLISECONDS);
                System.out.println("publish:" + i);
//                publisher.submit(i + "s");
                publisher.offer(i + "drop",(sub, s) -> Long.valueOf(s.substring(0, 1)) > 15);
            });
//            publisher.closeExceptionally(new RuntimeException("error end"));
        });

        DelayUtils.sleep(50L);
    }


    private static class CustomerSubscriber implements Subscriber<String> {
        private Subscription subscription;

        @Override
        public void onSubscribe(Subscription subscription) {
            this.subscription = subscription;
            System.out.println("subscribe on publisher");
            subscription.request(100);
        }

        @Override
        public void onNext(String item) {
            System.out.println("receive item from publisher:" + item);
            DelayUtils.sleep(1L);
        }

        @Override
        public void onError(Throwable throwable) {
            System.out.println("ERROR HAPPEND!!!");
            throwable.printStackTrace();
        }

        @Override
        public void onComplete() {
            System.out.println("stream complete!");
        }
    }
}


