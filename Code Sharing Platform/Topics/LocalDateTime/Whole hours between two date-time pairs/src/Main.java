import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Scanner;

class Main {
    public static void main(String[] args) {
        // put your code here
        Scanner scanner = new Scanner(System.in);

        String input1 = String.valueOf(scanner.nextLine());
        String input2 = String.valueOf(scanner.nextLine());

        String inputDate1 = input1.split("T")[0];
        String inputTime1 = input1.split("T")[1];

        String inputDate2 = input2.split("T")[0];
        String inputTime2 = input2.split("T")[1];

        LocalDateTime dateTime1 = LocalDateTime.of(LocalDate.parse(inputDate1), LocalTime.parse(inputTime1));
        LocalDateTime dateTime2 = LocalDateTime.of(LocalDate.parse(inputDate2), LocalTime.parse(inputTime2));

        long i = Duration.between(dateTime1, dateTime2).abs().toHours();

        System.out.println(i);

    }
}