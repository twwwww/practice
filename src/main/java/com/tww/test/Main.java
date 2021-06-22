package com.tww.test;

import java.util.Arrays;
import java.util.Map;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) {
        //candy(new int[]{1,0,2});
        //cuttingRope(6);
        cherryPickup(new int[][]{new int[]{0,1,-1},new int[]{1,0,-1},new int[]{1,1,1}});
    }

    public static int cherryPickup(int[][] grid) {

        int[] nums = new int[];
        Map<Integer, Long> mapper = Arrays.stream(nums)
                .sorted()
                .boxed()
                .collect(Collectors.groupingBy(i -> i, Collectors.counting()));
        int[][] newNums = mapper.entrySet().stream().sorted(Map.Entry.comparingByKey())
                .map(item -> new int[]{item.getKey(), item.getValue().intValue()}).toArray(int[][]::new);

        int m = grid.length;
        if (m == 1) {
            return grid[0][0];
        }
        int[][][] dp = new int[m][m][2];

        boolean is1 = grid[0][0] == 1;
        if (!is1) {
            grid[0][0] = 1;
        }

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < m; j++) {
                if (i == 0 && j == 0) {
                    dp[i][j][0] = grid[i][j];
                    dp[i][j][1] = grid[i][j];
                } else if (grid[i][j] == -1) {
                    dp[i][j][0] = 0;
                    dp[i][j][1] = 0;
                } else if (i == 0) {
                    dp[i][j][0] = dp[i][j - 1][0] > 0 ? dp[i][j - 1][0] + grid[i][j] : 0;
                    dp[i][j][1] = dp[i][j][0];
                } else if (j == 0) {
                    dp[i][j][0] = dp[i - 1][j][0] > 0 ? dp[i - 1][j][0] + grid[i][j] : 0;
                    dp[i][j][1] = dp[i][j][0];
                } else {
                    int dp0 = Integer.max(dp[i - 1][j][0], dp[i][j - 1][0]);
                    dp[i][j][0] = dp0 > 0 ? dp0 + grid[i][j] : 0;
                    int dp1 = Integer.max(dp[i - 1][j][0] + dp[i][j - 1][0], Integer.max(dp[i - 1][j][1], dp[i][j - 1][1]));
                    dp[i][j][1] = dp1 > 0 ? dp1 + grid[i][j] : 0;
                }
            }
        }
        int result = dp[m - 1][m - 1][1] <= 0 || is1 ? dp[m - 1][m - 1][1] : dp[m - 1][m - 1][1] - 2;
        return result;

    }

    private int binarySearchFirstGt(int[] arr,int findNum) {

        int length = arr.length;
        int left = 0;
        int right = length - 1;
        while(left <= right) {
            int mid = (left+right) / 2;
            if(arr[mid] > findNum){
                right = mid-1;
            } else if (arr[mid] <= findNum) {
                left = mid+1;
            }
        }
        return left;

    }

}