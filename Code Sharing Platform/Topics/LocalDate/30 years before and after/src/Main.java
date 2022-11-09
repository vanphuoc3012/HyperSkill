import java.time.LocalDate;
import java.util.Scanner;

class Main {
    public static void main(String[] args) {
        // put your code here
        Scanner scanner = new Scanner(System.in);
        String input = String.valueOf(scanner.nextLine());
        String[] parts = input.split("-");
        int year = Integer.valueOf(parts[0]);
        int month = Integer.valueOf(parts[1]);
        int date = Integer.valueOf(parts[2]);


        LocalDate localDate = LocalDate.of(year, month, date);
        System.out.println(localDate.minusYears(30));
        System.out.println(localDate.plusYears(30));
    }
}