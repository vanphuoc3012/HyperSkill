package phonebook;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Time;
import java.util.*;
import java.util.concurrent.TimeUnit;

public class Main {
    public static void main(String[] args) {

        String pathToDirectory = "C:\\Users\\Admin\\Downloads\\directory.txt";
        String pathToFind = "C:\\Users\\Admin\\Downloads\\find.txt";

        File file1 = new File(pathToDirectory);
        File file2 = new File(pathToFind);

        List<Contact> directories = new ArrayList<>();
        List<String> findList = new ArrayList<>();

        Map<String, Contact> map = new HashMap<>();

        try {
            Scanner scannerDirectory = new Scanner(file1);
            Scanner scannerFind = new Scanner(file2);

            while(scannerDirectory.hasNext()) {
                String nextLine = scannerDirectory.nextLine();
                String[] parts = nextLine.split(" ");
                String name = parts[1];
                String phoneNumber = parts[0];

                if(parts.length == 3) {
                    name =  name + " " +parts[2];
                }

                directories.add(new Contact(name, phoneNumber));
                map.put(name, new Contact(name, phoneNumber));
            }

            while (scannerFind.hasNext()) {
                String name = scannerFind.nextLine();
                findList.add(name);
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        System.out.println("sizer directories: " +directories.size());

//        long startQuickSort = System.currentTimeMillis();
        List<Contact> listTest = quickSort(directories, 0, directories.size()-1);
//        long endQuickSort = System.currentTimeMillis();




//        linear search
        System.out.println("Start searching (linear search)...");
        int foundLinearSearch = 0;
        long startLinearSearch = System.currentTimeMillis();
        for(String name : findList) {
            for(Contact s : directories) {
                if(s.equals(name)) {
                    foundLinearSearch++;
                }
            }
        }
        long endLinearSearch = System.currentTimeMillis();
        long timeLinearSearch = endLinearSearch - startLinearSearch;
        System.out.print("Found "+foundLinearSearch+" / 500 entries. ");
        System.out.print(timeTaken(startLinearSearch, endLinearSearch));
        System.out.println();

        System.out.println("Start searching (bubble sort + jump search)...");
        long startSort = System.currentTimeMillis();
        int last = directories.size() - 1;
        while (last > 0) {
//            System.out.print(last +" === ");
            for(int i = 1; i < last; i++) {
                Contact temp = directories.get(i - 1);
                if(temp.getName().compareTo(directories.get(i).getName()) > 0) {
                    directories.set(i-1, directories.get(i));
                    directories.set(i, temp);
                }
            }
            if(System.currentTimeMillis() - startSort > timeLinearSearch * 2) {
                break;
            }
            last--;
        }
        long endSort = System.currentTimeMillis();

        int foundJumpSearch1 = 0;
        long startLinearSearch1 = System.currentTimeMillis();
        for(String name : findList) {
            if(jumpSearch(listTest, name) != -1) {
                foundJumpSearch1++;
            }
        }
        long endLinearSearch1 = System.currentTimeMillis();
        System.out.print("Found "+foundJumpSearch1+" / 500 entries. ");
        System.out.println(timeTaken(startSort, endLinearSearch1));
        System.out.print(timeTaken(startSort, endSort).replace("Time taken:", "Sorting time:"));
//        System.out.println(" - STOPPED, moved to linear search");
        System.out.print(timeTaken(startLinearSearch1, endLinearSearch1).replace("Time taken:", "Searching time:"));


//        // quick sort
        System.out.println();
        System.out.println("Start searching (quick sort + binary search)...");

        long startQuickSort = System.currentTimeMillis();
        List<Contact> listTest1 = quickSort(directories, 0, directories.size()-1);
        long endQuickSort = System.currentTimeMillis();

        int binSearchCount = 0;
        long startBinSearch = System.currentTimeMillis();
        for(String s : findList) {
            if(binarySearch(listTest1, s, 0, listTest1.size()-1) != -1) {
                binSearchCount++;
            }
        }
        long endBinSearch = System.currentTimeMillis();
        System.out.print("Found "+binSearchCount+" / 500 entries. ");
        System.out.println(timeTaken(startQuickSort, endBinSearch));
        System.out.print(timeTaken(startQuickSort, endQuickSort).replace("Time taken:", "Sorting time:"));
        System.out.print(timeTaken(startBinSearch, endBinSearch).replace("Time taken:", "Searching time:"));

        System.out.println();
        System.out.println("Start searching (hash table)...");

        long startCreate = System.currentTimeMillis();
        try {
            Scanner scannerDirectory = new Scanner(file1);
            Scanner scannerFind = new Scanner(file2);

            while(scannerDirectory.hasNext()) {
                String nextLine = scannerDirectory.nextLine();
                String[] parts = nextLine.split(" ");
                String name = parts[1];
                String phoneNumber = parts[0];

                if(parts.length == 3) {
                    name =  name + " " +parts[2];
                }
                map.put(name, new Contact(name, phoneNumber));
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        long endCreate = System.currentTimeMillis();

        int binMapCount = 0;
        long startMapSearch = System.currentTimeMillis();
        for(String s : findList) {
            if(map.containsKey(s)) {
                binMapCount++;
            }
        }
        long endMapSearch = System.currentTimeMillis();
        System.out.print("Found "+binMapCount+" / 500 entries. ");
        System.out.println(timeTaken(startCreate, endMapSearch));
        System.out.print(timeTaken(startCreate, endCreate).replace("Time taken:", "Creating time:"));
        System.out.print(timeTaken(startMapSearch, endMapSearch).replace("Time taken:", "Searching time:"));

    }

    public static List<Contact> quickSort(List<Contact> list, int left, int right) {
//        System.out.println("List size: "+list.size());
//        System.out.println("Left: "+left+" === Right: "+right);

        int l = Integer.valueOf(left);
        int r = Integer.valueOf(right);
        Contact pivot = list.get((left+right) / 2);
        while (l <= r) {
//            System.out.println("loop");
            while (list.get(l).getName().toLowerCase().compareTo(pivot.getName().toLowerCase()) < 0) {
                l++;
//                System.out.println("l: "+l);
            }
            while (list.get(r).getName().toLowerCase().compareTo(pivot.getName().toLowerCase() ) > 0) {
                r--;
//                System.out.println("r: "+r);
            }
            if(l <= r) {
//                System.out.println("if");
                Contact temp = list.get(l);
                list.set(l, list.get(r));
                list.set(r, temp);
                l++;
                r--;
            }
        }

        if(left < r) {
            quickSort(list, left, r);
        }
        if(l < right) {
            quickSort(list, l, right);
        }

        return list;
    }

    public static int binarySearch(List<Contact> list, String name, int low, int high) {
        if(low > high) {
            return -1;
        } else {
            int mid = (low+high) / 2;
//            System.out.println("Low: "+low+" -- High: "+high+" -- Mid: "+mid);
            if(list.get(mid).equals(name)) {
                return mid;
            }
            if(name.toLowerCase().compareTo(list.get(mid).getName().toLowerCase()) < 0) {
                return binarySearch(list, name, low, mid -1);
            } else {
                return binarySearch(list, name, mid+1, high);
            }
        }
    }


    public static List<String> sortListBubbleSort(List<String> list) {
        int last = list.size() - 1;
        while (last > 0) {
//            System.out.println(last);
            for(int i = 1; i < last; i++) {
                String temp = list.get(i - 1);
                if(getName(temp).compareTo(list.get(i)) > 0) {
                    list.set(i-1, list.get(i));
                    list.set(i, temp);
                }
            }
            last--;
        }
        return list;
    }

    public static int jumpSearch(List<Contact> sortedList, String name) {
        int preIndex = 0;
        int currIndex = 0;
        int step = (int) Math.sqrt(sortedList.size());
        int lastIndex = sortedList.size() - 1;

        while (sortedList.get(currIndex).getName().toLowerCase().compareTo(name.toLowerCase()) < 0) {
            if(currIndex == lastIndex) {
                return -1;
            }
            preIndex = currIndex;
            currIndex = Math.min(currIndex + step, lastIndex);
        }

        while(sortedList.get(currIndex).getName().toLowerCase().compareTo(name.toLowerCase()) > 0) {
            currIndex--;
            if(currIndex < preIndex) {
                return -1;
            }
        }
        if(sortedList.get(currIndex).getName().toLowerCase().compareTo(name.toLowerCase()) == 0){
            return currIndex;
        }
        return -1;
    }

    public static String getName(String s) {
        String[] parts = s.split(" ");
        if(parts.length == 3) {
            return parts[1] + " " +parts[2];
        }
        return parts[1];
    }

    public static String timeTaken(long start, long end) {
        long take = end - start;
        long min = TimeUnit.MILLISECONDS.toMinutes(take);
        long sec = TimeUnit.MILLISECONDS.toSeconds(take) - TimeUnit.MINUTES.toSeconds(min);
        long milis = take - TimeUnit.MINUTES.toMillis(min) - TimeUnit.SECONDS.toMillis(sec);
        return "Time taken: "+min+" min. "+sec+" sec. "+milis+" ms.";
    }
}
