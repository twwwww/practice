package com.tww.test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) {
        //maxSatisfied(new int[]{1,0,1,2,1,1,7,5},new int[]{0,1,0,1,0,1,0,1},3);
        removeKdigits("10001",4);
        //cherryPickup(new int[][]{new int[]{0,1,-1},new int[]{1,0,-1},new int[]{1,1,1}});
    }

    public static String removeKdigits(String num, int k) {
        List<Integer> list = Arrays.stream(num.split("")).map(Integer::new).collect(Collectors.toList());
        LinkedList<Integer> stack = new LinkedList<>();
        List<Integer> removeIndex = new ArrayList<>();
        stack.push(0);
        a : for (int i = 1; i < list.size(); i++) {
            while (!stack.isEmpty() && list.get(stack.peek()) > list.get(i)) {
                Integer pop = stack.pop();
                removeIndex.add(pop);
                if (removeIndex.size() >= k) {
                    break a;
                }
            }
            stack.push(i);
        }

        if (removeIndex.size() < k) {
            for (int i = list.size() - 1; i >= list.size() - 1 - k + removeIndex.size() + 1; i--) {
                removeIndex.add(i);
            }
        }

        StringBuilder sb = new StringBuilder();
        boolean zero = true;
        for (int i = 0; i < list.size() && sb.length() < num.length() - k; i++) {
            if (!removeIndex.contains(i)) {
                if (list.get(i) != 0 || !zero) {
                    sb.append(list.get(i));
                    zero = false;
                }
            }
        }

        return sb.length() > 0 ? sb.toString() : "0";
    }

}