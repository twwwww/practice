package com.tww.test.arithmetic.test.luogu;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;

public class P1156 {


    /**
     *
     * 卡门――农夫约翰极其珍视的一条 Holsteins 奶牛――已经落了到“垃圾井”中。“垃圾井”是农夫们扔垃圾的地方，它的深度为D(2≤D≤100)英尺。
     *
     * 卡门想把垃圾堆起来，等到堆得与井同样高时，她就能逃出井外了。另外，卡门可以通过吃一些垃圾来维持自己的生命。
     *
     * 每个垃圾都可以用来吃或堆放，并且堆放垃圾不用花费卡门的时间。
     *
     * 假设卡门预先知道了每个垃圾扔下的时间t(0<t≤1000)，以及每个垃圾堆放的高度h(1≤h≤25)和吃进该垃圾能维持生命的时间f(1≤f≤30)，要求出卡门最早能逃出井外的时间，假设卡门当前体内有足够持续10小时的能量，如果卡门10小时内没有进食，卡门就将饿死。
     *
     * 输入格式 第一行为2个整数，D 和 G(1≤G≤100)，G为被投入井的垃圾的数量。
     *
     * 第二到第G+1行每行包括3个整数：T(0<T<=1000)，表示垃圾被投进井中的时间；F(1≤F≤30)，表示该垃圾能维持卡门生命的时间；和 H(1≤H≤25)，该垃圾能垫高的高度。
     *
     * 输出格式 如果卡门可以爬出陷阱，输出一个整表示最早什么时候可以爬出；否则输出卡门最长可以存活多长时间。
     *
     *
     * dp[i][j]
     *
     * 剩余i能量，处理完第j件垃圾后的最大高度
     *
     *
     * 消耗:t[j] - t[j - 1] 1) 吃掉 : 能量增加 f[j] 高度不变 ==> eat = dp[i + t[j] - t[j - 1] - f[j]][j - 1] 2) 增加高度
     * : 能量不变 高度增加 h[j] ==> high = dp[i + t[j] - t[j - 1]][j - 1] + h[j] 注:i不能小于0，小于0为非法值。
     *
     * dp[i][j] = Max(eat,high)
     *
     * Base Case : dp[i][0] = 0 (i <= 10)
     *
     * dp[i][0] = -1 (i > 10) 初始能量为10，大于10为非法值
     *
     *
     * 遍历 dp j 0 -> G 有大于N的时代表可以出来的最早垃圾落入时间
     *
     * 不可出来时，存活时间为能量为掉落时间差值
     *
     *
     *
     * //todo
     * @param args
     * @throws Exception
     */
    public static void main(String[] args) throws Exception {
        test1();
    }


    private static void test1() {
        Scanner cin = new Scanner(System.in);
        int D = cin.nextInt();
        int G = cin.nextInt();

        int A = 10;

        List<Rubbish> rubbishes = new ArrayList<>();
        for (int i = 0; i < G; i++) {
            Rubbish rubbish = new Rubbish();
            rubbish.t = Integer.valueOf(cin.next());
            rubbish.f = Integer.valueOf(cin.next());
            rubbish.h = Integer.valueOf(cin.next());
            rubbishes.add(rubbish);
        }

        int AAll = rubbishes.stream().mapToInt(a -> a.f).sum() + A;

        rubbishes.sort(Comparator.comparing(b -> b.t));

        int[][] dp = new int[AAll + 1][G + 1];

        int result = -1;
        a: for (int i = 0; i < G + 1; i++) {
            for (int j = 0; j < AAll + 1; j++) {
                if (i == 0) {
                    dp[j][i] = j <= A ? 0 : Integer.MIN_VALUE;
                } else {
                    int eat = Integer.MIN_VALUE;
                    int high = Integer.MIN_VALUE;
                    if (j + rubbishes.get(i - 1).t - (i >= 2 ? rubbishes.get(i - 1 - 1).t : 0)
                            - rubbishes.get(i - 1).f >= 0
                            && j + rubbishes.get(i - 1).t - (i >= 2 ? rubbishes.get(i - 1 - 1).t : 0)
                                    - rubbishes.get(i - 1).f < AAll) {
                        eat = dp[j + rubbishes.get(i - 1).t - (i >= 2 ? rubbishes.get(i - 1 - 1).t : 0)
                                - rubbishes.get(i - 1).f][i - 1];
                    }
                    if (j + rubbishes.get(i - 1).t - (i >= 2 ? rubbishes.get(i - 1 - 1).t : 0) >= 0
                            && j + rubbishes.get(i - 1).t - (i >= 2 ? rubbishes.get(i - 1 - 1).t : 0) < AAll) {
                        high = dp[j + rubbishes.get(i - 1).t - (i >= 2 ? rubbishes.get(i - 1 - 1).t : 0)][i - 1]
                                + rubbishes.get(i - 1).h;
                    }

                    dp[j][i] = Integer.max(eat, high);
                    if (dp[j][i] >= D) {
                        result = i;
                        break a;
                    }
                }
            }
        }
        if (result > -1) {
            System.out.println(rubbishes.get(result - 1).t);
        } else {
            int AA = A;
            for (int i = 0; i < G; i++) {
                if (rubbishes.get(i).t <= AA) {
                    AA += rubbishes.get(i).f;
                }
            }
            System.out.println(AA);
        }


    }

    private static class Rubbish {
        private int t;
        private int f;
        private int h;
    }



    /**
     *
     * 20 4 5 4 9 9 3 2 12 6 10 13 1 1 t f h
     *
     * 13
     *
     *
     *
     *
     *
     *
     */
}
