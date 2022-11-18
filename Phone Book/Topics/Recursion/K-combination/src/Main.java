import java.util.Scanner;

public class Main {

    public static int comb(int n, int k) {
        System.out.println("comb: "+n+" ---"+k);
        // write your code here
        if(k > n ) {
            return 0;
        } else if (k == 0) {
            return 1;
        } else if (k == 1) {
            return n;
        } else if(k == 2) {
            return fraction(n) / (2*fraction(n-k));
        }

        return comb(n-1, k) + comb(n-1, k-1);
    }

    public static int fraction(int x) {
        if(x <= 1) {
            return 1;
        }
        return x * fraction(x-1);
    }

    /* Do not change code below */
    public static void main(String[] args) {
        final Scanner scanner = new Scanner(System.in);
        final int n = scanner.nextInt();
        final int k = scanner.nextInt();
        System.out.println(comb(n, k));
    }
}