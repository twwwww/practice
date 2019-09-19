package com.tww.test.arithmetic.dp.by;

public class ByTest {
    /**
     * 你和你的朋友面前有一排石头堆，用一个数组 piles 表示，piles[i] 表示第 i 堆石子有多少个。你们轮流拿石头，
     * 一次拿一堆，但是只能拿走最左边或者最右边的石头堆。所有石头被拿完后，谁拥有的石头多，谁获胜。
     *
     * sto[n],dp[i,j][fir],dp[i,j][sed].(i<=n<=j)
     *
     * 状态转移：
     * dp[i,j][fir] = Max(dp[i+1,j][sed] + sto[i],dp[i,j-1][sed] + sto[j])
     * dp[i,j][sed] = choose == left ? dp[i+1,j][fir] : dp[i,j-1][fir]
     * Base Case :
     * dp[n,n] = (sto[n],0)
     *
     * O(n^2),O(n^2)
     * 优化：数组只使用一半，右上部分，可以考虑数组平铺为一维数组
     *
     *
     *
     * ques2:
     * 状态转移:
     * dp[i][j] i-j范围内先手获得最大分数
     *
     * 先手操作后对手所能获取分数的最小值;
     * dp[i][j] = sum(i,j) - Min(dp[i+1],dp[j],dp[i][j-1])
     *
     * @param args
     */
    public static void main(String[] args) {
        int[] sto = new int[] {5, 10, 30, 2,36};
        int length = sto.length;
        Node[][] dp = new Node[length][length];
        for (int i = 0; i < length; i++) {
            for (int j = 0; j < length - i; j++) {
                dp[j][j + i] = new Node();
                if (i == 0) {
                    dp[j][j].first = sto[j];
                    dp[j][j].second = 0;
                } else {
                    boolean chooseLeft = dp[j + 1][j + i].second + sto[j] > dp[j][j + i - 1].second + sto[j + i];
                    dp[j][j + i].first =
                            Integer.max(dp[j + 1][j + i].second + sto[j], dp[j][j + i - 1].second + sto[j + i]);
                    dp[j][j + i].second = chooseLeft ? dp[j + 1][j + i].first : dp[j][j + i - 1].first;
                }
            }
        }
        System.out.println(dp[0][length - 1].first - dp[0][length - 1].second);
    }

    static class Node {
        int first;
        int second;
    }
}
