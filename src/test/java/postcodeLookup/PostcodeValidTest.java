package postcodeLookup;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Arrays;
import java.util.Collection;

import static org.junit.Assert.assertEquals;

@RunWith(value = Parameterized.class)
public class PostcodeValidTest {

    private final String postcode;
    private final Boolean expectedResult;

    public PostcodeValidTest(String postcodeString, Boolean expectedResult) {
        this.postcode = postcodeString;
        this.expectedResult = expectedResult;
    }

    @Parameterized.Parameters
    public static Collection postcodes() {
        return Arrays.asList(new Object[][]{
                {"A9 9AA", true},
                {"A9A 9AA", true},
                {"A99 9AA", true},
                {"AA9 9AA", true},
                {"AA9A 9AA", true},
                {"AA99 9AA", true},
                {"AA9A9AA", true},
                {"A9A9AA", true},
                {"A999AA", true},
                {"AA99AA", true},
                {"AA9 A9AAA", false},
                {"A9 9A", false},
                {"A9A", false},
                {"AAA9AA 9AA", false},
                {"A!99AA", false},
                {"", false},
        });
    }

    @Test
    public void testPostcodeIsValid() {
        assertEquals(expectedResult, PostcodeUtils.isValidPostcode(postcode));
    }
}