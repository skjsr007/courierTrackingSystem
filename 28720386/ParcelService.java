import ExceptionHandling.ApplicationException;
import ExceptionHandling.DataNotFoundException;
import ExceptionHandling.DatabaseConnectionException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;



    public class ParcelService {
        private static Connection connection;
        private static Scanner scanner = new Scanner(System.in);

        static {
            try {
                connection = DatabaseConnection.getConnection();
            } catch (DatabaseConnectionException e) {
                System.out.println("Failed to connect to the database: " + e.getMessage());
                System.exit(1); // Exit if the database connection fails
            }
        }

        public static void trackParcel() throws ApplicationException {
            System.out.print("Enter parcel tracking number: ");
            String trackingNumber = scanner.nextLine();

            try {
                String query = "SELECT * FROM Parcel WHERE tracking_number = ?";
                PreparedStatement preparedStatement = connection.prepareStatement(query);
                preparedStatement.setString(1, trackingNumber);
                ResultSet resultSet = preparedStatement.executeQuery();

                if (resultSet.next()) {
                    System.out.println("Parcel ID: " + resultSet.getInt("parcel_id"));
                    System.out.println("Sender Name: " + resultSet.getString("sender_name"));
                    System.out.println("Recipient Name: " + resultSet.getString("recipient_name"));
                    System.out.println("Current Status: " + resultSet.getString("current_status"));
                } else {
                    throw new DataNotFoundException("Parcel with tracking number " + trackingNumber + " not found.");
                }
            } catch (SQLException e) {
                throw new ApplicationException("Error: Unable to track the parcel. Please try again.", e);
            } catch (DataNotFoundException e) {
                System.out.println(e.getMessage());
            }
        }
        public static void viewDetailedParcelJourney() throws ApplicationException {
            System.out.print("Enter parcel tracking number: ");
            String trackingNumber = scanner.nextLine();

            try {
                String query = "SELECT delivery_history FROM Parcel WHERE tracking_number = ?";
                PreparedStatement preparedStatement = connection.prepareStatement(query);
                preparedStatement.setString(1, trackingNumber);
                ResultSet resultSet = preparedStatement.executeQuery();

                if (resultSet.next()) {
                    System.out.println("Delivery History: " + resultSet.getString("delivery_history"));
                } else {
                    throw new DataNotFoundException("Parcel with tracking number " + trackingNumber + " not found.");
                }
            } catch (SQLException e) {
                throw new ApplicationException("Error: Unable to retrieve parcel journey details.", e);
            } catch (DataNotFoundException e) {
                System.out.println(e.getMessage());
            }
        }

        // Method to update the delivery status of a parcel
        public static void updateDeliveryStatus() throws ApplicationException {
            System.out.print("Enter parcel tracking number: ");
            String trackingNumber = scanner.nextLine();
            System.out.print("Enter new delivery status: ");
            String newStatus = scanner.nextLine();

            try {
                String query = "UPDATE Parcel SET current_status = ? WHERE tracking_number = ?";
                PreparedStatement preparedStatement = connection.prepareStatement(query);
                preparedStatement.setString(1, newStatus);
                preparedStatement.setString(2, trackingNumber);
                int rowsAffected = preparedStatement.executeUpdate();

                if (rowsAffected > 0) {
                    System.out.println("Delivery status updated successfully.");
                } else {
                    throw new DataNotFoundException("Parcel with tracking number " + trackingNumber + " not found.");
                }
            } catch (SQLException e) {
                throw new ApplicationException("Error: Unable to update delivery status.", e);
            } catch (DataNotFoundException e) {
                System.out.println(e.getMessage());
            }
        }

        // Method to view delivery history of a parcel
        public static void viewDeliveryHistory() throws ApplicationException {
            System.out.print("Enter parcel tracking number: ");
            String trackingNumber = scanner.nextLine();

            try {
                String query = "SELECT delivery_history FROM Parcel WHERE tracking_number = ?";
                PreparedStatement preparedStatement = connection.prepareStatement(query);
                preparedStatement.setString(1, trackingNumber);
                ResultSet resultSet = preparedStatement.executeQuery();

                if (resultSet.next()) {
                    System.out.println("Delivery History: " + resultSet.getString("delivery_history"));
                } else {
                    throw new DataNotFoundException("Parcel with tracking number " + trackingNumber + " not found.");
                }
            } catch (SQLException e) {
                throw new ApplicationException("Error: Unable to retrieve delivery history.", e);
            } catch (DataNotFoundException e) {
                System.out.println(e.getMessage());
            }
        }

        // Method to calculate estimated delivery time for a parcel
        public static void calculateEstimatedDeliveryTime() throws ApplicationException {
            System.out.print("Enter parcel tracking number: ");
            String trackingNumber = scanner.nextLine();

            try {
                String query = "SELECT current_status FROM Parcel WHERE tracking_number = ?";
                PreparedStatement preparedStatement = connection.prepareStatement(query);
                preparedStatement.setString(1, trackingNumber);
                ResultSet resultSet = preparedStatement.executeQuery();

                if (resultSet.next()) {
                    String currentStatus = resultSet.getString("current_status");
                    System.out.println("Current Status: " + currentStatus);
                    System.out.println("Estimated Delivery Time: 2-3 days from current status.");
                } else {
                    throw new DataNotFoundException("Parcel with tracking number " + trackingNumber + " not found.");
                }
            } catch (SQLException e) {
                throw new ApplicationException("Error: Unable to calculate estimated delivery time.", e);
            } catch (DataNotFoundException e) {
                System.out.println(e.getMessage());
            }
        }
    }

