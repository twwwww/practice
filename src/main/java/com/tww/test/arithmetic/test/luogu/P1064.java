package com.tww.test.arithmetic.test.luogu;

import com.google.common.collect.Lists;

import java.util.List;
import java.util.Scanner;

public class P1064 {

    /**
     *
     * 先将副商品排列在主商品后，修改标识为1-N表示该主商品的第N个附属商品
     * 遍历填充dp数组，当计算主商品时，当前状态由前一个商品状态转移而来。
     * 当计算为副商品时，需要遍历归属的同一主商品的主商品状态及所有副商品状态
     *
     * m元时，第k件物品的 买与不买，能获得的最大价值
     *
     * 主商品时
     * dp[m][k][0] = Max(dp[m][k - 1][0],dp[m][k - 1][1])
     * dp[m][k][1] = Max(dp[m - money[k]][k - 1][0] ,dp[m - money[k]][k - 1][1]) + money[k] * ups[k]
     *
     * 副商品时
     * dp[m][k][0] = Max(dp[m][k - 1][0],dp[m][k - 1][1])
     * dp[m][k][1] = Max(dp[m - money[k]][∑kBuy][1]) + money[k] * ups[k] (kBuy取值为该商品的主商品序号到该商品前一个商品序号)
     *
     *
     * Base Case:
     * dp[0][0 - k] = 0;
     * dp[0 - money[0]][0] = 0
     * dp[money[0] - m][0] = money[0] * ups[0]
     *
     * @param args
     * @throws Exception
     */
    public static void main(String args[]) throws Exception {
        Scanner cin = new Scanner(System.in);
        int N = cin.nextInt(), m = cin.nextInt();

        int[] moneys = new int[m];
        int[] ups = new int[m];
        int[] belongs = new int[m];

        List<Product> products = Lists.newArrayList();
        for (int i = 0; i < m; i++) {
            Product pro = new Product();
            pro.setMoney(cin.nextInt());
            pro.setUp(cin.nextInt());
            pro.setBelong(cin.nextInt());
            products.add(pro);
        }




        int[][][] dp = new int[N][m][2];


        System.out.println(dp[N - 1][m - 1]);
    }

    static class Product {
        private int money;
        private int up;
        private int belong;

        public int getMoney() {
            return money;
        }

        public void setMoney(int money) {
            this.money = money;
        }

        public int getUp() {
            return up;
        }

        public void setUp(int up) {
            this.up = up;
        }

        public int getBelong() {
            return belong;
        }

        public void setBelong(int belong) {
            this.belong = belong;
        }
    }
}
