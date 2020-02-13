package com.tww.test.arithmetic.dp;

public class GpDpTest2 {
    /**
     * Say you have an array for which the ith element is the price of a given stock on day i.
     * 
     * Design an algorithm to find the maximum profit. You may complete as many transactions as you like
     * (i.e., buy one and sell one share of the stock multiple times).
     * 
     * Note: You may not engage in multiple transactions at the same time (i.e., you must sell the stock
     * before you buy again).
     * 
     * Example 1:
     * 
     * Input: [7,1,5,3,6,4] Output: 7 Explanation: Buy on day 2 (price = 1) and sell on day 3 (price =
     * 5), profit = 5-1 = 4. Then buy on day 4 (price = 3) and sell on day 5 (price = 6), profit = 6-3 =
     * 3. Example 2:
     * 
     * Input: [1,2,3,4,5] Output: 4 Explanation: Buy on day 1 (price = 1) and sell on day 5 (price = 5),
     * profit = 5-1 = 4. Note that you cannot buy on day 1, buy on day 2 and sell them later, as you are
     * engaging multiple transactions at the same time. You must sell before buying again. Example 3:
     * 
     * Input: [7,6,4,3,1] Output: 0 Explanation: In this case, no transaction is done, i.e. max profit =
     * 0.
     *
     * 第k天往后投资的最大收益
     * dp[k] = Max(dp[m] + days[j] - days[i])  (i<=j<=m) i天买，j天卖
     * Base Case:
     * dp[k] = 0;
     *
     * time : O(n^3)
     * space : O(n)
     *
     * 优化：判断第m天卖股票时可以固定sell，遍历buy
     * （在一堆数字中，已知最值，如果给这堆数添加一个数，那么比较一下就可以很快算出新的最值；
     *  但如果减少一个数，就不一定能很快得到新的最值了，而要遍历所有数重新找最值。）
     * time : O(n^2)
     * space : O(n)
     *
     * 最优方案：贪心算法，只要价格第二天比前一天高，就进行交易
     *
     * 变化1：限制交易次数y;
     * 增加了一个状态交易次数y
     * dp[y][k] = Max(dp[y-1][m] + days[j] - days[i])  (i<=j<=m,0<y);
     * Base Case:
     * dp[0][0-k] = 0;
     * dp[0-y][k] = 0;
     * time : O(yn^2)
     * space : O(yn)
     *
     * 变化2: 卖出股票后无法在第二天买入股票（即冷冻期为1天）
     * 状态转移方程限制条件变化
     * dp[k] = dp[k] = Max(dp[m] + days[j] - days[i])   (i<=j,j + 2 <= m)
     * Base Case:
     * dp[k] = 0;
     * time : O(n^2)
     * space: O(n)
     *
     * 变化3: 增加交易手续费 f
     * 状态转移方程变化
     * dp[k] = dp[k] = Max(dp[m] + days[j] - days[i] - f)   (i<=j<=m)
     * time : O(n^2)
     * space : O(n)
     */
    public static void main(String[] args) {
        int[] days = new int[]{7,1,5,3,6,4};
        ques1(days);
        ques2(new int[]{3,2,6,5,0,3},2);
        ques3(new int[]{1,2,3,0,2});
        ques4(new int[]{1,3,2,8,4,9},2);
    }

    private static void ques1(int[] days) {
        int length = days.length;
        int[] dp = new int[length];
        dp[length - 1] = 0;
        for (int i = length - 2; i >= 0; i--) {
            Integer max = 0;

            /*for (int j = i; j < length ; j++) {
                for (int m = j; m < length; m++) {
                    max = Integer.max(max,dp[m] + days[m] - days[j]);
                }
            }*/

            int minBuy = days[i];
            for (int m = i; m < length; m++) {
                minBuy = Integer.min(minBuy,days[m]);
                max = Integer.max(max,dp[m] + days[m] - minBuy);
            }

            dp[i] = max;
        }
        System.out.println(dp[0]);
    }

    private static void ques2(int[] days,int y) {
        int length = days.length;
        int[][] dp = new int[y + 1][length];
        dp[0][length - 1] = 0;

        for (int i = 0; i <= y; i++) {
            for (int j = length - 2; j >= 0; j--) {
                if (i == 0) {
                    dp[i][j] = 0;
                } else {
                    Integer max = 0;
                    int minBuy = days[j];
                    for (int m = j; m < length; m++) {
                        minBuy = Integer.min(minBuy,days[m]);
                        max = Integer.max(max,dp[i - 1][m] + days[m] - minBuy);
                    }
                    dp[i][j] = max;
                }
            }
        }
        System.out.println(dp[y][0]);
    }

    private static void ques3(int[] days) {
        int length = days.length;
        int[] dp = new int[length];
        dp[length - 1] = 0;
        for (int i = length - 2; i >= 0; i--) {
            Integer max = 0;

            int minBuy = days[i];
            for (int m = i; m < length; m++) {
                minBuy = Integer.min(minBuy,days[m]);
                max = Integer.max(max,(m + 2 < length ? dp[m + 2] : 0) + days[m] - minBuy);
            }

            dp[i] = max;
        }
        System.out.println(dp[0]);
    }

    private static void ques4(int[] days,int f) {
        int length = days.length;
        int[] dp = new int[length];
        dp[length - 1] = 0;
        for (int i = length - 2; i >= 0; i--) {
            Integer max = 0;
            int minBuy = days[i];
            for (int m = i; m < length; m++) {
                minBuy = Integer.min(minBuy,days[m]);
                max = Integer.max(max,dp[m] + Integer.max(0,(days[m] - minBuy - 2))); //判断同一天买卖时收益为0
            }
            dp[i] = max;
        }
        System.out.println(dp[0]);
    }
}
