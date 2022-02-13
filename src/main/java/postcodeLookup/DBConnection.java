package postcodeLookup;

import java.sql.*;

public class DBConnection {

    private static final String jdbcURL = "jdbc:h2:mem:cookDatabase";

    public static Connection getConnection() {
        Connection connection = null;
        try {
            connection = DriverManager.getConnection(jdbcURL);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return connection;
    }

}