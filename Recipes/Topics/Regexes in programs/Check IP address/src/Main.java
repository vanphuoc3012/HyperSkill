import java.util.Scanner;

class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        // start coding here
        String x = String.valueOf(scanner.nextLine());

        String regex = "([0-1]?[0-9]?[0-9]|2[0-5][0-5])\\.([0-1]?[0-9]?[0-9]|2[0-5][0-5])\\.([0-1]?[0-9]?[0-9]|2[0-5][0-5])\\.([0-1]?[0-9]?[0-9]|2[0-5][0-5])";

        System.out.println(x.matches(regex) ? "YES" : "NO");
    }
}