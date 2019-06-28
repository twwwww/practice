package com.tww.test.utils;

import java.util.concurrent.DelayQueue;
import java.util.concurrent.TimeUnit;

public class DelayUtils {
    private static DelayQueue delayQueue = new DelayQueue();

    public static void sleep(Long timeout) {
        sleep(timeout,TimeUnit.SECONDS);
    }

    public static void sleep(Long timeout, TimeUnit unit) {
        try {
            delayQueue.poll(timeout,unit);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
