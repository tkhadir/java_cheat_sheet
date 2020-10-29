import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Regex {

    public static void main(final String[] args) {
        Regex r = new Regex();
        r.matchAnything();
        r.beginingAndEnding();
    }

    void matchAnything() {
        Pattern r = Pattern.compile(".");
        String anything = "hello world";
        Matcher m = r.matcher(anything);
        System.out.println(m.find());
    }

    void beginingAndEnding() {
        Pattern r = Pattern.compile("^hel");
        String word = "hello world";
        Matcher m = r.matcher(word);
        System.out.println(m.find());
        r = Pattern.compile("orld$");
        m = r.matcher(word);
        System.out.println(m.find());
    }
}
