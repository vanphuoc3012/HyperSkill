import java.time.LocalTime;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        // write your code here
        String input1 = String.valueOf(scanner.nextLine());
        String input2 = String.valueOf(scanner.nextLine());

        String[] parts1 = input1.split(":");
        String[] parts2 = input2.split(":");

        LocalTime localTime1 = LocalTime.of(Integer.valueOf(parts1[0]), Integer.valueOf(parts1[1]), Integer.valueOf(parts1[2]));
        LocalTime localTime2 = LocalTime.of(Integer.valueOf(parts2[0]), Integer.valueOf(parts2[1]), Integer.valueOf(parts2[2]));

        int x = localTime1.toSecondOfDay();
        int y = localTime2.toSecondOfDay();

        System.out.println(Math.abs(x-y));
    }
}