import java.time.LocalTime;
import java.util.Scanner;

class Main {
    public static void main(String[] args) {
        // put your code here
        Scanner scanner = new Scanner(System.in);
        String input1 = String.valueOf(scanner.nextLine());
        String input2 = String.valueOf(scanner.nextLine());

        String[] parts1 = input1.split(":");
        String[] parts2 = input2.split(" ");

        LocalTime time1 = LocalTime.of(Integer.valueOf(parts1[0]), Integer.valueOf(parts1[1]));
//        LocalTime time2 = LocalTime.of(Integer.valueOf(parts2[0]), Integer.valueOf(parts2[1]));

        time1 = time1.minusHours(Integer.valueOf(parts2[0]));
        time1 = time1.minusMinutes(Integer.valueOf(parts2[1]));
        System.out.println(time1);
    }
}