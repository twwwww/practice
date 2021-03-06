package com.tww.test.arithmetic.test.luogu;

import java.util.Scanner;

public class P3205 {


    /**
     *
     * 为了在即将到来的晚会上有更好的演出效果，作为 AAA 合唱队负责人的小 A 需要将合唱队的人根据他们的身高排出一个队形。
     * 假定合唱队一共 n 个人，第 i 个人的身高为 h_i米（1000 <= h_i <= 2000）， 并已知任何两个人的身高都不同。假定最终排出的队形是 A 个人站成一排，为了简化问题，小 A
     * 想出了如下排队的方式：
     * 他让所有的人先按任意顺序站成一个初始队形，然后从左到右按以下原则依次将每个人插入最终棑排出的队形中：
     *
     * 第一个人直接插入空的当前队形中。
     *
     * 对从第二个人开始的每个人，如果他比前面那个人高（h 较大），那么将他插入当前队形的最右边。如果他比前面那个人矮（h 较小），那么将他插入当前队形的最左边。
     *
     * 当 n 个人全部插入当前队形后便获得最终排出的队形。
     *
     * 例如，有 6 个人站成一个初始队形，身高依次为 1850, 1900, 1700, 1650, 1800, 1750 那么小 A 会按以下步骤获得最终排出的队形：
     *
     * 1850。
     *
     * 1850, 1900，因为 1900 > 1850。
     *
     * 1700, 1850, 1900，因为 1700 < 1900。
     *
     * 1650, 1700, 1850, 1900，因为 1650 < 1700。
     *
     * 1650, 1700, 1850, 1900, 1800，因为 1800 > 1650。
     *
     * 1750, 1650, 1700, 1850, 1900, 1800，因为 1750 < 1800。
     *
     * 因此，最终排出的队形是 1750, 1650, 1700, 1850, 1900, 1800。
     *
     * 小 A 心中有一个理想队形，他想知道多少种初始队形可以获得理想的队形。
     *
     * 请求出答案对 19650827 取模的值。
     *
     * 输入格式 第一行一个整数 n。 第二行 n 个整数，表示小 A 心中的理想队形。
     *
     * 输出格式 输出一行一个整数，表示答案 mod 19650827 的值。
     *
     * dp[i][j][k] i-j区间内得到的最多方案 （k,0:最后添加在左边，1:最后添加在右边）
     *
     * dp[i][j][0] = h(i) < h(i + 1) ? dp[i + 1][j][0] : 0 + h(i) < h(j) ? dp[i + 1][j][1] : 0
     * dp[i][j][1] = h(j) > h(i) ? dp[i][j - 1][0] : 0 + h(j) > h(j - 1) ? dp[i][j - 1][1] : 0
     *
     * Base Case :
     * dp[i][j][0] = 1，dp[i][j][0] = 0 (i == j)一个人时，只有1中方案，只默认一边为1.
     * dp[i][j][k] = 1 (i + 1 == j)
     *
     * time complexity:n^2
     * space complexity:2n^2
     *
     * 斜方向遍历 （y-x = i） i逐渐增加
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

        int[] h = new int[n];
        for (int i = 0; i < n; i++) {
            h[i] = cin.nextInt();
        }

        int[][][] dp = new int[n][n][2];

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n - i; j++) {
                if (i == 0) {
                    dp[j][j + i][0] = 1;
                    // dp[j][j + i][1] = 1;默认为一个人时从左边进来
                } else {
                    int left = (h[j] < h[j + 1] ? dp[j + 1][j + i][0] : 0)
                            + (h[j] < h[j + i] ? dp[j + 1][j + i][1] : 0);
                    int right = (h[j + i] > h[j + i - 1] ? dp[j][j + i - 1][1] : 0)
                            + (h[j + i] > h[j] ? dp[j][j + i - 1][0] : 0);
                    dp[j][j + i][0] = Math.floorMod(left,19650827);
                    dp[j][j + i][1] = Math.floorMod(right,19650827);
                }
            }
        }

        System.out.println(Math.floorMod(dp[0][n - 1][0] + dp[0][n - 1][1],19650827));

    }



    /**
     *
     * 4
     * 1701 1702 1703 1704
     *
     * 8
     *
     */
}
