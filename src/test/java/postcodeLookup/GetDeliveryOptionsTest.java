package postcodeLookup;

import dbSetup.H2DataBase;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Arrays;
import java.util.Collection;

import static org.junit.Assert.assertArrayEquals;

@RunWith(value = Parameterized.class)
public class GetDeliveryOptionsTest {

    private final String postcode;
    private final String[] expectedResult;

    public GetDeliveryOptionsTest(String postcodeString, String expectedResult) {
        this.postcode = postcodeString;
        this.expectedResult = expectedResult.split(",");
    }

    @Parameterized.Parameters
    public static Collection postcodes() {
        return Arrays.asList(new Object[][]{
                {"TN91AP", "No Deliveries" },
                {"ME102AA", "Collect from Kitchen" },
                {"ME103HH ", "Collect from Kitchen,Delivery from Sittingbourne" },
                {"ME91AA ", "No Deliveries" },
                {"W1N4DJ ", "Delivery by Courier" },
                {"TN91AB ", "Delivery from Warehouse" },
                {"TN155AB ", "Collect from Sevenoaks" },
                {"TN12QP ", "Van Delivery,Collect from Tunbridge Wells" },
                {"TN123QE ", "Delivery by Courier" },
                {"TN131AB ", "Delivery from Sevenoaks,Collect from Sevenoaks" }
        });
    }

    @Test
    public void testGetDeliveryOptions() {
        H2DataBase.getInstance();
        PostcodeLookup lookup = new PostcodeLookup();
        assertArrayEquals(expectedResult, lookup.getValidDeliveryOptions(postcode));
    }
}