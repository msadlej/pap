package main.java.org.papz20;
import main.java.org.papz20.ui.MainPage;

import java.io.*;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.ResultSet;

//import main.java.com.example.app.ui.MainPage;

public class App {
    private Connection connect() {
        // test.db is in src/main/java/com/example/app/database, and App is in src/main/java/com/example/app
        String url = "jdbc:sqlite:src/main/java/com/example/app/database/test.db";
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(url);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return conn;
    }
    public void connectDB() {
        try(Connection conn = this.connect()) {
            System.out.println("Connection to SQLite has been established.");
        }
        catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
    public static void main(String[] args) throws IOException {
        App app = new App();
        app.connectDB();
        MainPage ui = new MainPage();
        ui.setLocationRelativeTo(null);
        ui.setVisible(true);
    }
}