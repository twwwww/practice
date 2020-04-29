package com.tww.test.arithmetic.test.luogu;

import java.util.Scanner;

public class P1077 {


    /**
     *
     * 小明的花店新开张，为了吸引顾客，他想在花店的门口摆上一排花，共m盆。通过调查顾客的喜好，小明列出了顾客最喜欢的n种花，从1到n标号。为了在门口展出更多种花，规定第i种花不能超过a * i ​ 盆，摆花时同一种花放在一起，且不同种类的花需按标号的从小到大的顺序依次摆列。
     *
     * 试编程计算，一共有多少种不同的摆花方案。
     *
     * 输入格式 第一行包含两个正整数n和m，中间用一个空格隔开。
     *
     * 第二行有n个整数，每两个整数之间用一个空格隔开，依次表示a1,a2,…,an。
     *
     * 输出格式 一个整数，表示有多少种方案。注意：因为方案数可能很多，请输出方案数对1000007取模的结果。
     *
     *
     * dp[i,j]前i种花j盆位置能放置的最大数量
     *
     * dp(i,j) = Sum(dp(i-1,j - x))  0<=x<=a[i]
     *
     * Base Case:
     * dp[0][j] = 1 j <= a[0];
     * dp[0][j] = 0 j > a[0];
     * dp[i][0] = 1;
     *
     * time complexity : nm(ai)
     * space complexity: nm
     *
     * 优化space,滚动数组 m
     * @param args
     * @throws Exception
     */
    public static void main(String args[]) throws Exception {
        test2();
    }

    private static void test1() {
        Scanner cin = new Scanner(System.in);
        int n = cin.nextInt(), m = cin.nextInt();

        int[] arrN = new int[n];
        for (int i = 0; i < n; i++) {
            arrN[i] = cin.nextInt();
        }

        long[][] dp = new long[n][m + 1];

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m + 1; j++) {
                if (i == 0) {
                    dp[i][j] = j > arrN[i] ? 0 : 1;
                } else {
                    int start = Integer.max(0, j - arrN[i]);
                    long result = 0;
                    for (int k = start; k <= j; k++) {
                        result += dp[i - 1][k];
                    }
                    dp[i][j] = Math.floorMod(result,1000007);
                }
            }
        }

        System.out.println(dp[n - 1][m]);
    }

    private static void test2() {
        Scanner cin = new Scanner(System.in);
        int n = cin.nextInt(), m = cin.nextInt();

        int[] arrN = new int[n];
        for (int i = 0; i < n; i++) {
            arrN[i] = cin.nextInt();
        }

        long[] dp = new long[m + 1];

        for (int i = 0; i < n; i++) {
            for (int j = m; j >= 0; j--) {
                if (i == 0) {
                    dp[j] = j > arrN[i] ? 0 : 1;
                } else {
                    int start = Integer.max(0, j - arrN[i]);
                    long result = 0;
                    for (int k = start; k <= j; k++) {
                        result += dp[k];
                    }
                    dp[j] = Math.floorMod(result,1000007);
                }
            }
        }

        System.out.println(dp[m]);
    }


    /**
     *
     * 2 4
     * 3 2
     *
     *
     * 2
     *
     *
     *
     */
}
