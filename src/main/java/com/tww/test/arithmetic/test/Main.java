import com.google.common.collect.Lists;

import java.util.List;
import java.util.Scanner;

public class Main {


    /**
     * 一共有n件食材，每件食材有三个属性，ai，bi和ci，如果在t时刻完成第i样食材则得到ai-t*bi的美味指数，用第i件食材做饭要花去ci的时间。
     *
     *
     *
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
}
