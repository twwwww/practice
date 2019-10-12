package com.tww.test.arithmetic.test.luogu;

import java.util.Scanner;

public class P1091 {

    /**
     * 两个dp数组，一个以i为结尾的升序列，一个以i开头的降序列。
     * 对于i i1 + i2 最大值为 出列人数最小的情况
     *
     * dp[i] = Max(dp[0-i]) + 1
     *
     * Base Case: dp[x] = 1
     *
     * time:O(n^2)
     * space:O(N)
     *
     * 优化 待补充
     * time: O(nlogn)
     *
     * @param args
     * @throws Exception
     */
    public static void main(String args[]) throws Exception {

        Scanner cin = new Scanner(System.in);
        int N = cin.nextInt();

        int[] heights = new int[N];

        for (int i = 0; i < N; i++) {
            heights[i] = cin.nextInt();
        }

        int[] dp1 = new int[N];
        int[] dp2 = new int[N];
        dp1[0] = dp2[0] = 1;

        for (int i = 1; i < N; i++) {
            int max = 1;
            for (int j = 0; j < i; j++) {
                if (heights[i] > heights[j]) {
                    max = Integer.max(max,dp1[j] + 1);
                }
            }
            dp1[i] = max;
        }

        for (int i = N - 1; i >= 0; i--) {
            int max = 1;
            for (int j = N - 1; j > i ; j--) {
                if (heights[j] < heights[i]) {
                    max = Integer.max(max,dp2[j] + 1);
                }
            }
            dp2[i] = max;
        }

        int minOut = N;
        for (int i = 0; i < N; i++) {
            minOut = Integer.min(minOut,N - dp1[i] - dp2[i] + 1);
        }

        System.out.println(minOut);
    }
}
