package com.wipro.mockitodemo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class App {

    private static final String JDBC_URL = "jdbc:mysql://localhost:3306/wiprodb";
    private static final String DB_USER = "root";
    private static final String DB_PASSWORD = "Janhvee@12";

    public static void main(String[] args) {
        insertEmployee("Janhvee", "EXTC");
        readEmployees();  // <-- Call the function to read and display data
    }

    // Function to insert a record
    public static void insertEmployee(String name, String department) {
        try (Connection connection = DriverManager.getConnection(JDBC_URL, DB_USER, DB_PASSWORD);
             PreparedStatement preparedStatement = connection.prepareStatement(
                     "INSERT INTO employee (emp_name, department) VALUES (?, ?)")) {

            Class.forName("com.mysql.cj.jdbc.Driver");

            preparedStatement.setString(1, name);
            preparedStatement.setString(2, department);

            int rowsInserted = preparedStatement.executeUpdate();

            if (rowsInserted > 0) {
                System.out.println("✅ A new employee was inserted successfully!");
            }

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // ✅ Function to read data
    public static void readEmployees() {
        try (Connection connection = DriverManager.getConnection(JDBC_URL, DB_USER, DB_PASSWORD);
             PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM employee");
             ResultSet resultSet = preparedStatement.executeQuery()) {

            System.out.println("\nEmployee Table Data:");
            System.out.println("-----------------------------");

            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String empName = resultSet.getString("emp_name");
                String department = resultSet.getString("department");

                System.out.println("ID: " + id + ", Name: " + empName + ", Department: " + department);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
