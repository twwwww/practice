package com.tww.test.arithmetic.timing_wheel;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.DelayQueue;
import java.util.concurrent.Executor;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.stream.IntStream;

public class SimpleTimeRound {
    private List<TimeScale> scales;
    private TimeUnit timeUnit = TimeUnit.SECONDS;
    private Integer timeNum = 60;
    private Executor executor;

    public SimpleTimeRound() {
        init();
    }

    public SimpleTimeRound(TimeUnit timeUnit, Integer timeNum) {
        this.timeUnit = timeUnit;
        this.timeNum = timeNum;
        init();
    }

    private void init() {
        scales = new ArrayList<>();
        IntStream.range(0,timeNum).forEach(num -> {
            TimeScale scale = new TimeScale(num);
            scales.add(scale);
        });
        executor = new ThreadPoolExecutor(20,20,0,TimeUnit.SECONDS,new LinkedBlockingDeque<>());
    }

    public void turn() throws InterruptedException {
        int index = 0;
        while (true) {
            if (index >= timeNum) {
                index = 0;
            }
            System.out.println("now_time:" + new Date());
            TimeScale timeScale = scales.get(index);
            timeScale.scheduleTask(executor);
            index++;
            DelayQueue delayQueue = new DelayQueue();
            delayQueue.poll(1,timeUnit);
        }
    }

    public void addTask(Task task,Integer timeNum) {
        scales.get(timeNum - 1).addTask(task);
    }
}
