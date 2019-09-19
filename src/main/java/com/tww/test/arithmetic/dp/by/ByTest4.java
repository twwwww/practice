package com.tww.test.arithmetic.dp.by;

import java.util.Arrays;

public class ByTest4 {
    /**
     * 多组数据
     * 两人轮流操作，n轮一循环，给出总石子数和这n轮每次两人能取的石子上限（下限为1）。
     * 取到最后一颗者输。
     * 比如
     * 3 97 8 7 6 5 4 3
     * 表示一循环有三轮，
     * 可取的个数为：
     * 第一轮 先手8 后手7
     * 第二轮 先手6 后手5
     * 第三轮 先手4 后手3
     * 然后三轮每取完的话就进入下次循环。
     *
     * limit[y] 每轮拿石子的限制
     * dp(x,y) x还剩的石子数量,y处于的轮数
     * k拿的石子数量(1<= k <= limit[y])
     * dp(x,y)  win_state:  dp(x - k,y + 1) ( k 取 某一值时，对手变成lose_state，则当前为win_state)
     * dp(x,y)  lose_state:  dp(x - k,y + 1) ( k 取 任一值时，对手变成win_state，则当前为lose_state)
     * Base Case:
     * dp(0,y) = false;
     * dp(1,y) = true;
     *
     * O(n^3) O(n^2)
     */
    public static void main(String[] args) {
        int stone = 253;
        int[] limits = new int[]{9,7,6,5,4,3};
        boolean[][] dp = new boolean[stone + 1][limits.length];
        for (int i = 0; i < stone + 1; i++) {
            for (int j = 0; j < limits.length; j++) {
                if (i == 0) {
                    dp[i][j] = false;
                } else if (i == 1) {
                    dp[i][j] = true;
                } else {
                    int limit = limits[j];
                    boolean state = false;
                    for (int k = 1; k <= limit; k++) {
                        if (!dp[i - k][(j + 1) % limits.length]) {
                            state = true;
                            break;
                        }
                    }
                    dp[i][j] = state;
                }
            }
        }
        System.out.println(dp[stone][0]);
    }
}
