import java.io.BufferedReader;
import java.io.InputStreamReader;

class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        // start coding here
        String text = reader.readLine();
        String[] parts = text.split("");
        for(int i = parts.length - 1; i >=0; i--) {
            System.out.print(parts[i]);
        }

        reader.close();
    }
}