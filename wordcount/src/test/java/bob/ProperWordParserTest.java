package bob;

import org.junit.Test;

import java.util.List;

import static junit.framework.Assert.assertEquals;

/**
 * User: Jeffrey M Lutz
 * Date: 3/21/13
 */
public class ProperWordParserTest {

    @Test
    public void successfullyParseSimpleWords() {
        // pre-condition
        ProperWordParser parser = new ProperWordParser();
        String line = "the quick brown fox.";
        String[] expectedWords = {"the", "quick", "brown", "fox"};

        // perform test
        List<String> actualWords = parser.parseWords(line);

        // post condition / assertions
        assertEquals(expectedWords.length,actualWords.size());
        for(int i=0; i<expectedWords.length; i++) {
            assertEquals(expectedWords[i], actualWords.get(i));
        }
    }

    @Test(expected = NullPointerException.class)
    public void throwsExceptionAttemptingToParseEmptyLine() {
        // pre-condition
        ProperWordParser parser = new ProperWordParser();
        String line = null;

        // perform test
        List<String> actualWords = parser.parseWords(line);

        // post condition / assertions
    }
}
