import java.time.LocalDate;
import java.util.Scanner;

class Main {
    public static void main(String[] args) {
        // put your code here
        Scanner scanner = new Scanner(System.in);

        String input = String.valueOf(scanner.nextLine());
        String[] parts = input.split(" ");

        int year = Integer.valueOf(parts[0]);
        int dayOfYear = Integer.valueOf(parts[1]);

        LocalDate localDate = LocalDate.ofYearDay(year, dayOfYear);
        if(localDate.getDayOfMonth() == localDate.getMonth().maxLength() ||
                localDate.getDayOfMonth() == localDate.getMonth().minLength()){
            System.out.println(true);
        } else {
            System.out.println(false);
        }
    }
}