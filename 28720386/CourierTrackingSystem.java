import ExceptionHandling.ApplicationException;
import ExceptionHandling.DatabaseConnectionException;

import java.sql.Connection;
import java.util.Scanner;


public class CourierTrackingSystem {
    private static Connection connection;
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        try {
            connectToDatabase();

            int choice;
            do {
                displayMenu();
                choice = Integer.parseInt(scanner.nextLine());
                switch (choice) {
                    case 1:
                        int option;
                        System.out.println("1. Track Parcel");
                        System.out.println("2. View Detailed Parcel Journey");
                        System.out.println("Enter your option: ");
                        option = Integer.parseInt(scanner.nextLine());
                        switch (option) {
                            case 1:
                                ParcelService.trackParcel();
                                break;
                            case 2:
                                ParcelService.viewDetailedParcelJourney();
                                break;
                            default:
                                System.out.println("Invalid choice. Please try again.");
                        }
                        break;
                    case 2:
                        System.out.println("1. Update Delivery Status ");
                        System.out.println("2. View delivery history ");
                        System.out.println("3. Calculate estimated Delivery Time ");
                        System.out.println("Enter your option: ");
                        option = Integer.parseInt(scanner.nextLine());
                        switch (option) {
                            case 1:
                                  ParcelService.updateDeliveryStatus();
                                  break;

                            case 2:
                                  ParcelService.viewDeliveryHistory();
                                  break;

                            case 3:
                                  ParcelService.calculateEstimatedDeliveryTime();
                                  break;
                            default:
                                  System.out.println("Invalid choice. Please try again.");
                        }
                        break;

                    case 3:
                        System.out.println("1. Register New Customer ");
                        System.out.println("2. View Customer Details ");
                        System.out.println("3. Update Customer Details ");
                        System.out.println("4. Delete Customer Details ");
                        System.out.println("Enter your option: ");
                        option = Integer.parseInt(scanner.nextLine());

                        switch (option) {
                            case 1:
                                customerService.registerNewCustomer();
                                break;

                            case 2:
                                customerService.viewCustomerDetails();
                                break;

                            case 3:
                                customerService.updateCustomerInformation();
                                break;

                            case 4:
                                customerService.deleteCustomer();
                                break;
                            default:
                                System.out.println("Invalid choice. Please try again.");
                        }
                        break;
                    case 0:
                        System.out.println("Exiting the system.");
                        break;
                    default:
                        System.out.println("Invalid choice. Please try again.");
                }
            } while (choice != 0);
        } catch (DatabaseConnectionException e) {
            System.out.println("Error: Unable to connect to the database. " + e.getMessage());
        } catch (ApplicationException e) {
            System.out.println("An application error occurred: " + e.getMessage());
        }
    }

    private static void displayMenu() {
        System.out.println("\nCourier Tracking System Menu:");
        System.out.println("1. Parcel Tracking");
        System.out.println("2. Delivery Status Management");
        System.out.println("3. Customer Information Management");
        System.out.println("0. Exit");
        System.out.print("Enter your choice: ");
    }

    private static void connectToDatabase() throws DatabaseConnectionException {
        connection = DatabaseConnection.getConnection();
        if (connection != null) {
            System.out.println("Connected to the database.");
        }
    }
}