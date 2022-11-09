import java.util.*;
import java.util.stream.Collectors;

class Age implements Comparable<Age>{
    private final int value;

    public Age(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    @Override
    public int compareTo(Age age) {

        return Integer.valueOf(this.getValue()).compareTo(age.getValue());
    }
}

// do not change the code below
class Main {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        List<Age> list = Arrays.stream(sc.nextLine().split(" "))
                .mapToInt(Integer::parseInt)
                .mapToObj(Age::new)
                .sorted()
                .collect(Collectors.toList());

        Checker.check(list);
    }
}

class Checker {
    static void check(List<Age> list) {
        for (int i = 1; i < list.size(); i++) {
            var curr = list.get(i);
            var prev = list.get(i - 1);
            if (curr.getValue() < prev.getValue()) {
                System.out.println("Incorrect sorted order");
                return;
            }
        }
        System.out.println("Correct sorted order");
    }
}