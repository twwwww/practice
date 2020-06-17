package com.tww.test.arithmetic.test.luogu;

import java.util.Scanner;


public class P1439 {


    /**
     *
     * 题目描述 给出 1,2,…,n 的两个排列 P 1 ​ 和 P 2 ​ ，求它们的最长公共子序列。
     *
     * 输入格式 第一行是一个数 n。
     *
     * 接下来两行，每行为 n 个数，为自然数 1,2,…,n 的一个排列。
     *
     * 输出格式 一个数，即最长公共子序列的长度。
     *
     * dp[m,n] = dp[m-1,n-1] + 1 (l1[m] = l2[n])
     * dp[m,n] = Max(dp[m,n-1],dp[m-1,n]) (l1[m] != l2[n])
     *
     * time complexity : mn
     * space complexity : mn
     * -- 50
     *
     *
     *
     * @param args
     * @throws Exception
     */
    public static void main(String args[]) throws Exception {
        test1();
    }

    private static void test1() {
        Scanner cin = new Scanner(System.in);
        int n = cin.nextInt();

        int[] l1 = new int[n];
        int[] l2 = new int[n];
        for (int i = 0; i < n; i++) {
            l1[i] = cin.nextInt();
        }

        for (int i = 0; i < n; i++) {
            l2[i] = cin.nextInt();
        }

        int[][] dp = new int[n + 1][n + 1];//size加1处理边界条件

        for (int i = 0; i < n + 1; i++) {
            for (int j = 0; j < n + 1; j++) {
                if (i == 0 || j == 0) {
                    dp[i][j] = 0;
                } else {
                    if (l1[i - 1] == l2[j - 1]) {
                        dp[i][j] = dp[i - 1][j - 1] + 1;
                    } else {
                        dp[i][j] = Integer.max(dp[i - 1][j],dp[i][j - 1]);
                    }
                }
            }
        }
        System.out.println(dp[n][n]);
    }

/**
 * 5
 * 3 2 1 4 5
 * 1 2 3 4 5
 *
 * 3
 */
}


