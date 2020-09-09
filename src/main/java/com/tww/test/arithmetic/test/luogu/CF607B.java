package com.tww.test.arithmetic.test.luogu;

import java.util.Scanner;


public class CF607B {


    /**
     *
     *
     * Genos最近在他的手机上下载了祖玛游戏。在祖玛游戏里，存在n个一行的宝石，第i个宝石的颜色是Ci 。这个游戏的目标是尽快的消灭一行中所有的宝石。 在一秒钟，Genos能很快的挑选出这些有颜色的宝石中的一个回文的，连续的子串，并将这个子串移除。每当一个子串被删除后，剩余的宝石将连接在一起，形成一个新的行列。你的任务是：求出把整个宝石串都移除的最短时间。 让我们给你一个提示：如果一个串正着读或倒着读都一样，那么这个串（或子串）叫回文串。在我们这道题中，“回文”指这个宝石串中的第一个珠子的颜色等于最后一个珠子的颜色，第二个珠子的颜色等于倒数第二个珠子的颜色，等等。 输入输出格式 输入格式：
     *
     * 第一行包含一个整数n(1<=n<=500) ——宝石串的长度。 第二行包含n个被空格分开的整数，第i(1<=i<=n) 个表示这行中第i个珠子的颜色。 输出格式：
     *
     * 输出一个整数，把这行珠子移除的最短时间。 (样例略) 说明：
     *
     * 在第一个例子中，Genos可以在一秒钟就把这行珠子全部移走。 在第二个例子中，Genos一次只能移走一个珠子，所以移走三个珠子花费他三秒。 在第三个例子中，为了达到2秒的最快时间，先移除回文串4,再移除回文串1 2 3 2 1。
     *
     * 感谢@Administrator2004 提供的翻译
     *
     *
     * @param args
     * @throws Exception
     */
    public static void main(String args[]) throws Exception {
        test1();
    }

    private static void test1() {
        Scanner cin = new Scanner(System.in);
        int n = cin.nextInt();

        int[] l1 = new int[n];
        int[] l2 = new int[n];
        for (int i = 0; i < n; i++) {
            l1[i] = cin.nextInt();
        }

        for (int i = 0; i < n; i++) {
            l2[i] = cin.nextInt();
        }


    }

/**
 *
 * 3
 * 1 2 1
 *
 * 1
 *
 *
 * 3
 * 1 2 3
 *
 * 3
 *
 *
 * 7
 * 1 4 4 2 3 2 1
 *
 * 2
 */
}


