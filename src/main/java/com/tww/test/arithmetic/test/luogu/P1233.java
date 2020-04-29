package com.tww.test.arithmetic.test.luogu;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class P1233 {


    /**
     *
     * 一堆木头棍子共有n根，每根棍子的长度和宽度都是已知的。棍子可以被一台机器一个接一个地加工。机器处理一根棍子之前需要准备时间。准备时间是这样定义的：
     *
     * 第一根棍子的准备时间为1分钟；
     *
     * 如果刚处理完长度为L，宽度为W的棍子，那么如果下一个棍子长度为Li，宽度为Wi，并且满足L>＝Li，W>＝Wi，这个棍子就不需要准备时间，否则需要1分钟的准备时间；
     *
     * 计算处理完n根棍子所需要的最短准备时间。比如，你有5根棍子，长度和宽度分别为(4, 9)，(5, 2)，(2, 1)，(3, 5)，(1, 4)，最短准备时间为2（按(4, 9)、(3,
     * 5)、(1, 4)、(5, 2)、(2, 1)的次序进行加工）。
     *
     * 输入格式 第一行是一个整数n(n<＝5000)，第2行是2n个整数，分别是L1，W1，L2，w2，…，Ln，Wn。L和W的值均不超过10000，相邻两数之间用空格分开。
     *
     *
     *
     * 对第一属性降序排序（第一属性相同时按第二属性降序）（nlogn），求第二属性不上升序列段数。Dilworth定理 => 求最长上升子序列长度
     *
     * dp[n] = Max((n > i) ? dp[i] + 1 : 1) (0 <= i < n)
     *
     * Base Case : dp[0] = 1;
     *
     * time complexity : n^2 space complexity : n
     *
     * all : nlogn + n^2 = n^2
     *
     * @param args
     * @throws Exception
     */
    public static void main(String args[]) throws Exception {
        Scanner cin = new Scanner(System.in);
        int n = cin.nextInt();

        List<Stick> stickes = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            Stick stick = new Stick();
            stick.a = cin.nextInt();
            stick.b = cin.nextInt();
            stickes.add(stick);
        }
        List<Integer> sortBList = stickes.stream()
                .sorted(Comparator.comparing(Stick::getA)
                        .reversed()
                        .thenComparing(Comparator.comparing(Stick::getB).reversed()))
                .map(Stick::getB)
                .collect(Collectors.toList());

        int[] dp = new int[n];

        dp[0] = 1;

        Integer max = 0;
        for (int i = 1; i < n; i++) {
            Integer num = 1;
            for (int j = 0; j < i; j++) {
                num = Integer.max(num, sortBList.get(i) > sortBList.get(j) ? dp[j] + 1 : 1);
            }
            dp[i] = num;
            max = Integer.max(num, max);
        }


        System.out.println(max);
    }

    private static class Stick {
        private Integer a;
        private Integer b;

        public Integer getA() {
            return a;
        }

        public void setA(Integer a) {
            this.a = a;
        }

        public Integer getB() {
            return b;
        }

        public void setB(Integer b) {
            this.b = b;
        }
    }

    /**
     *
     * 5 4 9 5 2 2 1 3 5 1 4
     */
}
