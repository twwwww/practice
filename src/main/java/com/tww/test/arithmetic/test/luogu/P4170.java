package com.tww.test.arithmetic.test.luogu;

import java.util.Scanner;

public class P4170 {


    /**
     *
     * 假设你有一条长度为 5 的木版，初始时没有涂过任何颜色。你希望把它的 5 个单位长度分别涂上红、绿、蓝、绿、红色，用一个长度为 5 的字符串表示这个目标：RGBGR。
     *
     * 每次你可以把一段连续的木版涂成一个给定的颜色，后涂的颜色覆盖先涂的颜色。例如第一次把木版涂成 RRRRR，第二次涂成 RGGGR，第三次涂成 RGBGR，达到目标。
     *
     * 用尽量少的涂色次数达到目标。
     *
     * 输入格式 输入仅一行，包含一个长度为 n 的字符串，即涂色目标。字符串中的每个字符都是一个大写字母，不同的字母代表不同颜色，相同的字母代表相同颜色。
     *
     * 输出格式 仅一行，包含一个数，即最少的涂色次数。
     *
     *
     * dp[i][j] i,j区域内的字段完成需要的最小次数
     *
     * dp[i][j] 可以从dp[i + 1][j]或者dp[i][j - 1]转移而来。
     * dp[i][j] = Min((v[j] == v[i] || v[j] == v[j - 1]) ? dp[i][j - 1] : dp[i][j - 1] + 1,
     *                 (v[i] == v[i + 1] || v[i] == v[j]) ? dp[i + 1][j] : dp[i + 1][j] + 1)
     *
     * Base Case :
     * dp[i][i] = 1
     *
     * time complexity : n^2
     * space complexity: n^2
     *
     * 斜着遍历
     *
     * //todo WA 找不到原因，luogu无用例（找不到反例，奇怪）
     *  @param args
     * @throws Exception
     */
    public static void main(String args[]) throws Exception {
        test1();
    }


    private static void test1() {
        Scanner cin = new Scanner(System.in);
        String str = cin.nextLine();
        int n = str.length();
        char[] v = str.toCharArray();

        int[][] dp = new int[n][n];

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n - i; j++) {
                if (i == 0) {
                    dp[j][j + i] = 1;
                } else {
                    int left = (v[j] == v[j + 1] || v[j] == v[j + i]) ? dp[j + 1][j + i] : dp[j + 1][j + i] + 1;
                    int right = (v[j + i] == v[j] || v[j + i] == v[j + i - 1]) ? dp[j][j + i - 1] : dp[j][j + i - 1] + 1;
                    dp[j][j + i] = Integer.min(left,right);
                }
            }
        }

        System.out.println(dp[0][n - 1]);
    }



    /**
     *
     * AAAAA -> 1
     *
     * RGBGR -> 3
     *
     *
     *
     * cccc
     * aaaacc
     * abbbbc
     *
     *
     */
}
