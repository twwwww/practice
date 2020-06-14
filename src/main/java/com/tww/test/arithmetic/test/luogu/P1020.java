package com.tww.test.arithmetic.test.luogu;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class P1020 {


    /**
     *
     * 某国为了防御敌国的导弹袭击，发展出一种导弹拦截系统。但是这种导弹拦截系统有一个缺陷：虽然它的第一发炮弹能够到达任意的高度，但是以后每一发炮弹都不能高于前一发的高度。某天，雷达捕捉到敌国的导弹来袭。由于该系统还在试用阶段，所以只有一套系统，因此有可能不能拦截所有的导弹。
     *
     * 输入导弹依次飞来的高度（雷达给出的高度数据是≤50000的正整数），计算这套系统最多能拦截多少导弹，如果要拦截所有导弹最少要配备多少套这种导弹拦截系统。
     *
     * 输入格式
     * 1行，若干个整数（个数\≤100000）
     *
     * 输出格式
     * 2行，每行一个整数，第一个数字表示这套系统最多能拦截多少导弹，第二个数字表示如果要拦截所有导弹最少要配备多少套这种导弹拦截系统。
     *
     *
     * 1)求最大不上升子序列长度
     * 2)求最少的最大不上升子序列划分数 <=> 最大上升子序列长度
     *
     * 1)
     *
     * ans1:
     * dp[j] = Max(m[j] <= m[i] ? dp[i] + 1 : 1) (0<=i<j);
     *
     * Base Case : dp[0] = 1
     *
     * time complexity : n^2
     * space complexity : n
     *
     * ans2:
     *
     *   最长递增子序列问题：在一列数中寻找一些数，这些数满足：任意两个数a[i]和a[j]，若i<j，必有a[i]<a[j]，这样最长的子序列称为最长递增子序列。
     *
     *    设dp[i]表示以i为结尾的最长递增子序列的长度，则状态转移方程为：
     *
     * dp[i] = max{dp[j]+1}, 1<=j<i,a[j]<a[i].
     *
     *    这样简单的复杂度为O(n^2)，其实还有更好的方法。
     *
     *    考虑两个数a[x]和a[y]，x<y且a[x]<a[y],且dp[x]=dp[y]，当a[t]要选择时，到底取哪一个构成最优的呢？显然选取a[x]更有潜力，因为可能存在a[x]<a[z]<a[y]，这样a[t]可以获得更优的值。在这里给我们一个启示，当dp[t]一样时，尽量选择更小的a[x].
     *
     *     按dp[t]=k来分类，只需保留dp[t]=k的所有a[t]中的最小值，设d[k]记录这个值，d[k]=min{a[t],dp[t]=k}。
     *
     *     这时注意到d的两个特点（重要）：
     *
     * 1. d[k]在计算过程中单调不升；
     *
     * 2. d数组是有序的，d[1]<d[2]<..d[n]。
     *
     *     利用这两个性质，可以很方便的求解：
     *
     * 1. 设当前已求出的最长上升子序列的长度为len（初始时为1），每次读入一个新元素x：
     *
     * 2. 若x>d[len]，则直接加入到d的末尾，且len++；（利用性质2）
     *
     *    否则，在d中二分查找，找到第一个比x小的数d[k]，并d[k+1]=x，在这里x<=d[k+1]一定成立（性质1,2）。
     *
     *
     *
     * @param args
     * @throws Exception
     */
    public static void main(String args[]) throws Exception {
        test2();
    }


    private static void test1() {
        Scanner cin = new Scanner(System.in);

        String s = cin.nextLine();
        List<Integer> collect = Arrays.stream(s.split(" ")).map(Integer::valueOf).collect(Collectors.toList());
        int num = collect.size();
        Integer[] m = collect.toArray(new Integer[num]);

        int[] dp1 = new int[num];
        int[] dp2 = new int[num];

        dp1[0] = 1;
        dp2[0] = 1;

        int max1 = 1;
        int max2 = 1;

        for (int i = 1; i < num; i++) {
            int dp1M = 1;
            int dp2M = 1;
            for (int j = 0; j < i; j++) {
                if (m[i] <= m[j]) {
                    dp1M = Integer.max(dp1M,dp1[j] + 1);
                } else {
                    dp2M = Integer.max(dp2M,dp2[j] + 1);
                }
            }
            dp1[i] = dp1M;
            dp2[i] = dp2M;
            max1 = Integer.max(max1,dp1M);
            max2 = Integer.max(max2,dp2M);
        }

        System.out.println(max1);
        System.out.println(max2);

    }

    private static void test2() {
        Scanner cin = new Scanner(System.in);

        String s = cin.nextLine();
        List<Integer> collect = Arrays.stream(s.split(" ")).map(Integer::valueOf).collect(Collectors.toList());
        int num = collect.size();
        Integer[] m = collect.toArray(new Integer[num]);

        int[] d1 = new int[num + 1];
        int[] d2 = new int[num + 1];

        d1[0] = Integer.MAX_VALUE;
        d2[0] = Integer.MIN_VALUE;

        int d1Len = 1;
        int d2Len = 1;

        for (int i = 0; i < num; i++) {
            if (m[i] <= d1[d1Len - 1]) {
                d1[++d1Len] = m[i];
            } else {
                int index = binarySearchMinNumGteThan(d1, m[i], 0, d1Len - 1);
                d1[index] = m[i];
            }

            if (m[i] > d2[d1Len - 1]) {
                d2[++d2Len] = m[i];
            } else {
                int index = binarySearchMaxNumLtThan(d2, m[i], 0, d2Len - 1);
                d2[index] = m[i];
            }
        }

        System.out.println(d1[d1Len - 1]);
        System.out.println(d2[d2Len - 1]);

    }

    private static int binarySearchMaxNumLtThan(int[] arr,int target,int start,int end) {
        int mid  = (end - start) / 2 + start;
        if (arr[mid] < target) {
            if (mid + 1 == end) {
                return mid;
            }
            return binarySearchMaxNumLtThan(arr,target,mid,end);
        } else {
            if (mid == start) {
                return -1;
            }
            return binarySearchMaxNumLtThan(arr,target,start,mid);
        }
    }

    private static int binarySearchMinNumGteThan(int[] arr,int target,int start,int end) {
        int mid  = (end - start) / 2 + start;
        if (arr[mid] >= target) {
            if (mid + 1 == end) {
                return mid;
            }
            return binarySearchMinNumGteThan(arr,target,mid,end);
        } else {
            if (mid == start) {
                return -1;
            }
            return binarySearchMinNumGteThan(arr,target,start,mid);
        }
    }




    /**
     *
     * 389 207 155 300 299 170 158 65
     *
     * 6
     * 2
     *
     *
     */
}
