package com.tww.test.arithmetic;

public class NewtonIteration {

    /**
     *
     * @param args
     */
    public static void main(String[] args) {
        double arg = 6.189965478e79;
        double result = squareRoot(arg);
        System.out.println(arg + " ===> " + result + " : " + result * result);
        System.out.println("Math.sqrt result ===> " + Math.sqrt(arg) + " : " + Math.sqrt(arg) * Math.sqrt(arg));
    }

    private static int loopCount = 0;

    /**
     * 牛顿迭代法求平方根
     * f(X) = X^2 - N; f(X`) = 2Xn; f(Xn) = Xn^2 - N f(Y) - f(Xn) = f(X`)*(Y - Xn) f(Y) = f(X`)*(Y - Xn)
     * + f(Xn) Y = Xn + (N - Xn^2 ) /2Xn Y = (Xn^2 + N) / 2Xn
     *
     * @param number
     * @return
     */
    public static double squareRoot(double number) {
        double err = 1e-15;
        double result = number;
        while (Math.abs(number - result * result) > err && loopCount < 100_000) {
            result = (result * result + number) / (2 * result);
            loopCount++;
            System.out.println("loopCount: " + loopCount + ", result ===> " + result + " : " + result * result);
        }
        return result;
    }
}
