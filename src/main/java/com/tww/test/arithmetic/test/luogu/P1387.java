package com.tww.test.arithmetic.test.luogu;

import java.util.Scanner;

public class P1387 {


    /**
     *
     * dp(n,m) 表示以nums[n,m]为右下角的正方形最大变长
     * nums[n,m] = 0 ,dp(n,m) = 0
     * nums[n,m] = 1 ,dp(n,m) = 1 +  Math.min(dp(n,m - 1),dp(n - 1,m),dp(n - 1,m - 1))
     * Base Case:
     *  n = 0 || m = 0 时 dp(n,m) = nums[n,m] == 1 ? 1 : 0
     *
     * 矩形:
     * dp(n,m,k) k = 1,2 表示以nums[n,m]为右下角的矩形形最大长和宽
     *
     * nums[n,m] = 0 ,dp(n,m,k) = 0
     * nums[n,m] = 1 ,dp(n,m,1) = 1 +  Math.min(dp(n - 1,m - 1,1),dp(n,m - 1,1))
     *                dp(n,m,2) = 1 +  Math.min(dp(n - 1,m - 1,2),dp(n - 1,m,2))
     * Base Case:
     * n = 0 , m = 0 时 dp(n,m,k) = nums[n,m] == 1 ? 1 : 0
     * m = 0 dp(n,m,1) = nums[n,m] == 1 ? 1 : 0
     *       dp(n,m,2) = nums[n,m] == 1 ? dp(n - 1,m,2) + 1 : 0
     *
     * @param args
     * @throws Exception
     */
    public static void main(String args[]) throws Exception {
        method1();
    }

    private static void method1() {
        Scanner cin = new Scanner(System.in);
        int n = cin.nextInt(), m = cin.nextInt();

        int[][] nums = new int[n][m];

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                nums[i][j] = cin.nextInt();
            }
        }

        int[][] dp = new int[n][m];
        int max = 0;

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (i == 0 || j == 0) {
                    dp[i][j] = nums[i][j] == 0 ? 0 : 1;
                } else {
                    if (nums[i][j] == 0) {
                        dp[i][j] = 0;
                    } else {
                        dp[i][j] = 1 +  Integer.min(dp[i - 1][j - 1],Integer.min(dp[i - 1][j],dp[i][j - 1]));
                    }
                }
                max = Integer.max(max,dp[i][j]);
            }
        }

        System.out.println(max);
    }

    //todo
    private static void method2() {
        Scanner cin = new Scanner(System.in);
        int n = cin.nextInt(), m = cin.nextInt();

        int[][] nums = new int[n][m];

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                nums[i][j] = cin.nextInt();
            }
        }

        int[][][] dp = new int[n][m][2];
        int max = 0;

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (i == 0 && j == 0) {
                    dp[i][j][0] = nums[i][j] == 0 ? 0 : 1;
                    dp[i][j][1] = nums[i][j] == 0 ? 0 : 1;
                } else if (i == 0) {
                    if (nums[i][j] == 0) {
                        dp[i][j][0] = 0;
                        dp[i][j][1] = 0;
                    } else {
                        dp[i][j][0] = 1 + dp[i][j - 1][0];
                        dp[i][j][1] = 1;
                    }
                } else if (j == 0) {
                    if (nums[i][j] == 0) {
                        dp[i][j][0] = 0;
                        dp[i][j][1] = 0;
                    } else {
                        dp[i][j][0] = 1;
                        dp[i][j][1] = 1 + dp[i - 1][j][1];
                    }
                } else {
                    if (nums[i][j] == 0) {
                        dp[i][j][0] = 0;
                        dp[i][j][1] = 0;
                    } else {
                        dp[i][j][0] = 1 +  Integer.min(dp[i - 1][j - 1][0],dp[i][j - 1][0]);
                        dp[i][j][1] = 1 +  Integer.min(dp[i - 1][j - 1][1],dp[i - 1][j][1]);
                    }
                }
                max = Integer.max(max,dp[i][j][0] * dp[i][j][1]);
            }
        }

        System.out.println(max);
    }
}
