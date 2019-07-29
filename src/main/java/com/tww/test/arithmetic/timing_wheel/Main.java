package com.tww.test.arithmetic.timing_wheel;

import java.util.Date;

public class Main {

    public static void main(String[] args) throws InterruptedException {
        SimpleTimeRound timeRound = new SimpleTimeRound();
        Task task1 = () -> System.out.println("task1 ==========>" + new Date());
        Task task2 = () -> System.out.println("task2 ==========>" + new Date());
        timeRound.addTask(task1,1);
        timeRound.addTask(task2,10);
        timeRound.turn();
    }
}
