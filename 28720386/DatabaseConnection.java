import ExceptionHandling.DatabaseConnectionException;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {
    private static final String URL = "jdbc:mysql://localhost:3306/courier_system";
    private static final String USER = "root";
    private static final String PASSWORD = "Shubham@123";

    public static Connection getConnection() throws DatabaseConnectionException {
        try {
            // Load MySQL JDBC Driver
            Class.forName("com.mysql.cj.jdbc.Driver");

            // Establish the connection
            Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
//            System.out.println("Connected to the database successfully!");
            return connection;
        } catch (ClassNotFoundException e) {
            throw new DatabaseConnectionException("Error: MySQL JDBC Driver not found. Please ensure the JDBC driver is added to the project's classpath.", e);
        } catch (SQLException e) {
            String message = "Error: Unable to connect to the database.";
            throw new DatabaseConnectionException(message, e);
        }
    }
}

