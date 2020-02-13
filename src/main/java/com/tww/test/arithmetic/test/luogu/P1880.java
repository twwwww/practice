package com.tww.test.arithmetic.test.luogu;

import java.util.Scanner;

public class P1880 {

    /**
     *
     * dp[i,j] 表示i-j内获取的最大分数，
     * dp[i,j]  = max(dp[i,i+k],dp[i+k+1,j]) (i<=k<=j)
     *
     * 断环为链：将长度为n的链复制一份接在后面，环的情况就是长度为2n的链中任意连续的长度为n的链,遍历间隔为n的区间取最大值。
     *
     * Base Case:
     * dp[m,m] = stone[m]
     * dp[m,m + 1] = stone[m] + stone[m + 1]
     *
     * 递推方式：
     * i （0-N） j（0-N） k 递推时会出现前面的状态还未计算
     *
     *
     * 斜着递推
     * 
     * 优化：
     * i = N + 1 后的数据不需要,只需要递推到j = N 时;
     *
     *
     * time: O(mn)
     * space:O(1)
     *
     * @param args
     * @throws Exception
     */
    public static void main(String args[]) throws Exception {
        method1();
    }

    private static void method1() {
        Scanner cin = new Scanner(System.in);
        int N = cin.nextInt();

        int[] stone = new int[2 * N];

        for (int i = 0; i < N; i++) {
            stone[i] = stone[i + N] = cin.nextInt();
        }

        int[][] dp1 = new int[2 * N][2 * N];
        int[][] dp2 = new int[2 * N][2 * N];

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < 2 * N - i; j++) {
                if (i == 0) {
                    dp1[j][i + j] = 0;
                    dp2[j][i + j] = 0;
                } else if (i == 1) {
                    dp1[j][i + j] = stone[j] + stone[i + j];
                    dp2[j][i + j] = stone[j] + stone[i + j];
                } else {
                    int max = Integer.MIN_VALUE;
                    int min = Integer.MAX_VALUE;
                    for (int k = 0; k < i; k++) {
                        max = Integer.max(max,dp1[j][j + k] + dp1[j + k + 1][i + j] + sum(j,i + j,stone));
                        min = Integer.min(min,dp2[j][j + k] + dp2[j + k + 1][i + j] + sum(j,i + j,stone));
                    }
                    dp1[j][i + j] = max;
                    dp2[j][i + j] = min;
                }
            }
        }

        int max = Integer.MIN_VALUE;
        int min = Integer.MAX_VALUE;

        for (int i = 0; i < N; i++) {
            max = Integer.max(max,dp1[i][i + N - 1]);
            min = Integer.min(min,dp2[i][i + N - 1]);
        }

        System.out.println(min);
        System.out.println(max);
    }

    private static int sum(int i,int j,int[] stone) {
        int sum = 0;
        for (int k = i; k <= j; k++) {
            sum += stone[k];
        }
        return sum;
    }
}
