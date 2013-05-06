package bob;

import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

/**
 * User: Jeffrey M Lutz
 * Date: 3/21/13
 */
public class ProperWordParser {
    private StringTokenizer tokenizer;
    private static final String DELIMITERS = " ,.:;\"\'`[](){}-...!?//<>=_@#$%^&*+";

    public ProperWordParser() {
        super();
    }

    public List<String> parseWords(String line) {

        tokenizer = new StringTokenizer(line, DELIMITERS);
        List<String> words = new ArrayList<String>();
        while(tokenizer.hasMoreTokens()) {
            words.add(tokenizer.nextToken());
        }
        return words;
    }
}
