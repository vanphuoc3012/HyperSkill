import java.util.*;


class MapFunctions {

    public static void calcTheSamePairs(Map<String, String> map1, Map<String, String> map2) {
        // write your code here
        int count = 0;

        for (String k1 : map1.keySet()) {
            for (String k2 : map2.keySet()) {
                if (k1.equals(k2)) {
                    if (map1.get(k1).equals(map2.get(k2))) {
                        count++;
                    }
                }
            }
        }

        System.out.println(count);

    }
}