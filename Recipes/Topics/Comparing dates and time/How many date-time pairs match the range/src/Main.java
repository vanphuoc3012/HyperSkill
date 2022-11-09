import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Scanner;

class Main {
    public static void main(String[] args) {
        // put your code here
        Scanner scanner = new Scanner(System.in);

        LocalDateTime rangeFrom = LocalDateTime.parse(scanner.nextLine());
        LocalDateTime rangeTo = LocalDateTime.parse(scanner.nextLine());
        if(rangeFrom.isAfter(rangeTo)) {
            LocalDateTime temp = rangeFrom;
            rangeFrom = rangeTo;
            rangeTo = temp;
        }

        int n = Integer.valueOf(scanner.nextLine());
        int count = 0;

        while(n > 0) {
            LocalDateTime compare = LocalDateTime.parse(scanner.nextLine());
            if(compare.isEqual(rangeFrom) || (compare.isAfter(rangeFrom) && compare.isBefore(rangeTo))) {
                count++;
            }
            n--;
        }

        System.out.println(count);
    }
}