# Courier Tracking System

This is a Java console application that simulates a courier tracking system. The application allows users to track parcels, manage delivery status, and update customer information using a MySQL database.

## Features

- **Parcel Tracking:**

  - Enter a parcel tracking number to get status updates.
  - View detailed information about the parcel's journey.
  - Receive notifications for key status changes.

- **Delivery Status Management:**

  - Update delivery status for parcels.
  - View delivery history for parcels.
  - Calculate estimated delivery times.

- **Customer Information Management:**
  - Register new customers.
  - View customer details.
  - Update customer information.
  - Delete customer accounts.

## Prerequisites

Before you begin, ensure you have met the following requirements:

- Java Development Kit (JDK) 8 or higher
- MySQL Server
- MySQL Connector/J (JDBC Driver)
- An IDE or build tool (e.g., IntelliJ IDEA, Eclipse, NetBeans) or a terminal/command prompt to compile and run Java programs.

## Setup Instructions

### 1. Clone the Repository

### 2. Set Up the MySQL Database

    Install and configure MySQL Server.

    Create a database named courier_system.


            CREATE DATABASE courier_system;
            USE courier_system;


    Create the required tables:


            CREATE TABLE Customer (
                customer_id INT AUTO_INCREMENT PRIMARY KEY,
                customer_name VARCHAR(255) NOT NULL,
                email VARCHAR(255),
                phone_number VARCHAR(20) NOT NULL
            );

            CREATE TABLE Parcel (
                parcel_id INT AUTO_INCREMENT PRIMARY KEY,
                tracking_number VARCHAR(50) NOT NULL UNIQUE,
                sender_name VARCHAR(255) NOT NULL,
                sender_address VARCHAR(255),
                recipient_name VARCHAR(255) NOT NULL,
                recipient_address VARCHAR(255),
                current_status VARCHAR(50),
                delivery_history TEXT,
                date_shipped DATETIME                ------FOR ESTIMATED DELIVERY TIME------
            );

### 3. Download and Add MySQL Connector/J

        Download the MySQL Connector/J (JDBC Driver) from the official MySQL website.

        Add the mysql-connector-java-<version>.jar file to your project:

        Eclipse: Right-click on project > Build Path > Add External Archives.

### 4. Update Database Credentials

        Open the DatabaseConnection.java file.
        Replace the placeholders with your actual database credentials:

        <!--  // Replace with your database name as in this case "courier_system". -->
        private static final String URL = "jdbc:mysql://localhost:3306/courier_system";

        <!-- // Replace with your MySQL username -->
        private static final String USER = "yourUsername";

        <!--  // Replace with your MySQL password -->
        private static final String PASSWORD = "yourPassword";


### 5. Compile and Run the Application

        Using an IDE: Open the project and run the CourierTrackingSystem class.

### Usage Instructions

        Run the Application: Start the application from your IDE or terminal.

        Select an Option from the Menu:
                Enter the corresponding number for the desired operation.
                Follow the prompts to input the required information.

### Example Commands
Track a Parcel: Enter the tracking number to view the status.
Update Delivery Status: Enter the parcel's tracking number and the new status.
Register a New Customer: Provide customer details (name, email, phone number).
View Customer Details: Enter the customer ID to view information.
Delete a Customer Account: Enter the customer ID to delete the account.

### Custom Exception Handling
The application uses custom exceptions for better error management:

 ApplicationException: For general application errors.
 DataNotFoundException: When a requested parcel or customer is not found.
 DatabaseConnectionException: For handling database connection issues.
 These exceptions provide user-friendly messages and guide the user in case of errors.

### Troubleshooting

        Database Connection Error: Ensure MySQL server is running, and your credentials in DatabaseConnection.java are correct.
        JDBC Driver Not Found: Make sure the MySQL Connector/J JAR file is included in your project classpath.
        SQL Errors: Check your SQL syntax, table names, and column names.
