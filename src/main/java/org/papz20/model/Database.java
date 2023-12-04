package main.java.org.papz20.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.ResultSet;

public class Database {
    private Connection connect() {
        String url = "jdbc:sqlite:src/main/resources/database/appDB.db";
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(url);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return conn;
    }
    public Connection connectDB() {
        try {
            Connection conn = this.connect();
            System.out.println("Connection to SQLite has been established.");
            return conn;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
