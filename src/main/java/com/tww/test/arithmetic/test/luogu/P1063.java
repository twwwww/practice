package com.tww.test.arithmetic.test.luogu;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.concurrent.atomic.AtomicReference;

public class P1063 {


    /**
     *
     * 在Mars星球上，每个Mars人都随身佩带着一串能量项链。在项链上有N颗能量珠。能量珠是一颗有头标记与尾标记的珠子，这些标记对应着某个正整数。并且，对于相邻的两颗珠子，前一颗珠子的尾标记一定等于后一颗珠子的头标记。因为只有这样，通过吸盘（吸盘是Mars人吸收能量的一种器官）的作用，这两颗珠子才能聚合成一颗珠子，同时释放出可以被吸盘吸收的能量。如果前一颗能量珠的头标记为m，尾标记为r，后一颗能量珠的头标记为r，尾标记为n，则聚合后释放的能量为m * r * n（Mars单位），新产生的珠子的头标记为m，尾标记为n。
     *
     * 需要时，Mars人就用吸盘夹住相邻的两颗珠子，通过聚合得到能量，直到项链上只剩下一颗珠子为止。显然，不同的聚合顺序得到的总能量是不同的，请你设计一个聚合顺序，使一串项链释放出的总能量最大。
     *
     * 例如：设N=4，4颗珠子的头标记与尾标记依次为(2,3) (3,5) (5,10) (10,2)。我们用记号⊕表示两颗珠子的聚合操作，(j⊕k)表示第j,k两颗珠子聚合后所释放的能量。则第4、1两颗珠子聚合后释放的能量为：
     *
     * (4⊕1)=10 * 2 * 3=60=10×2×3=60。
     *
     * 这一串项链可以得到最优值的一个聚合顺序所释放的总能量为：
     *
     * ((4⊕1)⊕2)⊕3）=10 * 2 * 3+10 * 3 * 5+10 * 5 * 10=710。
     *
     * 输入格式
     * 第一行是一个正整数N(4≤N≤100)，表示项链上珠子的个数。第二行是N个用空格隔开的正整数，所有的数均不超过1000。第i个数为第i颗珠子的头标记(1≤i≤N)，当i<N时，第i颗珠子的尾标记应该等于第i+1颗珠子的头标记。第N颗珠子的尾标记应该等于第1颗珠子的头标记。
     *
     * 至于珠子的顺序，你可以这样确定：将项链放到桌面上，不要出现交叉，随意指定第一颗珠子，然后按顺时针方向确定其他珠子的顺序。
     *
     * 输出格式
     * 一个正整数E(E≤2.1 * (10)^9)，为一个最优聚合顺序所释放的总能量。
     *
     * a1:
     * 先去除小的值最终能获得更大的值
     *
     * a2:
     * dp[i][j] :i,j区间的能量
     *
     * dp[i][j] = Max(dp[i][k] + dp[k + 1][j] + arr[i] * arr[k + 1] * arr[j + 1]) i < k < j
     *
     * Base Case :
     * dp[i][i+1] = arr[i] * arr[i + 1] * arr[i + 2]
     *
     * dp[i][i] = 0;
     *
     * 断环为链：将长度为n的链复制一份接在后面，环的情况就是长度为2n的链中任意连续的长度为n的链,遍历间隔为n的区间取最大值。
     *
     * time complexity : n^3
     * space complexity: n^2
     *
     * @param args
     * @throws Exception
     */
    public static void main(String args[]) throws Exception {
        test2();
    }


    private static void test2() {
        Scanner cin = new Scanner(System.in);
        int n = cin.nextInt();

        int[] arrN = new int[2 * n];
        for (int i = 0; i < n; i++) {
            arrN[i] = arrN[i + n] = cin.nextInt();
        }

        int[][] dp = new int[2 * n][2 * n];


        for (int i = 0; i < n; i++) {
            for (int j = 0; j < 2 * n - i - 1; j++) {
                if (i == 0) {
                    dp[j][j + i] = 0;
                } else if (i == 1) {
                    dp[j][j + i] = arrN[j] * arrN[j + 1] * arrN[j + 2];
                } else {
                    Integer max = Integer.MIN_VALUE;
                    for (int k = j; k < j + i; k++) {
                        max = Integer.max(max,dp[j][k] + dp[k + 1][i + j] + arrN[j] * arrN[k + 1] * arrN[j + i + 1]);
                    }
                    dp[j][j + i] = max;
                }
            }
        }


        Integer max = Integer.MIN_VALUE;
        for (int i = 0; i < n; i++) {
            max = Integer.max(max,dp[i][i + n - 1]);
        }

        System.out.println(max);

    }

    // todo fail
    private static void test1() {
        Scanner cin = new Scanner(System.in);
        int n = cin.nextInt();

        List<Integer> arrN = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            arrN.set(i,cin.nextInt());
        }

        HashMap<Integer, List<Index>> hashMap = new HashMap<>();

        for (int i = 0; i < n; i++) {
            Integer preSufValue;
            if (i == 0) {
                preSufValue = arrN.get(n - 1) * arrN.get(1);
            } else if (i == n - 1) {
                preSufValue = arrN.get(n - 2) * arrN.get(0);
            } else {
                preSufValue = arrN.get(i - 1) * arrN.get(i + 1);
            }
            Index index = new Index();
            index.index = i;
            index.preSufValue = preSufValue;
            index.value = arrN.get(i);
            List<Index> orDefault = hashMap.getOrDefault(i, new ArrayList<>());
            orDefault.add(index);
            hashMap.put(i,orDefault);
        }

        AtomicReference<Long> result = new AtomicReference<>(0L);

        hashMap.entrySet().stream().sorted(Comparator.comparing(Map.Entry::getKey)).forEach(entry -> {
            List<Index> indexList = entry.getValue();
            indexList.stream().sorted(Comparator.comparing(Index::getPreSufValue).reversed()).forEach(index -> {
                result.updateAndGet(v -> v + index.preSufValue * index.value );

            });
        });


    }

    private static class Index {
        Integer index;
        Integer preSufValue;
        Integer value;

        public Integer getValue() {
            return value;
        }

        public void setValue(Integer value) {
            this.value = value;
        }

        public Integer getIndex() {
            return index;
        }

        public void setIndex(Integer index) {
            this.index = index;
        }

        public Integer getPreSufValue() {
            return preSufValue;
        }

        public void setPreSufValue(Integer preSufValue) {
            this.preSufValue = preSufValue;
        }
    }



    /**
     *
     * 4
     * 2 3 5 10
     * 60 150 500 -> 710
     *
     * 30 100 200 -> 330
     *
     * MIN
     * 100 30 12 -> 142
     *
     * 150 60 12 -> 222
     *
     *
     *
     * 710
     *
     * m^2 * n  (m,k)
     *
     *
     * 3 2 2 2 5
     *
     * 30 150 500 -> 680
     *
     *
     * 20 20 30
     * 8 12 30
     *
     */
}
