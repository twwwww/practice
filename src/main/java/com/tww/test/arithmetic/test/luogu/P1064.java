package com.tww.test.arithmetic.test.luogu;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
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
     * dp[m][k][1] = Max(dp[m - money[k]][kBuy][1]) + money[k] * ups[k] (kBuy取值为该商品的主商品序号到该商品前一个商品序号)
     *
     *
     * Base Case:
     * dp[0][0 - k][0] = 0;
     * dp[0][0 - k][1] = 0;
     * dp[0 - money[0]][0][0] = 0
     * dp[0 - money[0]][0][1] = -1
     * dp[money[0] - m][0][1] = money[0] * ups[0]
     * dp[money[0] - m][0][0] = 0
     *
     * dp[m][k][1] = -1  (m > money[k])   当前金额小于商品金额，不能选择买
     *
     * time : O(m * n^2)
     * space: O(m * n)
     *
     * @param args
     * @throws Exception
     */
    public static void main(String args[]) throws Exception {
        Scanner cin = new Scanner(System.in);
        int N = cin.nextInt(), m = cin.nextInt();

        List<Product> products = new ArrayList<>();
        for (int i = 0; i < m; i++) {
            Product pro = new Product();
            pro.money = cin.nextInt();
            pro.up = cin.nextInt();
            pro.belong = cin.nextInt();
            products.add(pro);
        }

        //将副商品排列在主商品后修改标识为1-N表示该主商品的第N个附属商品  O(n)
        Product[] sortProducts = changeProducts(products).toArray(new Product[products.size()]);

        N++;

        int[][][] dp = new int[N][m][2];

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < m; j++) {
                if (i == 0) {
                    dp[i][j][0] = 0;
                    dp[i][j][1] = -1;
                } else if (j == 0) {
                    dp[i][j][0] = 0;
                    if (i < sortProducts[0].money) {
                        dp[i][j][1] = -1;
                    } else {
                        dp[i][j][1] = sortProducts[0].money * sortProducts[0].up;
                    }
                } else {
                    Product currProduct = sortProducts[j];
                    dp[i][j][0] = Integer.max(dp[i][j - 1][0],dp[i][j - 1][1]);
                    if (currProduct.belong == 0) { //主商品
                        if (i < currProduct.money) {
                            dp[i][j][1] = -1;
                        } else {
                            dp[i][j][1] = Integer.max(dp[i - currProduct.money][j - 1][0],
                                    dp[i - currProduct.money][j - 1][1]) + currProduct.up * currProduct.money;
                        }
                    } else {  //副商品
                        if (i < currProduct.money) {
                            dp[i][j][1] = -1;
                        } else {
                            int max = -1;
                            for (int k = 1; k <= currProduct.belong; k++) {
                                int childState = dp[i - currProduct.money][j - k][1];
                                if (childState >= 0) {
                                    max = Integer.max(childState + currProduct.up * currProduct.money, max);
                                }
                            }
                            dp[i][j][1] = max;
                        }
                    }
                }
            }
        }
        System.out.println(Integer.max(dp[N - 1][m - 1][0],dp[N - 1][m - 1][1]));
    }


    private static List<Product> changeProducts(List<Product> products) {
        Map<Integer,List<Product>> productMap = new HashMap<>();
        products.forEach(pro -> {
            if (!productMap.containsKey(pro.belong)) {
                productMap.put(pro.belong,new ArrayList<>());
            }
            List<Product> beLongPros = productMap.get(pro.belong);
            if (pro.belong != 0) { //将副商品修改标识为1-N表示该主商品的第N个附属商品
                pro.belong = beLongPros.size() + 1;
            }
            beLongPros.add(pro);
        });
        List<Product> result = new ArrayList<>();
        for (int i = 0; i < products.size(); i++) {
            if (products.get(i).belong == 0) {
                result.add(products.get(i));
                result.addAll(Objects.isNull(productMap.get(i + 1)) ? new ArrayList<>() : productMap.get(i + 1));
            }
        }
        return result;
    }

    private static class Product {
        int money;
        int up;
        int belong;

    }
}
