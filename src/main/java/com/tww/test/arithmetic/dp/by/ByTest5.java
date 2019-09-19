package com.tww.test.arithmetic.dp.by;

import java.util.Arrays;

public class ByTest5 {
    /**
     *2个人轮流拿数组里面的一个数，且要比另外一个人上一次拿的数大[a，b]，
     * 第一次拿要拿[a，b]范围的数，求第一个人拿的总和比第二个人的总和最多大多少
     *
     * 对num[i]排序
     * j:上次取值下标，k本次取的下标          (0 <= j,k <= i)
     * dp(j) = num[k] - Min(dp(k))  (num[j] + a <= num[k] <= num[j] + b)
     * Base Case:
     * dp(i) = num[i]
     *
     */
    public static void main(String[] args) {
        int [] nums = new int[]{0,1,3,-2,5,-3,6};  //第一位加入0占位
        int max = 2;
        int min = 1;
        Arrays.sort(nums);
        int[] dp =  new int[nums.length + 1];
        dp[nums.length] = nums[nums.length - 1];
        for (int i = nums.length; i >= 0; i--) {
            dp[i] = nums[i - 1] - ;
            int minNum = Integer.MAX_VALUE;

        }
    }
}
