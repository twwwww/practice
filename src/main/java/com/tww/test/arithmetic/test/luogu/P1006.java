package com.tww.test.arithmetic.test.luogu;

import java.util.Scanner;

public class P1006 {

    /**
     *
     * @param args
     * @throws Exception
     */
    public static void main(String args[]) throws Exception {
        Scanner cin = new Scanner(System.in);
        int m = cin.nextInt();
        int n = cin.nextInt();

        int[][] foods = new int[m][n];

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                foods[i][j] = cin.nextInt();
            }
        }
    }


}
