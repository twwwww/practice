package com.tww.test.arithmetic.test.other;

import com.google.common.collect.Lists;

import java.math.BigInteger;
import java.util.Scanner;

public class FactorialTest {


    /**
     *
     * 1.输入一个非负整数n，请你计算阶乘n!的结果末尾有几个 0。
     *
     *
     * 2.输入一个非负整数K，请你计算有多少个n，满足n!的结果末尾恰好有K个 0。
     *
     * @param args
     * @throws Exception
     */
    public static void main(String args[]) throws Exception {
        Scanner cin = new Scanner(System.in);
        int n = cin.nextInt();
        test0(n);
//        test1(n);
        test1_1(n);
        // test2(n);
    }

    private static void test0(Integer n) {
        BigInteger num = new BigInteger(n.toString());
        n = n - 1;
        while (n > 0) {
            num = num.multiply(new BigInteger(n.toString()));
            n--;
        }
        System.out.println(num.toString());
    }

    /**
     * time complexity : NlongN
     * space complexity : 1
     * @param n
     */
    private static void test1(Integer n) {


        int seed = 5;
        int seedNum = 0;

        while (seed <= n) {

            int multiple = 1;
            while (seed * multiple <= n) {
                seedNum++;
                multiple++;
            }
            seed *= 5;
        }
        System.out.println(seedNum);
    }

     /**
     * time complexity : longN
     * space complexity : 1
     * @param n
     */
    private static void test1_1(Integer n) {
        int seed = 5;
        int seedNum = 0;

        while (seed <= n) {
            seedNum += n / seed;
            seed *= 5;
        }

        System.out.println(seedNum);
    }

    /**
     * binary search
     * @param n
     */
    private static void test2(Integer n) {


        int seed = 5;
        int seedNum = 0;

        while (seed <= n) {

            int multiple = 1;
            while (seed * multiple <= n) {
                seedNum++;
                multiple++;
            }
            seed *= seed;
        }

        System.out.println(seedNum);
    }


}