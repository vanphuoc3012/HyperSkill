import java.util.Optional;
import java.util.Scanner;

class Main {
    public static void main(String[] args) {
        InputStringReader reader = new InputStringReader();        
        Optional<String> value = reader.getValue();
        if (value.isPresent()) {
            System.out.println("Value is present: " + value.get());
        } else {
            System.out.println("Value is empty");
        }
    }
}

class InputStringReader {
    private Scanner scanner = new Scanner(System.in);
    private String input;

    public InputStringReader() {
        this.input = scanner.nextLine();
    }

    public Optional<String> getValue() {
        // implement
        Optional<String> o = Optional.ofNullable(input);
        if(input.equals("empty")){
            return Optional.empty();
        } else {
            return Optional.ofNullable(input);
        }
    }
}