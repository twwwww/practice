package com.tww.test.code;

public class LocalVaraiablesTable {

    public static int ss = 1;
    public static String str = "987";
    public final String aa = "67";
    public static final String bb = "086";

    private void write(int age) {
        String name = "123";
        double d = 1.0;
        int i = 3;
    }

    public static void method(int a) {
        if (a > 2) {
            int age = 18;
        }
    }

    public void addCall() {
        int i = 3;
        add(i);
    }

    public int add(int a) {
        int b = 3;
        int c = a + b;
        return c;
    }

    public static void main(String[] args) {
        int i = 8;
        i = i++;
        int j = 8;
        j = ++j;
        //自增操作是在局部变量表中进行的
        System.out.println(i);
        System.out.println(j);
    }

    public void add() {
        // 第1类问题
        int i1 = 10;
        i1++;
        int i2 = 10;
        ++i2;
        // 第2类问题：
        int i3 = 10;
        int i4 = i3++;
        int i5 = 10;
        int i6 = ++i5;
        // 第3类问题：
        int i7 = 10;
        i7 = i7++;
        int i8 = 10;
        i8 = ++i8;
        // 第4类问题：
        int i9 = 10;
        int i10 =  ++i9 + i9++;
    }


}
