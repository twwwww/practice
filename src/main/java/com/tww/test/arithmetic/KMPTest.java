/*package com.tww.test.arithmetic;

public class KMPTest {

    public static void main(String[] args) {

    }

    *//**
     * Base Case : dp[0][pattern.chartAt(0)] = 1
     *//*
    static class KMP {
        private String pattern;
        private int[][] dp;

        public KMP(String pattern) {
            this.pattern = pattern;
            dp = new int[pattern.length()][256];//ASCII码
            int X = 0;//影子状态
            for (int i = 1; i < pattern.length(); i++) {
                for (int j = 0; j < 256; j++) {
                    if (pattern.charAt(i) == j) {
                        dp[i][j] = i + 1;
                    } else {
                        dp[i][j] = dp[X][];
                    }
                    X =
                }
            }
        }

        public boolean search() {
            return false;
        }
    }
}*/
