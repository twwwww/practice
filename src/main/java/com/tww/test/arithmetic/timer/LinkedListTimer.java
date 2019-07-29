package com.tww.test.arithmetic.timer;

import java.util.LinkedList;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class LinkedListTimer implements Timer {

    private LinkedList<TimerTask> tasks = new LinkedList<>();

    @Override
    public void schedule(TimerTask task) {
        for (int i = 0; i < tasks.size(); i++) {
            if (tasks.get(i).getDelay(TimeUnit.SECONDS) > task.getDelay(TimeUnit.SECONDS)) {
                tasks.add(i, task);
                return;
            }
        }
        tasks.addLast(task);
    }

    @Override
    public void cancel(TimerTask task) {
        tasks.removeIf(timerTask -> timerTask.equals(task));
    }

    @Override
    public void expire() {
        if (!tasks.isEmpty()) {
            TimerTask first = tasks.getFirst();
            if (first.getDelay(TimeUnit.SECONDS) <= 0L) {
                first.execute();
                tasks.removeFirst();
            }
        }
    }

    /**
     */
    public void turn() {
        Executors.newSingleThreadExecutor().execute(() -> {
            while (true) {
                this.expire();
            }
        });
    }


    public static void main(String[] args) {
        LinkedListTimer timer = new LinkedListTimer();
        timer.schedule(new SimpleTimerTask(System.currentTimeMillis() + 1000L));
        timer.schedule(new SimpleTimerTask(System.currentTimeMillis() + 5000L));
        timer.turn();
        timer.schedule(new SimpleTimerTask(System.currentTimeMillis() + 3000L));
    }
}
