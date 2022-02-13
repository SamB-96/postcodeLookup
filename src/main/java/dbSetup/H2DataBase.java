package dbSetup;

import postcodeLookup.DBConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.Properties;

public class H2DataBase {

    private static H2DataBase instance = null;

    private final String insertString = "INSERT INTO POSTCODES VALUES(?, ?)";

    public static H2DataBase getInstance() {
        if (instance==null) {
            instance = new H2DataBase();
        }
        return instance;
    }

    /**
     * Used to set up the database when main starts running. Creates the postcodes table and inserts the data from a file
     */
    public H2DataBase() {
        try {
            Connection conn = DBConnection.getConnection();
            Statement statement = conn.createStatement();
            statement.execute("CREATE TABLE POSTCODES(POSTCODE VARCHAR PRIMARY KEY, DELIVERIES VARCHAR);");

            PreparedStatement preparedStatement;

            Properties props = ReadFile.readFile("DeliveryDataset.properties");
            for (Object key : props.keySet()) {
                preparedStatement = conn.prepareStatement(insertString);
                preparedStatement.setString(1, key.toString());
                preparedStatement.setString(2, props.get(key).toString());
                preparedStatement.executeUpdate();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
