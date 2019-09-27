package com.tww.test.arithmetic.test.luogu;

import java.util.Scanner;

public class P1164 {

    /**
     *
     *
     * 第N件物品，剩余M元，选与不选能达到的最大方案
     * 
     * dp[N][M] = dp[N - 1][M] + dp[N - 1][M - price[N]](M > price[N])
     * dp[N][M] = dp[N - 1][M] + 1(M = price[N])
     * dp[N][M] = dp[N - 1][M](M < price[N])
     * 
     * 
     * Base Case:
     * dp[0][ 0 - M] = (1:M = price[0] ,0:其他);
     * dp[0 - N][0] = 1;
     *
     * @param args
     * @throws Exception
     */
    public static void main(String args[]) throws Exception {
        Scanner cin = new Scanner(System.in);
        int N = cin.nextInt(), M = cin.nextInt();

        int[] prices = new int[N];

        for (int i = 0; i < N; i++) {
            prices[i] = cin.nextInt();
        }

        int[][] dp = new int[N][M + 1];

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M + 1; j++) {
                if (i == 0) {
                    if (j == prices[i] || j == 0) {
                        dp[i][j] = 1;
                    } else {
                        dp[i][j] = 0;
                    }
                } else if (j == 0) {
                    dp[i][j] = 1;
                } else {
                    if (j >= prices[i]) {
                        dp[i][j] = dp[i - 1][j] + dp[i - 1][j - prices[i]];
                    } else {
                        dp[i][j] = dp[i - 1][j];
                    }
                }
            }
        }
        System.out.println(dp[N - 1][M]);
    }
}
