package com.tww.test.datastructure.heap;

import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;

public class HeapTest1 {
    /**
     * 给定一个会议时间安排的数组，每个会议时间都会包括开始和结束的时间  [[ s1 , e1 ] ，[ s2 , e2 ]，…] (si < ei) ，
     * 为避免会议冲突，同时要考虑充分利用会议室资源，请你计算至少需要多少间会议室，才能满足这些会议安排。
     *
     * 假设每一个会议一个房间，当一个会议开始是可以根据其他会议的结束时间判断是否可以使用更早结束的房间
     * 开始时间排序，遍历开始时间，是否有之前的房间处于结束状态，有就使用之前的房间，
     * @param args
     */
    public static void main(String[] args) {
        int[][] meetings = new int[][]{{3,7},{5,8},{2,3},{2,5},{3,4}};
        int rooms1 = solution1(meetings);
        int rooms2 = solution2(meetings);
        System.out.println("need rooms1 : " + rooms1);
        System.out.println("need rooms2 : " + rooms2);
    }

    /**
     * time:O(nlogn) + O(nlogn)
     * space:O(n)
     * @return
     */
    private static int solution1(int[][] meetings) {
        Arrays.sort(meetings, Comparator.comparingInt(m -> m[0]));
        PriorityQueue<Integer> pq = new PriorityQueue<>(meetings.length);
        pq.offer(meetings[0][1]);
        for (int i = 1; i < meetings.length; i++) {
            if (meetings[i][0] >= pq.peek()) {
                pq.poll();
            }
            pq.offer(meetings[i][1]);
        }
        return pq.size();
    }

    /**
     * time:O(nlogn) + O(n)
     * space:O(n)
     * @param meetings
     * @return
     */
    private static int solution2(int[][] meetings) {
        int[] start = new int[meetings.length];
        int[] end = new int[meetings.length];
        for (int i = 0; i < meetings.length; i++) {
            start[i] = meetings[i][0];
            end[i] = meetings[i][1];
        }
        Arrays.sort(start);
        Arrays.sort(end);
        int k = 0;
        for (int i = 1; i < meetings.length; i++) {
            if (start[i] >= end[k]) {
                ++k;
            }
        }
        return meetings.length - k;

    }
}
