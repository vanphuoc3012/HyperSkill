import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.Scanner;

class Main {
    public static void main(String[] args) {
        // put your code here
        Scanner scanner = new Scanner(System.in);
        String input = String.valueOf(scanner.nextLine());

        String[] parts = input.split(" ");
        int year = Integer.valueOf(parts[0]);
        int month = Integer.valueOf(parts[1]);

        LocalDate localDate = LocalDate.of(year, month, 1);
        int firstMonday = 8 - localDate.getDayOfWeek().getValue();


        if(localDate.getMonth() == localDate.plusDays(firstMonday).getMonth()){

            localDate = localDate.plusDays(firstMonday);
            System.out.println(localDate);
            while (localDate.plusWeeks(1).getMonth() == localDate.getMonth()){
                System.out.println(localDate.plusWeeks(1));
                localDate = localDate.plusWeeks(1);
            }
        }

    }
}