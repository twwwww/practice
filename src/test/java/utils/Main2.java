package utils;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.Stack;

public class Main2 {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);

        Stack<Double> stack = new Stack();


        String s = in.next();
        if (s.charAt(0) != '-') {
            s = "+" + s;
        }

        List<String> nums = new ArrayList<>();
        boolean isAdd = false;
        for (int i = 0; i < s.length(); i++) {

            if (s.charAt(i) == '+' || s.charAt(i) == '-' || s.charAt(i) == '*' || s.charAt(i) == '/') {
                if (isAdd) {
                    isAdd = false;
                } else {
                    nums.add(String.valueOf(s.charAt(i)));
                    isAdd = true;
                }
            } else {
                StringBuilder numStr = new StringBuilder();
                if (isAdd) {
                    numStr.append("-");
                }
                isAdd = false;
                do {
                    numStr.append(s.charAt(i));
                    i++;
                }
                while (i <= s.length() - 1 && s.charAt(i) != '+' && s.charAt(i) != '-' && s.charAt(i) != '*' && s.charAt(i) != '/');
                i--;
                nums.add(numStr.toString());
            }
        }

        for (int i = 0; i < (nums.size() / 2); i++) {
            String symbol = nums.get(2 * i);
            Integer num = Integer.valueOf(nums.get(2 * i + 1));
            if (symbol.equals("*")) {
                stack.push(stack.pop() * num);
            } else if (symbol.equals("/")) {
                stack.push(stack.pop()/num);
            } else if (symbol.equals("-")) {
                stack.push(Integer.valueOf(0 - num).doubleValue());
            } else {
                stack.push(num.doubleValue());
            }
        }

        Double result = 0d;
        while (!stack.empty()) {
            result += stack.pop();
        }
        System.out.println(result);
    }
}