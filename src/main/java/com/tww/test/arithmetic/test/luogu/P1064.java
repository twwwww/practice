package com.tww.test.arithmetic.test.luogu;

import java.util.Scanner;

public class P1064 {

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
     * @param args
     * @throws Exception
     */
    public static void main(String args[]) throws Exception {
        Scanner cin = new Scanner(System.in);
        int N = cin.nextInt(), m = cin.nextInt();

        int[] moneys = new int[m];
        int[] ups = new int[m];
        int[] belongs = new int[m]

        for (int i = 0; i < m; i++) {
            moneys[i] = cin.nextInt();
            ups[i] = cin.nextInt();
            belongs[i] = cin.nextInt();
        }
        int[][] dp = new int[N][m];


        System.out.println(dp[N - 1][m - 1]);
    }
}
