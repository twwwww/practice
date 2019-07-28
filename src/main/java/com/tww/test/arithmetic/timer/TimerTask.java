package com.tww.test.arithmetic.timer;

import java.util.concurrent.TimeUnit;

public interface TimerTask {
    long getDelay(TimeUnit unit);

    void execute();
}
