package com.tww.test.arithmetic.test.luogu;

import java.util.Scanner;

public class P1508 {

    /**
     * dp[i][j]表示到达i，j位置能够获得的最大值
     *
     * dp[i][j] = Max(dp[i - 1][j],dp[i - 1][j - 1],dp[i - 1][j + 1]) + foods[i][j]
     *
     * Base Case:
     * dp[m][n/2] = foods[m][n/2]
     * 从起点到两边中间无法到达的区域
     * dp[i][j] = -∞
     *
     * 状态只跟前一行有关，使用foods数组即可，不需要从新定义dp数组
     * @param args
     * @throws Exception
     */
    public static void main(String args[]) throws Exception {
        Scanner cin = new Scanner(System.in);
        int m = cin.nextInt();
        int n = cin.nextInt();

        int[][] foods = new int[m][n];

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                foods[i][j] = cin.nextInt();
            }
        }


        for (int i = m - 1; i >= 0; i--) {
            for (int j = 0; j < n; j++) {
                if (i == m - 1) {
                    if (j != n / 2 && j != n / 2 - 1 && j != n / 2 + 1) {
                        foods[i][j] = -1000_000;
                    }
                } else {
                    int max;
                    max = Integer.max(foods[i + 1][j],j - 1 >= 0 ? foods[i + 1][j - 1] : -1000_000) + foods[i][j];
                    max = Integer.max(max,(j + 1 <= n - 1 ? foods[i + 1][j + 1] : -1000_000) + foods[i][j]);
                    foods[i][j] = max;
                }
            }
        }

        int max = 0;

        for (int i = 0; i < n; i++) {
            max = Integer.max(max,foods[0][i]);
        }

        System.out.println(max);
    }
}
