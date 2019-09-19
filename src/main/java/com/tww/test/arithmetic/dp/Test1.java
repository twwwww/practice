package com.tww.test.arithmetic.dp;

public class Test1 {

    /**
     * 有一种玻璃杯质量确定但未知，需要检测。
     * 有一栋100层的大楼，该种玻璃杯从某一层楼扔下，刚好会碎。
     * 现给你两个杯子，问怎样检测出这个杯子的质量，即找到在哪一层楼刚好会碎？
     *
     *
     * x杯子,y层数  (1 <= x,y)
     * dp(x,y)最少尝试次数
     * k从第y层扔下
     * 2中情况：1)碎了：dp(x-1,y-1)  2)没碎：dp(x,y-1)
     * 取两种情况中最大值
     * 遍历从1-y层扔下的情况，找到区间中最小值即为最少尝试次数
     * dp(x,y) = Min(Max(dp(x - 1,y - k),dp(x,y - k)) + 1)  (1 <= k <= y)
     * Base Case:
     * dp(1,y) = y - 1
     * dp(x,1) = 0
     *
     * O(n^3) O(n^2)
     *
     * @param args
     */
    public static void main(String[] args) {
        int cup = 3;
        int floor = 100;
        int[][] dp = new int[cup + 1][floor + 1];
        for (int i = 1; i < cup + 1; i++) {
            for (int j = 1; j < floor + 1; j++) {
                if ( i == 1) {
                    dp[i][j] = j - 1;
                } else if (j == 1) {
                    dp[i][j] = 0;
                } else {
                    int minSum = Integer.MAX_VALUE;
                    for (int k = 1; k <= j; k++) {
                        int maxSum = Integer.max(dp[i - 1][k - 1],dp[i][j - k]);
                        minSum = Integer.min(minSum,maxSum);
                    }
                    dp[i][j] = minSum + 1;
                }
            }
        }
        System.out.println(dp[cup][floor]);
    }
}
