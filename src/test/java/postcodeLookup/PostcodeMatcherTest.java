package postcodeLookup;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Arrays;
import java.util.Collection;
import java.util.regex.Matcher;

import static org.junit.Assert.assertEquals;

@RunWith(value = Parameterized.class)
public class PostcodeMatcherTest {

    private final String postcode;
    private final String area;
    private final String district;
    private final String sector;

    public PostcodeMatcherTest(String postcodeString, String area, String district, String sector) {
        this.postcode = postcodeString;
        this.area = area;
        this.district = district;
        this.sector = sector;
    }

    @Parameterized.Parameters
    public static Collection postcodes() {
        return Arrays.asList(new Object[][] {
            { "PO163AB", "PO163", "PO16", "PO"},
            { "ME88JZ",  "ME88",  "ME8",  "ME"},
            { "L1A5QW",  "L1A5",  "L1A",  "L"},
            { "SW11AA",  "SW11",  "SW1",  "SW"}
        });
    }

    @Test
    public void testPostcodeMatcher() {
        Matcher m = PostcodeUtils.postcodeMatcher(postcode);
        assertEquals(postcode, m.group(0));
        assertEquals(area, m.group(1));
        assertEquals(district, m.group(2));
        assertEquals(sector, m.group(3));
    }
}