import ExceptionHandling.ApplicationException;
import ExceptionHandling.DataNotFoundException;
import ExceptionHandling.DatabaseConnectionException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class customerService {
    private static Connection connection;
    private static Scanner scanner = new Scanner(System.in);

    static {
        try {
            connection = DatabaseConnection.getConnection();
        } catch (DatabaseConnectionException e) {
            throw new RuntimeException(e);
        }
    }

    public static void registerNewCustomer() throws ApplicationException {
        System.out.print("Enter customer name: ");
        String customerName = scanner.nextLine();
        System.out.print("Enter email: ");
        String email = scanner.nextLine();
        System.out.print("Enter phone number: ");
        String phoneNumber = scanner.nextLine();

        try {
            String query = "INSERT INTO Customer (customer_name, email, phone_number) VALUES (?, ?, ?)";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, customerName);
            preparedStatement.setString(2, email);
            preparedStatement.setString(3, phoneNumber);
            int rowsAffected = preparedStatement.executeUpdate();

            if (rowsAffected > 0) {
                System.out.println("Customer registered successfully.");
            } else {
                throw new ApplicationException("Failed to register the customer. Please try again.");
            }
        } catch (SQLException e) {
            throw new ApplicationException("Error: Unable to register new customer. Please try again.", e);
        } catch (ApplicationException e) {
            throw new RuntimeException(e);
        }
    }
    // Method to view customer details by ID
    public static void viewCustomerDetails() throws ApplicationException {
        System.out.print("Enter customer ID: ");
        int customerId = Integer.parseInt(scanner.nextLine());

        try {
            String query = "SELECT * FROM Customer WHERE customer_id = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, customerId);
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                System.out.println("Customer ID: " + resultSet.getInt("customer_id"));
                System.out.println("Customer Name: " + resultSet.getString("customer_name"));
                System.out.println("Email: " + resultSet.getString("email"));
                System.out.println("Phone Number: " + resultSet.getString("phone_number"));
            } else {
                throw new DataNotFoundException("Customer with ID " + customerId + " not found.");
            }
        } catch (SQLException e) {
            throw new ApplicationException("Error: Unable to view customer details. Please try again.", e);
        } catch (DataNotFoundException e) {
            System.out.println(e.getMessage());
        }
    }
    // Method to update customer information
    public static void updateCustomerInformation() throws ApplicationException {
        System.out.print("Enter customer ID: ");
        int customerId = Integer.parseInt(scanner.nextLine());
        System.out.print("Enter new customer name: ");
        String newCustomerName = scanner.nextLine();
        System.out.print("Enter new email: ");
        String newEmail = scanner.nextLine();
        System.out.print("Enter new phone number: ");
        String newPhoneNumber = scanner.nextLine();

        try {
            String query = "UPDATE Customer SET customer_name = ?, email = ?, phone_number = ? WHERE customer_id = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, newCustomerName);
            preparedStatement.setString(2, newEmail);
            preparedStatement.setString(3, newPhoneNumber);
            preparedStatement.setInt(4, customerId);
            int rowsAffected = preparedStatement.executeUpdate();

            if (rowsAffected > 0) {
                System.out.println("Customer information updated successfully.");
            } else {
                throw new DataNotFoundException("Customer with ID " + customerId + " not found.");
            }
        } catch (SQLException e) {
            throw new ApplicationException("Error: Unable to update customer information.", e);
        } catch (DataNotFoundException e) {
            System.out.println(e.getMessage());
        }
    }
    // Method to delete a customer account
    public static void deleteCustomer() throws ApplicationException {
        System.out.print("Enter customer ID: ");
        int customerId = Integer.parseInt(scanner.nextLine());

        try {
            String query = "DELETE FROM Customer WHERE customer_id = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, customerId);
            int rowsAffected = preparedStatement.executeUpdate();

            if (rowsAffected > 0) {
                System.out.println("Customer account deleted successfully.");
            } else {
                throw new DataNotFoundException("Customer with ID " + customerId + " not found.");
            }
        } catch (SQLException e) {
            throw new ApplicationException("Error: Unable to delete customer account.", e);
        } catch (DataNotFoundException e) {
            System.out.println(e.getMessage());
        }
    }
}


