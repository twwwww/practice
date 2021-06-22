package com.tww.test.arithmetic.test.luogu;

import java.util.Scanner;

public class P1133 {


    /**
     *
     * 题目描述
     * 教主有着一个环形的花园，他想在花园周围均匀地种上n棵树，但是教主花园的土壤很特别，每个位置适合种的树都不一样，一些树可能会因为不适合这个位置的土壤而损失观赏价值。
     *
     * 教主最喜欢33种树，这3种树的高度分别为10,20,3010,20,30。教主希望这一圈树种得有层次感，所以任何一个位置的树要比它相邻的两棵树的高度都高或者都低，并且在此条件下，教主想要你设计出一套方案，使得观赏价值之和最高。
     *
     * 输入格式
     * 第一行为一个正整数nn，表示需要种的树的棵树。
     *
     * 接下来nn行，每行33个不超过1000010000的正整数a_i,b_i,c_ia
     * i
     * ​
     *  ,b
     * i
     * ​
     *  ,c
     * i
     * ​
     *  ，按顺时针顺序表示了第ii个位置种高度为10,20,3010,20,30的树能获得的观赏价值。
     *
     * 第ii个位置的树与第i+1i+1个位置的树相邻，特别地，第11个位置的树与第nn个位置的树相邻。
     *
     * 输出格式
     * 一个正整数，为最大的观赏价值和。
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

        int[][] arr = new int[n][3];
        for (int i = 0; i < n; i++) {
            arr[i][0] = cin.nextInt();
            arr[i][1] = cin.nextInt();
            arr[i][2] = cin.nextInt();
        }


    }




    /**
     *
     * 4
     * 1 3 2
     * 3 1 2
     * 3 1 2
     * 3 1 2
     *
     * 11
     *
     *
     */
}
