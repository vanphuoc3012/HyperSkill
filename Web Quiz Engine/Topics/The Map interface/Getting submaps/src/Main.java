import com.sun.source.tree.Tree;

import java.util.Scanner;
import java.util.SortedMap;
import java.util.TreeMap;

class Main {
    public static void main(String[] args) {
        // put your code here
        Scanner scanner = new Scanner(System.in);

        String range = String.valueOf(scanner.nextLine());
        int from = Integer.valueOf(range.split(" ")[0]);
        int to = Integer.valueOf(range.split(" ")[1]);

        int size = Integer.valueOf(scanner.nextLine());

        SortedMap<Integer, String> map = new TreeMap<>();

        for(int i = 0; i < size; i++){
            String input = String.valueOf(scanner.nextLine());
            int key = Integer.valueOf(input.split(" ")[0]);
            String value = String.valueOf(input.split(" ")[1]);
            map.put(key, value);
        }

        map.subMap(from, to+1).forEach((key, value) -> System.out.println(key+" "+value));
    }
}