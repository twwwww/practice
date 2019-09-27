import java.util.Scanner;

public class Main {


    /**
     *
     *
     *
     * @param args
     * @throws Exception
     */
    public static void main(String args[]) throws Exception {
        Scanner cin = new Scanner(System.in);
        int N = cin.nextInt(), m = cin.nextInt();

        int[] moneys = new int[m];
        int[] ups = new int[m];

        for (int i = 0; i < m; i++) {
            moneys[i] = cin.nextInt();
            ups[i] = cin.nextInt();
        }


        System.out.println();
    }
}
