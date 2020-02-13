package com.tww.test.arithmetic.test.luogu;

import com.google.common.collect.Lists;

import java.util.List;
import java.util.Scanner;

public class P1417 {


    /**
     *
     * x,y，p时刻
     * x先: a[x] + a[y] - b[x] * (p + c[x]) - b[y] * (p + c[x] + c[y])
     * y先: a[x] + a[y] - b[x] * (p + c[x] + c[y]) - b[y] * (p + c[y])
     * 要使x先始终大于y先
     * b[y] * c[x] < b[x] * c[y]
     *
     * @param args
     * @throws Exception
     */
    // todo
    public static void main(String args[]) throws Exception {
        Scanner cin = new Scanner(System.in);
        int T = cin.nextInt(), n = cin.nextInt();

        int[] arrA = new int[n];
        int[] arrB = new int[n];
        int[] arrC = new int[n];

        for (int i = 0; i < n; i++) {
            arrA[i] = cin.nextInt();
        }
        for (int i = 0; i < n; i++) {
            arrB[i] = cin.nextInt();
        }
        for (int i = 0; i < n; i++) {
            arrC[i] = cin.nextInt();
        }

        List<Food> foods = Lists.newArrayList();
        for (int i = 0; i < n; i++) {
            Food food = new Food();
            food.a = arrA[i];
            food.b = arrB[i];
            food.c = arrC[i];
            foods.add(food);
        }

        foods.sort((f1,f2) -> Math.toIntExact(f2.b * f1.c - f2.c * f1.b));

        int[] dp = new int[T];

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < T; j++) {
                if (i == 0) {

                }
            }
        }

        System.out.println();
    }

    private static class Food {
        private long a;
        private long b;
        private long c;
    }
}
