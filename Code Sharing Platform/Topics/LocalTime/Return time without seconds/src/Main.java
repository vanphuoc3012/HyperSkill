
import java.time.LocalTime;
import java.util.Scanner;

class Main {
    public static void main(String[] args) {
        // put your code here
        Scanner scanner = new Scanner(System.in);
        String input = String.valueOf(scanner.nextLine());
        String[] parts = input.split(":");
        int hours = Integer.valueOf(parts[0]);
        int minutes = Integer.valueOf(parts[1]);
//        int seconds = Integer.valueOf(parts[2]);

        LocalTime localTime = LocalTime.of(hours, minutes);
        System.out.println(localTime);
    }
}