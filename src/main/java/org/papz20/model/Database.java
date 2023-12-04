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

    public void connectDB() {
        try(Connection conn = this.connect()) {
            System.out.println("Connection to SQLite has been established.");
        }
        catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public void closeConnection(Connection connection) {
        try {
            connection.close();
            System.out.println("Connection to SQLite has been terminated.");
        }
        catch (SQLException e){
            System.out.println(e.getMessage());
        }
    }

    public String[][] selectBooks(String title_key, String author_key, String genre_key) {
        String[][] book_array = null;
        String query = "SELECT * FROM books WHERE title LIKE ? AND author LIKE ? AND genre LIKE ?";

        if (!title_key.equals("%")) {
            title_key = "%" + title_key + "%";
        }
        if (!author_key.equals("%")) {
            author_key = "%" + author_key + "%";
        }
        if (!genre_key.equals("%")) {
            genre_key = "%" + genre_key + "%";
        }

        try (Connection conn = this.connect();
             PreparedStatement statement = conn.prepareStatement(query)){

            statement.setString(1, title_key);
            statement.setString(2, author_key);
            statement.setString(3, genre_key);

            try (ResultSet query_results = statement.executeQuery()){
                int row_count = 0;
                while (query_results.next()) {
                    row_count++;
                }
                book_array = new String[row_count][5];
                query_results.beforeFirst();
                int index = 0;
                while (query_results.next()) {
                    book_array[index][0] = query_results.getString("book_id");
                    book_array[index][1] = query_results.getString("title");
                    book_array[index][2] = query_results.getString("author");
                    book_array[index][3] = query_results.getString("date_published");
                    book_array[index][4] = query_results.getString("genre");
                    index++;
                }
            }
        }
        catch (SQLException e){
            e.printStackTrace();
        }
        return book_array;
    }

    public void removeBook(int book_id) {
        String sql = "DELETE FROM books WHERE book_id = ?";

        try (Connection conn = this.connect();
             PreparedStatement statement = conn.prepareStatement(sql)) {

            statement.setInt(1, book_id);

            int rowsAffected = statement.executeUpdate();

            if (rowsAffected == 1) {
                System.out.println("Book with book_id " + book_id + " removed successfully.");
            } else {
                System.out.println("Book with book_id " + book_id + " not found.");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void addBook(int book_id, String title, String author, String genre, String publish_date) {
        String sql = "INSERT INTO books (book_id, title, author, genre, publish_date) VALUES (?, ?, ?, ?, ?)";

        try (Connection conn = this.connect();
             PreparedStatement statement = conn.prepareStatement(sql)) {

            statement.setInt(1, book_id);
            statement.setString(2, title);
            statement.setString(3, author);
            statement.setString(4, genre);
            statement.setString(5, publish_date);

            int rowsAffected = statement.executeUpdate();

            if (rowsAffected == 1) {
                System.out.println("Book added successfully.");
            } else {
                System.out.println("Failed to add the book.");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void alterTable() {
    }

    public void insert(String name, String email, String password) {
    }


}
