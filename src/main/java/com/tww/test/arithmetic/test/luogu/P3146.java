package com.tww.test.arithmetic.test.luogu;

import java.util.Scanner;

public class P3146 {


    /**
     *
     * 给定一个1*n的地图，在里面玩2048，每次可以合并相邻两个（数值范围1-40），问最大能合出多少。注意合并后的数值并非加倍而是+1，例如2与2合并后的数值为3。
     *
     * 输入格式 The first line of input contains N, and the next N lines give the sequence
     *
     * of N numbers at the start of the game.
     *
     * 输出格式 Please output the largest integer Bessie can generate.
     *
     *
     * solution1:
     *
     * dp[i][j][k] (k = 0，左端,k = 1,右端)i,j区域两端能够合成的最大值
     *
     * dp[i][j][0] = dp[i + 1][j][0] == v[i] ? v[i] + 1 :v[i] //合成新值后还需要继续往前合成
     * dp[i][j][1] = dp[i][j - 1][1] == v[j] ? v[j] + 1 :v[j]
     *
     * Base Case:
     * dp[i][j][0] = dp[i][j][1] = v[i] (i == j)
     *
     * time complexity : n^2
     * space complexity : 2n^2
     *
     * todo 解法有问题
     *
     *
     *
     * solution2:
     *
     * dp[i][j], i-j区间合成最大值(仅代表合成得到的最大值，不包含单独的数字最大值,故结果的最大值需要在dp过程中获得)
     *
     * dp[i][j] = Max(dp[i][k] == dp[k + 1][j] ? dp[i,k] + 1 : 0)  i =< k < j
     * 结果需要在dp过程中获得，因为dp[0][n]可能为0
     *
     * Base Case :
     * dp[i][i] = v[i]
     *
     * time complexity : n^3
     * space complexity : n^2
     *
     *
     * @param args
     * @throws Exception
     */
    public static void main(String args[]) throws Exception {
        test2();
    }



    // WA
    private static void test1() {
        Scanner cin = new Scanner(System.in);
        int n = cin.nextInt();

        int[] v = new int[n];
        for (int i = 0; i < n; i++) {
            v[i] = cin.nextInt();
        }

        int[][][] dp = new int[n][n][2];

        int max = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n - i; j++) {
                if (i == 0) {
                    dp[j][j + i][0] = v[i];
                    dp[j][j + i][1] = v[i];
                } else {
                    dp[j][j + i][0] = v[j] == dp[j + 1][j + i][0] ? v[j] + 1 : v[j];
                    dp[j][j + i][1] = v[j + i] == dp[j][j + i - 1][1] ? v[j + i] + 1 : v[j + i];
                }
                max = Integer.max(Integer.max(dp[j][j + i][0],dp[j][j + i][1]),max);
            }
        }

        System.out.println(max);
    }


    // AC
    private static void test2() {
        Scanner cin = new Scanner(System.in);
        int n = cin.nextInt();

        int[] v = new int[n];
        for (int i = 0; i < n; i++) {
            v[i] = cin.nextInt();
        }

        int[][] dp = new int[n][n];

        int max = 0;

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n - i; j++) {
                if (i == 0) {
                    dp[j][j + i] = v[j];
                } else {
                    for (int k = j; k < j + i; k++) {
                        if (dp[j][k] == dp[k + 1][j + i]) {
                            dp[j][j + i] = Integer.max(dp[j][j + i],dp[j][k] + 1);
                        }
                    }
                }
                max = Integer.max(max,dp[j][j + i]);
            }
        }



        System.out.println(max);
    }



    /**
     *
     *
     * 4
     * 1
     * 1
     * 1
     * 2
     *
     * 3
     *
     *
     * 3 2 1 1 2 3
     *
     */
}
