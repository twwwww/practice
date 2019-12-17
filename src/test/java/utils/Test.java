package utils;

import com.google.common.collect.Lists;

import java.util.List;

public class Test {

    public static void main(String[] args) {
        List<Integer> list1 = Lists.newArrayList();

        List<Integer> list2 = Lists.newArrayList();

        list1.removeIf(list2::contains);
        list1.forEach(customerId -> {
            System.out.println(customerId + ",");
        });
    }
}
