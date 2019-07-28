package com.tww.test.arithmetic.timer;

import java.util.concurrent.TimeUnit;

public class SimpleTimerTask implements TimerTask {

    private Long time = 0L;

    public SimpleTimerTask(Long time) {
        this.time = time;
    }

    @Override
    public long getDelay(TimeUnit unit) {
        return unit.convert(time - System.currentTimeMillis(), TimeUnit.SECONDS);
    }

    @Override
    public void execute() {
        System.out.println("SimpleTimerTask Execute:" + time);
    }
}
