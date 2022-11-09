import java.util.Objects;
import java.util.Scanner;
import java.util.SortedMap;
import java.util.TreeMap;

class Main {
    public static void main(String[] args) {
        // put your code here
        Scanner scanner = new Scanner(System.in);

        String s1 = String.valueOf(scanner.nextLine());
        String s2 = String.valueOf(scanner.nextLine());

        String[] parts1 = s1.toLowerCase().split("");
        String[] parts2 = s2.toLowerCase().split("");

        SortedMap<String, Integer> map1 = new TreeMap<>();
        SortedMap<String, Integer> map2 = new TreeMap<>();

        for(String s : parts1){
            int value = 1;
            if(map1.containsKey(s)){
                value = map1.get(s) + 1;
            }
            map1.put(s, value);
        }

        for(String s : parts2){
            int value = 1;
            if(map2.containsKey(s)){
                value = map2.get(s) + 1;
            }
            map2.put(s, value);
        }

        if(Objects.equals(map1, map2)){
            System.out.println("yes");
        } else {
            System.out.println("no");
        }
    }
}