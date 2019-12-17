package com.tww.test.arithmetic.test.luogu;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;

public class P1052 {

    /**
     *
     * dp(l) = Min(dp(l-i)) + stone.contains(l) ? 1 : 0
     * Base Case:dp(i) = 1(S <= i <= T &&  stone.contains(i))
     * Base Case:dp(i) = 0(S <= i <= T &&  !stone.contains(i))
     * Base Case:dp(i) = -1(i < S)
     *
     * L > 1 * 10^9 时效率太低
     * 路径压缩，s < t时， ss和t一定会重合（当距离为lcm(s,t)lcm(s,t)即s，t的最小公倍数时） 而这以后的每个点都可以到达，
     * 所以我们只需将每两个石头超过 s×t 的距离缩成s×t就可以了
     * 当s = t时我们只需枚举每个石头的坐标是否为s的倍数即可。
     *
     * @param args
     * @throws Exception
     */
    public static void main(String args[]) throws Exception {
        method1();
    }

    public static void method1() {
        Scanner cin = new Scanner(System.in);
        int L = cin.nextInt();
        int S = cin.nextInt(), T = cin.nextInt();
        int M = cin.nextInt();

        List<Integer> stones = new ArrayList<>();
        for (int i = 0; i < M; i++) {
            stones.add(cin.nextInt());
        }

        int[] dp = new int[L + T - 1]; //有可能超过长度；

        for (int i = 0; i < L + T - 1; i++) {
            if (i < S) {
                dp[i] = -1;
            } else if (i >= S && i <= T) {
                dp[i] = stones.contains(i) ? 1 : 0;
            } else {
                int num = Integer.MAX_VALUE;
                for (int j = S; j <= T; j++) {
                    if (dp[i - j] != -1) {
                        num = Integer.min(num,dp[i - j] + (stones.contains(i) ? 1 : 0));
                    }
                }
                dp[i] = num == Integer.MAX_VALUE ? -1 : num;
            }
        }

        int min = Integer.MAX_VALUE;

        for (int i = L; i < L + T - 1; i++) {
            if (dp[i] != -1) {
                min = Integer.min(min, dp[i]);
            }
        }

        System.out.println(min);
    }

    public static void method2() {
        Scanner cin = new Scanner(System.in);
        int L = cin.nextInt();
        int S = cin.nextInt(), T = cin.nextInt();
        int M = cin.nextInt();

        List<Integer> stones = new ArrayList<>();
        for (int i = 0; i < M; i++) {
            stones.add(cin.nextInt());
        }

        if (S == T) {
            System.out.println(stones.stream().filter(stone -> stone % S == 0).count());
            return;
        }

        stones.sort(Comparator.comparing(stone -> stone));

        int[] dp = new int[99 * S * T];
        int[] stoneIndexMapper = new int[stones.size()];
        int lastIndex = 0;
        int stoneIndex = 0;

    }



}
