package com.tww.test.arithmetic.timer;

public interface Timer {
    void schedule(TimerTask task);

    void cancel(TimerTask task);

    void expire();
}
