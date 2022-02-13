package postcodeLookup;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PostcodeUtils {

    /**
     * This regex splits the postcode up into the following groups:
     *  group 0 = full postcode          -   AA9 9AA
     *  group 1 = Outward code + sector  -   AA9 9A
     *  group 2 = Outward code           -   AA9
     *  group 3 = Area                   -   AA
     */
    private final static String postcodeRegex = "((([A-Z]{1,2})[0-9][A-Z0-9]? ?)[0-9])[A-Z]{2}";


    /**
     * @param postcode - String
     * @return - Matcher - Using the postcode regex to split the postcode into groups
     */
    public static Matcher postcodeMatcher(String postcode) {
        Pattern regexPattern = Pattern.compile(postcodeRegex);
        Matcher matcher = regexPattern.matcher(postcode);
        matcher.matches();
        return matcher;
    }


    /**
     * @param postcode - String
     * @return - boolean - Returns true is postcode is valid format UK postcode
     */
    public static boolean isValidPostcode(String postcode) {
        return postcode.matches(postcodeRegex);
    }
}
