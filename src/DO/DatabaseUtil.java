package DO;

import Database.DatabaseConnection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DatabaseUtil {
    private Connection connection;



    public static ResultSet executeQuery(String query, Object... params) {
        try {
            PreparedStatement preparedStatement = prepareStatement(query, params);
            return preparedStatement.executeQuery();
        } catch (SQLException e) {
            System.out.println("Error: Unable to execute query.");
            e.printStackTrace();
            return null;
        }
    }

    public static int executeUpdate(String query, Object... params) {
        try {
            PreparedStatement preparedStatement = prepareStatement(query, params);
            return preparedStatement.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Error: Unable to execute update.");
            e.printStackTrace();
            return -1;
        }
    }

    private static PreparedStatement prepareStatement(String query, Object[] params) throws SQLException {
        Connection con = DatabaseConnection.getInstance().getConnection();
        PreparedStatement preparedStatement = con.prepareStatement(query);
        for (int i = 0; i < params.length; i++) {
            preparedStatement.setObject(i + 1, params[i]);
        }
        return preparedStatement;
    }

    public void closeConnection() {
        try {
            if (connection != null) {
                connection.close();
            }
        } catch (SQLException e) {
            System.out.println("Error: Unable to close the connection.");
            e.printStackTrace();
        }
    }
}
