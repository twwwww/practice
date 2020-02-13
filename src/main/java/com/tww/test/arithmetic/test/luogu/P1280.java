package com.tww.test.arithmetic.test.luogu;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class P1280 {

    /**
     * dp[x] 表示从x往后时间能获取的最大空闲时间
     * dp[x] = dp[]
     *
     * 边界问题
     * @param args
     * @throws Exception
     */
    public static void main(String args[]) throws Exception {

        Scanner cin = new Scanner(System.in);
        int T = cin.nextInt();
        int N = cin.nextInt();

        Map<Integer,List<Time>> timeMap = new HashMap<>();

        for (int i = 0; i < N; i++) {
            Time time = new Time();
            time.start = cin.nextInt();
            time.time = cin.nextInt();
            time.end = time.start + time.time - 1;
            List<Time> timeList = timeMap.getOrDefault(time.start, new ArrayList<>());
            timeList.add(time);
            timeMap.put(time.start,timeList);
        }

        int[] dp = new int[T + 2];
        dp[T + 1] = 0;

        for (int i = T; i >= 0 ; i--) {
            List<Time> timeList;
            int f = i + 1;
            do {
                timeList = timeMap.get(f);
                f++ ;
            }
            while (timeList == null && f <= T);

            if (timeList == null || timeList.isEmpty()) {
                dp[i] = T - i;
            } else {
                int maxFreeTime = 0;
                for (Time time : timeList) {
                    maxFreeTime = Integer.max(maxFreeTime, (time.end >= T ? 0 : dp[time.end]) + time.start - i - 1);
                }
                dp[i] = maxFreeTime;
            }
        }

        System.out.println(dp[0]);

    }

    private static class Time {
        private int start;
        private int time;
        private int end;
    }

}
