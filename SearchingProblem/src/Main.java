import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import java.util.concurrent.TimeUnit;

public class Main {
    static int[] array = new int[1000];

    public static void main(String[] args) throws IOException {
//        Scanner scanner = new Scanner(System.in);
//
//        String pathToFile = "C:\\Users\\Admin\\Downloads\\dataset_91069.txt";
//
//        List<Integer> list1 = new ArrayList<>();
//        List<Integer> list2 = new ArrayList<>();
//
//        String input1 = "27 26 25 23 22 21 20 17 15 14 12 11 8 7 6 5 3 2 1 0";
//        for(String s : input1.split(" ")) {
//            list1.add(Integer.valueOf(s));
//        }
//
//        String input2 = "0 1 5 7 11 13 14 16 26 28";
//        for(String s : input2.split(" ")) {
//            list2.add(Integer.valueOf(s));
//        }
//
//        for (int i : list2) {
//            System.out.print(jumpSearchDecs(list1, i)+" ");
//        }
//
//
//    }
//
//    public static int jumpSearchDecs(List<Integer> listDecs, int value) {
//        if(listDecs.isEmpty()) {
//            return -1;
//        }
//        int jump = (int) Math.sqrt(listDecs.size());
//        int currIndex = 1;
//        int preIndex = 1;
//
//        int last = listDecs.size();
//
//        while(listDecs.get(currIndex) > value) {
//            if(listDecs.get(currIndex) == last) {
//                return -1;
//            }
//            preIndex = currIndex;
//            currIndex = Math.min(currIndex + jump, last - 1);
//        }
//
//        while (listDecs.get(currIndex) < value) {
//            currIndex--;
//            if(currIndex < preIndex) {
//                return -1;
//            }
//        }
//        if(listDecs.get(currIndex) == value) {
//            return currIndex;
//        }
//        return -1;

//        System.out.println(isAPowerOf2(10));
//        long start = System.currentTimeMillis();
//        System.out.println(fib(30));
//        long end = System.currentTimeMillis();

        String s = "700, 810, 930, 1100, 1230, 1250, 1310, 1330";
        String[] parts = s.split(", ");
        int[] arr = new int[parts.length];
        for(int i = 0; i < arr.length; i++) {
            arr[i] = Integer.valueOf(parts[i]);
        }

        System.out.println(binarySearch(arr, 1230, 0, arr.length-1));
    }

    public static boolean isAPowerOf2(double n) {
        System.out.println("method call");
        if(n == 1) {
            return true;
        } if(n > 1 && n < 2) {
            return false;
        } else {
            return isAPowerOf2(n / 2);
        }
    }

    public static int fib(int n) {
        System.out.println("Fibo");
        if(n <= 1) {
            return n;
        }
        return fib(n - 1) + fib(n - 2);
    }

    public static int fibo2(int n) {
        System.out.println("Fibo2");
        if(n <= 1) {
            return n;
        }
        if(array[n] != 0) {
            return array[n];
        } else {
            array[n] = fibo2(n-1)+fibo2(n-2);
        }
        return array[n];
    }

    public static int binarySearch(int[] array,int value, int min, int max) {
        while (min <= max) {
            int mid = (min+max) / 2;
            System.out.println("---aaa");
            if(value == array[mid]) {
                return mid;
            } else if(value < array[mid]) {
                max = mid - 1;
            } else {
                min = mid + 1;
            }
        }
        return -1;
    }
}