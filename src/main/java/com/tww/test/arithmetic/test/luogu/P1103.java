package com.tww.test.arithmetic.test.luogu;

import java.util.Arrays;
import java.util.Comparator;
import java.util.Scanner;

public class P1103 {

    /**
     * Frank是一个非常喜爱整洁的人。他有一大堆书和一个书架，想要把书放在书架上。书架可以放下所有的书，所以Frank首先将书按高度顺序排列在书架上。但是Frank发现，由于很多书的宽度不同，所以书看起来还是非常不整齐。于是他决定从中拿掉k本书，使得书架可以看起来整齐一点。
     *
     * 书架的不整齐度是这样定义的：每两本书宽度的差的绝对值的和。例如有4本书：
     *
     * 1 \times 21×2
     * 5 \times 35×3
     * 2 \times 42×4
     * 3 \times 13×1
     * 那么Frank将其排列整齐后是：
     *
     * 1 \times 21×2
     * 2 \times 42×4
     * 3 \times 13×1
     * 5 \times 35×3
     * 不整齐度就是2+3+2=72+3+2=7
     *
     * 已知每本书的高度都不一样，请你求出去掉k本书后的最小的不整齐度。
     *
     * dp[i][k] 以i为一个右端点，还需要抽k本书的最小值
     *
     * @param args
     * @throws Exception
     */
    public static void main(String args[]) throws Exception {

        Scanner cin = new Scanner(System.in);
        int n = cin.nextInt();
        int k = cin.nextInt();

        int[][] books = new int[n][2];

        for (int i = 0; i < n; i++) {
            books[i][0] = cin.nextInt();
            books[i][1] = cin.nextInt();
        }

        if (n == 1) {
            System.out.println(0);
            return;
        }

        Arrays.sort(books, Comparator.comparingInt(b -> b[0]));

        // 前缀和,0-n的不整齐度和
        int[] sum = new int[n];
        sum[0] = 0;
        for (int i = 1; i < n; i++) {
            sum[i] = sum[i - 1] + Math.abs(books[i][1] - books[i - 1][1]);
        }

        int[][] dp = new int[n][k + 1];

        for (int i = 0; i < k + 1; i++) {
            for (int j = i; j < n; j++) {
                if (i == 0) {
                    dp[j][i] = sum[j];
                } else {
                    dp[j][i] = Integer.MAX_VALUE;
                    for (int l = Integer.max(0,i - j + 1); l <= i; l++) {
                        dp[j][i] = Integer.min(dp[j][i],dp[j - l - 1][i - l] + Math.abs(books[j][1] - books[j - l - 1][1]));
                    }
                }
            }
        }

        System.out.println(1);
    }
}
