package com.tww.test.arithmetic.tail_recursion;

public class Test {

    public static void main(String[] args) {
        System.out.println(factorialTailRecursion(1,5).invoke());
    }

    /**
     * @param factorial
     * @param number
     * @return
     */
    public static TailRecursion<Long> factorialTailRecursion(final long factorial, final long number) {
        if (number == 1) {
            return TailInvoke.done(factorial);
        } else {
            return TailInvoke.call(() -> factorialTailRecursion(factorial * number, number - 1));
        }
    }
}
