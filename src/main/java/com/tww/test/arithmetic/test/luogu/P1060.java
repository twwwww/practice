package com.tww.test.arithmetic.test.luogu;

import java.util.Scanner;

public class P1060 {

    /**
     *
     *
     * m元时，第k件物品的选择能获得的最大价值
     * dp[m][k] = Max(dp[m][k - 1] ,dp[m - money[k]][k - 1] + money[k] * ups[k])
     * Base Case:
     * dp[0][0 - k] = 0;
     * dp[0 - money[0]][0] = 0
     * dp[money[0] - m][0] = money[0] * ups[0]
     *
     * time: O(mn)
     * space: O(mn)
     *
     *
     * @param args
     * @throws Exception
     */
    public static void main(String args[]) throws Exception {
        Scanner cin = new Scanner(System.in);
        int N = cin.nextInt(), m = cin.nextInt();

        int[] moneys = new int[m];
        int[] ups = new int[m];

        for (int i = 0; i < m; i++) {
            moneys[i] = cin.nextInt();
            ups[i] = cin.nextInt();
        }
        int[][] dp = new int[N + 1][m];

        for (int i = 0; i < N + 1; i++) {
            for (int j = 0; j < m; j++) {
                if (i == 0) {
                    dp[i][j] = 0;
                } else if (j == 0) {
                    if (i < moneys[j]) {
                        dp[i][j] = 0;
                    } else {
                        dp[i][j] = moneys[0] * ups[0];
                    }
                } else {
                    if (i >= moneys[j]) {
                        dp[i][j] = Integer.max(dp[i][j - 1],dp[i - moneys[j]][j - 1] + moneys[j] * ups[j]);
                    } else {
                        dp[i][j] = dp[i][j - 1];
                    }
                }
            }
        }

        System.out.println(dp[N][m - 1]);
    }
}
