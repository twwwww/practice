package com.tww.test.arithmetic.dp.by;

public class ByTest3 {

    /**
     *Alice和Bob在玩这样一个游戏。给定k个数字a[1],a[2],…,a[k]。一开始，有x枚硬币，Alice和Bob轮流取硬币。
     * 每次所取硬币的枚数一定要在a[1],a[2],…,a[k]当中。
     * Alice先取，取走最后一枚硬币的一方获胜。当双方都采取最优策略时，谁会获胜？题目假定a[1],a[2],…,a[k]中一定有1。
     *
     *以先手分析
     * win_state: x-a[i] => 对手lose_state  (1<=i<=k ,k取某一值时)
     * lose_state: x-a[i] => 对手win_state  (1<=i<=k ,k取任一值时)
     *
     * Base Case:
     * x=0 时  lose_state;
     * x=1 时 win_state;
     *
     * O(xk)，O(x)
     *
     */
    public static void main(String[] args) {
        int[] choose = new int[]{1,3,2};
        int coin = 12;
        boolean[] dp = new boolean[coin + 1];
        dp[0] = false;
        dp[1] = true;
        for (int i = 2; i < coin + 1; i++) {
            boolean state = false;
            for (int i1 : choose) {
                if (!dp[i - Integer.min(i, i1)]) {
                    state = true;
                    break;
                }
            }
            dp[i] = state;
        }
        System.out.println(dp[coin]);
    }
}
