package com.tww.test.arithmetic.dp.pressure_dp;

import java.util.Arrays;

public class PressureDPTest1 {
    /**
     * 在N×N的棋盘里面放K个国王，使他们互不攻击，共有多少种摆放方案。国王能攻击到它上下左右，以及左上左下右上右下八个方向上附近的各一个格子，共8个格子。
     *
     *
     * 假设一行棋子排列状态以二进制标识 k = 1010 , m(k)表示此种状态下使用棋子个数
     * dp(i,k,s)表示前i行，第i行棋子排列状态为k时，前i行使用棋子数为s时的方案数
     *
     * 状态转移:
     * dp(i,k,s) = Sum(dp(i-1,j,s-m(k))) (j为枚举所有i-1行，使用棋子数为s-m(k)的排列状态.)
     *
     * 预处理k的状态：
     * 同一行中：二进制数1001不能有两个相邻的1
     * 相邻行：使用位运算（&,同一位有都为1时值大于1）
     * k & j < 0
     * k & (j << 1) < 0
     * k & (j >> 1) < 0
     *
     * Base Case:
     * dp(0,k,s) = 1
     *
     */
    public static void main(String[] args) {
        int N = 8;
        int K = 0;
        int[][][] dp = new int[N][(1 << N) - 1][K];
        for (int i = 0; i < dp.length; i++) {
            for (int j = 0; j < dp[0].length; j++) {
                for (int k = 0; k < dp[0][0].length; k++) {
                    int countJ = get1Count(j);
                    if (i == 0) {
                        dp[i][j][k] = 1;
                    } else if (countJ > k || is1Adjacent(k)) {
                        dp[i][j][k] = 0;
                    } else {
                        int sum = 0;
                        for (int l = 0; l < dp[0].length; l++) {
                            int countL = get1Count(l);
                            if (!is1Adjacent(l) && !isLineAdjacent(j,l) && countL <= k - countJ) {
                                sum += dp[i - 1][l][k - countJ];
                            }
                        }
                        dp[i][j][k] = sum;
                    }
                }
            }
        }


        Integer count = Arrays.stream(dp[N - 1])
                .flatMap(ints -> Arrays.stream(ints).boxed())
                .reduce(Integer::sum).orElse(0);

        System.out.println(count);
    }

    private static int get1Count(int number) {
        int count = 0;
        while (number > 0) {
            if ((number & 1) == 1) {
                ++count;
            }
            number = number >> 1;
        }
        return count;
    }

    private static boolean is1Adjacent(int number) {
        return isLeftAdjacent(number,number) || isRightAdjacent(number,number);
    }

    private static boolean isLineAdjacent(int line1,int line2) {
        return isTopAdjacent(line1,line2) || isLeftAdjacent(line1,line2) || isRightAdjacent(line1,line2);
    }

    private static boolean isTopAdjacent(int number1,int number2) {
        return (number1 & number2) > 0;
    }

    private static boolean isLeftAdjacent(int number1,int number2) {
        return (number1 & (number2 >> 1)) > 0;
    }

    private static boolean isRightAdjacent(int number1,int number2) {
        return (number1 & (number2 << 1)) > 0;
    }
}
