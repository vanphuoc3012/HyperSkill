import java.util.Scanner;
import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Set<Person> set = new HashSet();
        set.add(new Person(scanner.nextLine()));
        set.add(new Person(scanner.nextLine()));

        System.out.println(set.size());
    }
}

class Person {
    private String name;

    public Person(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Person person)) return false;
        return Objects.equals(name, person.name);
    }

//    @Override
//    public int hashCode() {
//        return Objects.hash(name);
//    }
}