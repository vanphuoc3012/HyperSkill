package sorting;

import java.util.*;

public class Main {
    public static void main(final String[] args) {
        Scanner scanner = new Scanner(System.in);

        List<Long> list = new ArrayList<>();

        while (scanner.hasNextLong()) {
            long number = scanner.nextLong();
            // write your code here
            list.add(number);
        }

        System.out.println("Total numbers: "+list.size());
        long max = Collections.max(list);
        System.out.println("The greatest number: "+max+"("+Collections.frequency(list, max)+" time(s)).");
    }
}
