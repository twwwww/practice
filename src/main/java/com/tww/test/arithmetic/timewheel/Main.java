package com.tww.test.arithmetic.timewheel;

import org.apache.commons.lang3.RandomUtils;

import java.util.stream.IntStream;

public class Main {
    public static void main(String[] args) {
        Timer timer = Timer.getInstance();
        IntStream.range(0,5_000_000).forEach(item -> {
            long delay = RandomUtils.nextInt(0, item);
            timer.addTask(new TimedTask(delay,
                    () -> System.out.println("Delay Task Run === index:" + item + ",Delay Time :" + delay)));
        });
    }
}
