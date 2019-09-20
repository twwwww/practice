package com.tww.test.arithmetic.dp.by;

import java.util.Arrays;
import java.util.stream.Collectors;

public class ByTest5 {
    /**
     *2个人轮流拿数组里面的一个数，且要比另外一个人上一次拿的数大[a，b]，
     * 第一次拿要拿[a，b]范围的数，求第一个人拿的总和比第二个人的总和最多大多少
     *
     * 对num[i]排序
     * j:上次取值下标，k本次取的下标          (0 <= j,k <= i)
     * dp(j) =Min( num[k] - dp(k))  (num[j] + a <= num[k] <= num[j] + b)
     * Base Case:
     * dp(i) = num[i]
     * dp(0) 时 (num[j] + a <= num[k] <= num[j] + b)  num(j) = 0;
     *
     */
    public static void main(String[] args) {
        int [] nums = new int[]{1,3,-2,5,-3,6};
        int max = 2;
        int min = 1;
        nums = Arrays.stream(nums).filter(i -> i >= min).sorted().toArray();
        int[] dp =  new int[nums.length];
        dp[nums.length - 1] = nums[nums.length - 1];
        for (int i = nums.length - 2; i >= 0; i--) {
            int maxNum = Integer.MIN_VALUE;
            int minIndex = nums.length;
            int maxIndex = -1;
            for (int m = i; m < nums.length; m++) {
                if (nums[m] >= min + (i == 0 ? 0 : nums[i - 1])) {
                    minIndex = m;
                    break;
                }
            }
            for (int m = nums.length - 1; m > i - 1; m--) {
                if (nums[m] <= max + (i == 0 ? 0 : nums[i - 1])) {
                    maxIndex = m;
                    break;
                }
            }
            if (minIndex <= maxIndex) {
                for (int j = minIndex; j <= maxIndex ; j++) {
                    maxNum = Integer.max(maxNum, nums[j] - dp[j]);
                }
            }
            dp[i] =  maxNum;
        }
        System.out.println(dp[0]);
    }
}
