package com.tww.test.code;

public class SynDeepStudy {

    public void test1() {
        synchronized (this) {
            System.out.println("test1");
        }
    }

    public synchronized void test2() {
        System.out.println("test2");
    }

    public static synchronized void test3() {
        System.out.println("test3");
    }
}