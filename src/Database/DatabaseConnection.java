package Database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {

    private DatabaseConnection() {};

    private static final String databaselink = "jdbc:mysql://localhost:3306/oop";
    private static final String username = "root";
    private static final String password = "root";

    private static DatabaseConnection instance;

    public Connection getConnection() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            return DriverManager.getConnection(databaselink, username, password);
        } catch (ClassNotFoundException | SQLException e) {
            throw new RuntimeException("Error encountered when establishing a connection to the database", e);
        }
    }

    public static DatabaseConnection getInstance(){
        if(instance == null){
            instance = new DatabaseConnection();
        }
        return instance;
    }
}

