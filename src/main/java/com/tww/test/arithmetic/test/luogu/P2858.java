package com.tww.test.arithmetic.test.luogu;

import java.util.Scanner;

public class P2858 {


    /**
     *
     * The first treat is sold on day 1 and has age a=1. Each subsequent day increases the age by 1.
     *
     * 约翰经常给产奶量高的奶牛发特殊津贴，于是很快奶牛们拥有了大笔不知该怎么花的钱．为此，约翰购置了N(1≤N≤2000)份美味的零食来卖给奶牛们．每天约翰售出一份零食．当然约翰希望这些零食全部售出后能得到最大的收益．这些零食有以下这些有趣的特性：
     *
     * •零食按照1．．N编号，它们被排成一列放在一个很长的盒子里．盒子的两端都有开口，约翰每
     *
     * 天可以从盒子的任一端取出最外面的一个．
     *
     * •与美酒与好吃的奶酪相似，这些零食储存得越久就越好吃．当然，这样约翰就可以把它们卖出更高的价钱．
     *
     * •每份零食的初始价值不一定相同．约翰进货时，第i份零食的初始价值为Vi(1≤Vi≤1000)．
     *
     * •第i份零食如果在被买进后的第a天出售，则它的售价是vi×a．
     *
     * Vi的是从盒子顶端往下的第i份零食的初始价值．约翰告诉了你所有零食的初始价值，并希望你能帮他计算一下，在这些零食全被卖出后，他最多能得到多少钱．
     *
     * 输入格式
     * Line 1: A single integer, N
     *
     * Lines 2..N+1: Line i+1 contains the value of treat v(i)
     *
     * 输出格式
     * Line 1: The maximum revenue FJ can achieve by selling the treats
     *
     *
     * 每个商品存在一天产生一次价值
     *
     * 剩余i，j区域内商品时确定获得的最大收益
     * dp[i][j] = Max(dp[i - 1][j] + 剩余商品一天的价值，dp[i][j + 1] + 剩余商品一天的价值)
     *
     * Base Case :
     * dp[0][n - 1] = p[n - 1]
     *
     * 预处理：用前缀和O(1)找到单位天的收益和。 令p[i]为商品价值的前缀和数组
     *
     * time complexity : n + n^2 + n
     * space complexity : n + n^2
     *
     * @param args
     * @throws Exception
     */
    public static void main(String args[]) throws Exception {
        test1();
    }



    private static void test1() {
        Scanner cin = new Scanner(System.in);
        int n = cin.nextInt();

        int[] v = new int[n];
        for (int i = 0; i < n; i++) {
            v[i] = cin.nextInt();
        }

        if (n == 1) {
            System.out.println(v[0]);
        }

        int[] p = new int[n];

        int sum = 0;
        for (int i = 0; i < n; i++) {
            sum += v[i];
            p[i] = sum;
        }

        int[][] dp = new int[n][n];

        dp[0][n - 1] = p[n - 1];
        for (int i = n - 2; i >= 0; i--) {
            for (int j = 0; j < n - i; j++) {
                dp[j][j + i] = Integer.max( (j + i + 1 < n ? dp[j][j + i + 1] : 0),(j > 0 ? dp[j - 1][j + i] : 0))
                        + p[j + i] - (j > 0 ? p[j - 1] : 0);
            }
        }

        int max = 0;

        for (int i = 0; i < n; i++) {
            max = Integer.max(max,dp[i][i]);
        }

        System.out.println(max);

    }



    /**
     *
     * 5
     *
     * 1
     * 3
     * 1
     * 5
     * 2
     *
     * 43
     *
     */
}
