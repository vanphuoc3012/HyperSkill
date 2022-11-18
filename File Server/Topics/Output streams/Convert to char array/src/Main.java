import java.io.CharArrayWriter;
import java.io.IOException;

class Converter {
    public static char[] convert(String[] words) throws IOException {
        // implement the method
        CharArrayWriter c = new CharArrayWriter();
        for(String w : words) {
            c.write(w);
        }
        return c.toCharArray();
    }
}