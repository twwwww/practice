package com.tww.test.arithmetic.test.luogu;

import java.util.Scanner;

public class P2758 {


    /**
     *
     * Base Case: dp[0,b] = b; dp[a,0] = a dp[0,0] = 0;
     *
     * @param args
     * @throws Exception
     */
    public static void main(String args[]) throws Exception {
        Scanner cin = new Scanner(System.in);
        String a = cin.next(), b = cin.next();

        char[] arrA = a.toCharArray();
        char[] arrB = b.toCharArray();

        int[][] dp = new int[arrA.length + 1][arrB.length + 1];

        for (int i = 0; i < a.length() + 1; i++) {
            for (int j = 0; j < b.length() + 1; j++) {
                if (i == 0) {
                    dp[i][j] = j;
                } else if (j == 0) {
                    dp[i][j] = i;
                } else {
                    if (arrA[i - 1] == arrB[j - 1]) {
                        dp[i][j] = dp[i - 1][j - 1];
                    } else {
                        dp[i][j] =
                                Integer.min(dp[i][j] = Integer.min(dp[i][j - 1], dp[i - 1][j]), dp[i - 1][j - 1]) + 1;
                    }
                }
            }
        }

        System.out.println(dp[a.length()][b.length()]);
    }
}
