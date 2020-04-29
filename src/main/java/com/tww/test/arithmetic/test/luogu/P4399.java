package com.tww.test.arithmetic.test.luogu;

import java.util.List;
import java.util.Scanner;

import com.google.common.collect.Lists;

public class P4399 {


    /**
     * ljt12138首先建了n个特斯拉电磁塔，这些电塔排成一排，从左到右依次标号为1到n，第i个电塔的高度为h[i]。
     *
     * 建筑大师需要从中选出一些电塔，然后这些电塔就会缩到地下去。这时候，如果留在地上的电塔的高度，从左向右构成了一个等差数列，那么这个选择方案就会被认为是美观的。
     *
     * 建筑大师需要求出，一共有多少种美观的选择方案，答案模998244353。
     *
     * 注意，如果地上只留了一个或者两个电塔，那么这种方案也是美观的。地上没有电塔的方案被认为是不美观的。
     *
     *
     * todo:先跳过
     *
     * @param args
     * @throws Exception
     */
    public static void main(String args[]) throws Exception {
        Scanner cin = new Scanner(System.in);
        int T = cin.nextInt(), n = cin.nextInt();

        List<Food> foods = Lists.newArrayList();
        for (int i = 0; i < n; i++) {
            Food food = new Food();
            food.a = cin.nextInt();
            foods.add(food);
        }

        for (int i = 0; i < n; i++) {
            foods.get(i).b = cin.nextInt();
        }

        for (int i = 0; i < n; i++) {
            foods.get(i).c = cin.nextInt();
        }


        System.out.println();
    }

    private static class Food {
        private Integer a;
        private Integer b;
        private Integer c;
    }

    /**
     *
     * 8
     * 13 14 6 20 27 34 34 41
     *
     * 50
     *
     *
     * 100
     * 90 1004 171 99 1835 108 81 117 141 126 135 144 81 153 193 81 962 162 1493 171 1780 864 297 180 532 1781 189 1059 198 333 1593 824 207 1877 216 270 225 1131 336 1875 362 234 81 288 1550 243 463 1755 252 406 261 270 279 288 1393 261 1263 297 135 333 872 234 881 180 198 81 225 306 180 90 315 81 81 198 252 81 297 1336 1140 1238 81 198 297 661 81 1372 469 1132 81 126 324 333 342 81 351 481 279 1770 1225 549
     *
     * 11153
     *
     */
}
