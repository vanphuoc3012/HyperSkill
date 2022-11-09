import java.time.LocalDate;
import java.util.Scanner;

class Main {
    public static void main(String[] args) {
        // put your code here
        Scanner scanner = new Scanner(System.in);

        String input = String.valueOf(scanner.nextLine());

        LocalDate date = LocalDate.parse(input.split(" ")[0]);

        int plus = Integer.valueOf(input.split(" ")[1]);

        System.out.println(date.plusDays(plus).isEqual(date.plusYears(1).withMonth(1).withDayOfMonth(1)));
    }
}