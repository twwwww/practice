package utils;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);

        int length = in.nextInt();
        String[] num = new String[2 * length - 2];

        int i = 0;
        int resultLength = 1;
        for (int j = 0; j < length - 1; j++) {
            String next = in.next();
            if (!next.equals(" ")) {
                if (next.equals("A")) {
                    num[i++] = "12";
                    num[i++] = "34";
                    resultLength += 2;
                } else if (next.equals("B")) {
                    num[i++] = "AB";
                    num[i++] = "CD";
                    resultLength += 2;
                } else {
                    num[i++] = next;
                    resultLength += 1;
                }
            }
        }

        System.out.print(resultLength);
        if (resultLength > 1) {
            System.out.print(" ");
        }
        for (int j = 0; j < resultLength - 1; j++) {
            System.out.print(num[j]);
            if (j < resultLength - 2) {
                System.out.print(" ");
            }
        }
    }
}