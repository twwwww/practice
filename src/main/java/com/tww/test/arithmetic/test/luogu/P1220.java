package com.tww.test.arithmetic.test.luogu;

import java.util.Scanner;

public class P1220 {


    /**
     *
     * 某一村庄在一条路线上安装了 n 盏路灯，每盏灯的功率有大有小（即同一段时间内消耗的电量有多有少）。老张就住在这条路中间某一路灯旁，他有一项工作就是每天早上天亮时一盏一盏地关掉这些路灯。
     *
     * 为了给村里节省电费，老张记录下了每盏路灯的位置和功率，他每次关灯时也都是尽快地去关，但是老张不知道怎样去关灯才能够最节省电。他每天都是在天亮时首先关掉自己所处位置的路灯，然后可以向左也可以向右去关灯。开始他以为先算一下左边路灯的总功率再算一下右边路灯的总功率，然后选择先关掉功率大的一边，再回过头来关掉另一边的路灯，而事实并非如此，因为在关的过程中适当地调头有可能会更省一些。
     *
     * 现在已知老张走的速度为 1m/s，每个路灯的位置（是一个整数，即距路线起点的距离，单位：m）、功率（W），老张关灯所用的时间很短而可以忽略不计。
     *
     * 请你为老张编一程序来安排关灯的顺序，使从老张开始关灯时刻算起所有灯消耗电最少（灯关掉后便不再消耗电了）。
     *
     * 输入格式 第一行是两个数字 n（表示路灯的总数）和 c（老张所处位置的路灯号）；
     *
     * 接下来 n 行，每行两个数据，表示第 1 盏到第 n 盏路灯的位置和功率。数据保证路灯位置单调递增。
     *
     * 输出格式 一个数据，即最少的功耗（单位：J，1J=1W * s）。
     *
     * dp[i][j][k] i,j区间内灯关掉时耗费的能量，k，0:位于i位置，1:位于j位置
     *
     * dp[i][j][0] = min((dp[i + 1][j][0] + (m[i + 1] - m[i]) * use()),(dp[i + 1][j][1] + (m[j] - m[i]) * use()))
     * dp[i][j][1] = min((dp[i][j - 1][0] + (m[j] - m[i]) * use()),(dp[i][j - 1][1] + (m[j] - m[j - 1]) * use()))
     *
     * use()亮着的灯单位时间耗能
     *
     * 预处理：用前缀和O(1)找到功率之和。 令p[i]为功率的前缀和数组
     *
     *
     *
     * Base Case:
     * dp[c][c][k] = 0;
     * dp[i][j][k] = Integer.MAX (i >c || j< c)
     *
     * dp[i][j] 需要用到 [i + 1]后的数据，斜着遍历。只需要 x<y 的数据
     *
     * time complexity: n + n^2
     * space complexity: n + 2n^2
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
        int c = cin.nextInt() - 1;

        int[] m = new int[n];
        int[] w = new int[n];
        for (int i = 0; i < n; i++) {
            m[i] = cin.nextInt();
            w[i] = cin.nextInt();
        }

        int[] p = new int[n];
        int P = 0;
        for (int i = 0; i < n; i++) {
            P += w[i];
            p[i] = P;
        }


        int[][][] dp = new int[n][n][2];

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n - i; j++) {
                if (i == 0) {
                    if (j != c) {
                        dp[j][j + i][0] = Integer.MAX_VALUE / 2;
                        dp[j][j + i][1] = Integer.MAX_VALUE / 2;
                    } else {
                        dp[j][j + i][0] = 0;
                        dp[j][j + i][1] = 0;
                    }
                } else {
                    int use0 = p[j] + p[n - 1] - p[j + i];
                    int use1 = j == 0 ?  p[n - 1] - p[j + i - 1] : p[j - 1] + p[n - 1] - p[j + i - 1];
                    dp[j][j + i][0] = Integer.min((dp[j + 1][j + i][0] + (m[j + 1] - m[j]) * use0),
                            (dp[j + 1][j + i][1] + (m[i + j] - m[j]) * use0));

                    dp[j][j + i][1] = Integer.min((dp[j][ i + j - 1][0] + (m[i + j] - m[j]) * use1),
                            (dp[j][i + j - 1][1] + (m[i + j] - m[i + j - 1]) * use1));
                }
            }
        }

        System.out.println(Integer.min(dp[0][n - 1][0],dp[0][n - 1][1]));

    }




    /**
     *
     * 5 3
     * 2 10
     * 3 20
     * 5 20
     * 6 30
     * 8 10
     *
     * 3 4 2 1 5 -> 270
     *
     * state:
     *
     *
     */
}
