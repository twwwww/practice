package com.tww.test.arithmetic.test.luogu;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class P2062 {


    /**
     *
     *
     * 给定n个选手，将他们分成若干只队伍。其中第i个选手要求自己所属的队伍的人数大等于a[i]人。
     *
     * 在满足所有选手的要求的前提下，最大化队伍的总数。
     *
     * 注：每个选手属于且仅属于一支队伍。
     *
     * 
     * @param args
     * @throws Exception
     */
    public static void main(String[] args) throws Exception {
        test2();
    }


    private static void test1() {
        Scanner cin = new Scanner(System.in);
        int num = cin.nextInt();
        int[] arr = new int[num];
        for (int i = 0; i < num; i++) {
            arr[i] = cin.nextInt();
        }

        List<Integer> sortedArr =
                Arrays.stream(arr).sorted().collect(ArrayList::new, ArrayList::add, ArrayList::addAll);

        int count = 0;
        for (int i = num - 1; i >= 0;) {
            Integer need = sortedArr.get(i);
            if (need > i + 1) {
                System.out.println("error");
            } else {
                i = i - need;
                count++;
                if (i < 0) {
                    break;
                }
            }
        }

        System.out.println(count);
    }

    private static void test2() {
        Scanner cin = new Scanner(System.in);
        int num = cin.nextInt();
        int[] arr = new int[num];
        for (int i = 0; i < num; i++) {
            arr[i] = cin.nextInt();
        }

        List<Integer> sortedArr =
                Arrays.stream(arr).sorted().collect(ArrayList::new, ArrayList::add, ArrayList::addAll);

        int[] dp = new int[num + 1];

        dp[0] = 0;

        for (int i = 1; i < num + 1; i++) {
            if (i - sortedArr.get(i - 1) < 0) {
                dp[i] = Integer.MIN_VALUE;
            } else {
                dp[i] = Integer.max(dp[i - 1],dp[i - sortedArr.get(i - 1)] + 1);
            }
        }

        System.out.println(dp[num]);
    }


    /**
     *
     * 5 2 1 2 2 3
     *
     *
     * 2
     *
     *
     *
     *
     */
}
