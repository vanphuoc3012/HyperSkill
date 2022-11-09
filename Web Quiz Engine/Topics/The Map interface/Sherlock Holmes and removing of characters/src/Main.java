import java.util.*;

class Main {
    public static void main(String[] args) {
        // put your code here
        Scanner scanner = new Scanner(System.in);
        int count = 0;

        SortedMap<String, Integer> map1 = new TreeMap<>();
        SortedMap<String, Integer> map2 = new TreeMap<>();

        String s1 = String.valueOf(scanner.nextLine());
        String s2 = String.valueOf(scanner.nextLine());

        String[] arr1 = s1.toLowerCase().split("");
        String[] arr2 = s2.toLowerCase().split("");

        for(String word : arr1){
            int value = 1;
            if(map1.containsKey(word)){
                value = map1.get(word) + 1;
            }
            map1.put(word, value);
        }
        for(String word : arr2){
            int value = 1;
            if(map2.containsKey(word)){
                value = map2.get(word) + 1;
            }
            map2.put(word, value);
        }

        if (Objects.equals(map1, map2)){
            System.out.println(count);
        } else {
           for(String t : map1.keySet()){
               if(map2.containsKey(t) && map1.get(t) == map2.get(t)){
                   continue;
               }
               int value1 = map1.getOrDefault(t,0);
               int value2 = map2.getOrDefault(t,0);

               if(value1 > value2){
                   count += value1 - value2;
               }
           }

            for(String t : map2.keySet()){
                if(map1.containsKey(t) && map1.get(t) == map2.get(t)){
                    continue;
                }
                int value1 = map1.getOrDefault(t,0);
                int value2 = map2.getOrDefault(t, 0);

                if(value2 > value1){
                    count += value2 - value1;
                }
            }
            System.out.println(count);
        }
    }
}