package com.tww.test.arithmetic.dp.by;

import java.util.Arrays;
import java.util.stream.Stream;

public class ByTest2 {

    /**
     *农夫约翰的奶牛喜欢玩硬币游戏.
     * 初始时，一个有N枚硬币的堆栈放在地上，从堆顶数起的第i枚硬币的币值 为Ci
     * 开始玩游戏时，第一个玩家可以从堆顶拿走一枚或两枚硬币.如果第一个玩家只拿走堆顶的 一枚硬币，那么第二个玩家可以拿走随后的一枚或两枚硬币.
     * 如果第一个玩家拿走两枚硬币，则第二个玩家可以拿走1，2，3，或4枚硬币.在每一轮中，当前的玩家至少拿走一枚硬币，至多拿 走对手上一次所拿硬币数量的两倍.
     * 当没有硬币可拿时，游戏结束.
     * 两个玩家都希望拿到最多钱数的硬币.请问，当游戏结束时，第一个玩家最多能拿多少钱呢？
     *
     *
     * coin[n]
     * i:剩余的数量，j:上回合拿的数量，k:本回合取的数量(1<=k<=2*j)
     * 状态转移:
     * dp[i][j] = Max(sum(i) - dp[i-k][k])  {1<=k<=2*j}
     *
     * dp[i][j] 和 dp[i][j-1] 重复计算项，
     * dp[i][j] 比 dp[i][j-1] 多计算的为sum(i) - dp[i - 2 * j][2 * j]),sum(i) - dp[i - (2 * j - 1)][2 * j - 1])；
     *
     *
     * Base Case:
     * dp[0][1-n] = 0
     * dp[1][1-n] = coin[0]
     *
     * O(n^3),O(n^2)
     *
     * 优化
     * dp[i][j] 和dp[i][j-1] 重复计算了(sum(i) - dp[i - k][k])(1 <= k <= 2 * j)
     * dp[i][j] = Max(dp[i][j-1],(sum(i) - dp[i - 2 * j][2 * j]),sum(i) - dp[i - (2 * j - 1)][2 * j - 1]))
     * O(n^2),O(n^2)
     *
     * @param args
     */
    public static void main(String[] args) {
        int[] coin = new int[]{2,111,1,3,111,111,1};
        int length = coin.length;
        int[][] dp = new int[length + 1][length + 1];
        for (int i = 0; i < length + 1; i++) {
            int sumCoin = Arrays.stream(coin).limit(i).sum();
            for (int j = 1; j < length + 1; j++) {
                if (i == 0) {
                    dp[i][j] = 0;
                } else if (i == 1) {
                    dp[i][j] = coin[0];
                } else {
                    /*int maxCoin = -1;
                    for (int k = 1; k <= 2 * j && k <= i; k++) {
                        maxCoin = Integer.max(maxCoin,sumCoin - dp[i - k][Integer.min(k,length - 1)]);
                    }
                    dp[i][j] = maxCoin;*/
                    dp[i][j] = dp[i][j - 1];
                    dp[i][j] = Integer.max(
                            dp[i][j],sumCoin - dp[Integer.max(0,i - 2 * j)][Integer.min(length - 1,2 * j)]);
                    dp[i][j] = Integer.max(
                            dp[i][j],sumCoin - dp[Integer.max(0,i - (2 * j - 1))][Integer.min(length - 1,2 * j - 1)]);
                }
            }
        }
        System.out.println(dp[length][1]);
    }
}
