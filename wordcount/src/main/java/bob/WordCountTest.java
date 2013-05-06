package bob;

/**
 * User: Jeffrey M Lutz
 * Date: 3/21/13
 */

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.StringTokenizer;

public class WordCountTest {

    private static BufferedReader br;

    public static void main(String[] args) {

        try {

            String FILESOURCE = "BibleSample.txt";

            br = new BufferedReader(new FileReader(FILESOURCE));
            String strLine = "";
            StringTokenizer st = null;
            String DELIMITERS = " ,.:;\"\'`[](){}-...!?//<>=_@#$%^&*+\\t\\s";
            int val = 0;

            //read file line by line, if the line is not null
            while ((strLine = br.readLine()) != null) {

                //break line using the delimiter list, and do not return delimiters
                st = new StringTokenizer(strLine, DELIMITERS);

                while (st.hasMoreTokens()) {
                    String key = st.nextToken();
                    val++;
                    if (!key.matches("\\d{2,3}") && val < 7000) {
                        System.out.println(key + ", " + val);
                    }
                }
            }


        } catch (Exception e) {
            System.out.println("Exception while reading file: " + e);
        }
    }
}
