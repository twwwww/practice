package com.tww.test.arithmetic.timing_wheel;

public class Main {

    public static void main(String[] args) throws InterruptedException {
        SimpleTimeRound timeRound = new SimpleTimeRound();
        Task task1 = () -> System.out.println("11111");
        Task task2 = () -> System.out.println("10");
        timeRound.addTask(task1,1);
        timeRound.addTask(task2,10);
        timeRound.turn();
    }
}
