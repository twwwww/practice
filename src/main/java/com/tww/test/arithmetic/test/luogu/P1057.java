package com.tww.test.arithmetic.test.luogu;

import java.util.Arrays;
import java.util.Comparator;
import java.util.Scanner;

public class P1057 {

    /**
     *上体育课的时候，小蛮的老师经常带着同学们一起做游戏。这次，老师带着同学们一起做传球游戏。
     *
     * 游戏规则是这样的：nn个同学站成一个圆圈，其中的一个同学手里拿着一个球，当老师吹哨子时开始传球，每个同学可以把球传给自己左右的两个同学中的一个（左右任意），当老师再次吹哨子时，传球停止，此时，拿着球没有传出去的那个同学就是败者，要给大家表演一个节目。
     *
     * 聪明的小蛮提出一个有趣的问题：有多少种不同的传球方法可以使得从小蛮手里开始传的球，传了mm次以后，又回到小蛮手里。两种传球方法被视作不同的方法，当且仅当这两种方法中，接到球的同学按接球顺序组成的序列是不同的。比如有三个同学11号、22号、33号，并假设小蛮为11号，球传了33次回到小蛮手里的方式有11->22->33->11和11->33->22->11，共22种。
     *
     * dp[i][j] 传递i次到达j位置的方案数
     *
     * @param args
     * @throws Exception
     */
    public static void main(String args[]) throws Exception {

        Scanner cin = new Scanner(System.in);
        int n = cin.nextInt();
        int m = cin.nextInt() - 1;



        // 空间可优化为n，滚动数组
        int[][] dp = new int[m][n];

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (i == 0) {
                    dp[i][j] = j == 0 ? 1 : 0;
                } else {
                    // 从左边((j - 1 + n) % n)(防止下标为0的左边)和右边((j + 1) % n)（防止下标为n - 1的右边）转移而来
                    dp[i][j] = dp[i - 1][(j - 1 + n) % n] + dp[i - 1][(j + 1) % n];
                }
            }
        }

        System.out.println(dp[m - 1][0]);
    }
}
