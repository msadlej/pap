package main.java.com.example.app;
import java.io.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.ResultSet;

import main.java.com.example.app.ui.MainPage;

public class App {
    public static void main(String[] args) throws IOException {
        System.out.println("Hello");
        String jdbcUrl = "jdbc:sqlite:test.db";
        try (Connection connection = DriverManager.getConnection(jdbcUrl);
             PreparedStatement createTableStatement = connection.prepareStatement(
                     "CREATE TABLE IF NOT EXISTS users (id INTEGER PRIMARY KEY, name TEXT)");
             PreparedStatement insertStatement = connection.prepareStatement(
                     "INSERT INTO users (name) VALUES (?)");
             PreparedStatement selectStatement = connection.prepareStatement(
                     "SELECT * FROM users")) {

            // Create table if not exists
            createTableStatement.execute();

            // Insert data
            insertStatement.setString(1, "John Doe");
            insertStatement.executeUpdate();
            insertStatement.setString(1, "Jane Smith");
            insertStatement.executeUpdate();

            System.out.println("Database created successfully.");

            // Query data
            ResultSet resultSet = selectStatement.executeQuery();
            System.out.println("Users in the database:");
            while (resultSet.next()) {
                int userId = resultSet.getInt("id");
                String userName = resultSet.getString("name");
                System.out.println("User ID: " + userId + ", Name: " + userName);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}