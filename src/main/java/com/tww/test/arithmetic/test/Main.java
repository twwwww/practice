import com.google.common.collect.Lists;


import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        /*List<Integer> list = new ArrayList<>();
        while (in.hasNext()) {
            list.add(in.nextInt());
        }*/
        int arr[] = new int[]{-2,1,-3,4,-1,2,1,-5,4};
        int n = arr.length;

        if (n == 0) {
            System.out.println();
        }

        int[] tmp = new int[n];
        tmp[0] = arr[0];
        for (int i = 1; i < n; i++) {
            tmp[i] = arr[i] + tmp[i - 1];
        }

        int result = Integer.MIN_VALUE;

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < i; j++) {
                result = Integer.max(result,tmp[i] - tmp[j]);
            }
        }
        System.out.println(result);
    }

}