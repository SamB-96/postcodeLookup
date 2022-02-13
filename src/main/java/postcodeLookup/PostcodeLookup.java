package postcodeLookup;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.regex.Matcher;

public class PostcodeLookup implements IPostcodeLookup {

    /**
     * @param postcode - String
     * @return - String[] - Array with each delivery option for the postcode
     */
    @Override
    public String[] getValidDeliveryOptions(String postcode) {
        String postcodeString = postcode.replaceAll(" ", "");
        return getDeliveryOptions(postcodeString).split(",");
    }


    /**
     * @param postcode - String
     * @return - String - String containing all delivery options for given postcode
     */
    private String getDeliveryOptions(String postcode) {
        Matcher m = PostcodeUtils.postcodeMatcher(postcode);
        String availableDeliveries = null;

        for (int i = 0; i < 4; i++) {
            availableDeliveries = selectDeliveriesFromDB(m.group(i));
            if (availableDeliveries != null) {
                return availableDeliveries;
            }
        }
        return selectDeliveriesFromDB("DEFAULT");
    }


    /**
     * @param postcode - String
     * @return - String - Delivery options for given postcode
     */
    public String selectDeliveriesFromDB(String postcode) {
        try (Connection conn = DBConnection.getConnection()) {
            String readString = "SELECT DELIVERIES FROM POSTCODES WHERE POSTCODE LIKE ?";
            PreparedStatement preparedStatement = conn.prepareStatement(readString);
            preparedStatement.setString(1, postcode);

            ResultSet rs = preparedStatement.executeQuery();
            if (rs.next()) {
                return rs.getString(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
