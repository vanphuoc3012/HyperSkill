import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Scanner;

class Main {
    public static void main(String[] args) {
        // put your code here
        Scanner scanner = new Scanner(System.in);

        String input = String.valueOf(scanner.nextLine());
        String inputDate = input.split("T")[0];
        String inputTime = input.split("T")[1];

        LocalDate localDate = LocalDate.parse(inputDate);
        LocalTime localTime = LocalTime.parse(inputTime);

        LocalDateTime localDateTime = LocalDateTime.of(localDate, localTime);

        String secondLine = String.valueOf(scanner.nextLine());
        int subHour = Integer.valueOf(secondLine.split(" ")[0]);
        int addMinute = Integer.valueOf(secondLine.split(" ")[1]);

        System.out.println(localDateTime.minusHours(subHour).plusMinutes(addMinute));

    }
}