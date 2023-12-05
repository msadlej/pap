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

    public void closeConnection(Connection connection) {
        try {
            connection.close();
            System.out.println("Connection to SQLite has been terminated.");
        }
        catch (SQLException e){
            System.out.println(e.getMessage());
        }
    }

    public int getRowCount(String tableName) {
        String sql = "SELECT COUNT(*) FROM " + tableName;

        try (Connection conn = this.connect();
             PreparedStatement statement = conn.prepareStatement(sql);
             ResultSet result = statement.executeQuery()) {

            if (result.next()) {
                return result.getInt(1);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
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

            try (ResultSet results = statement.executeQuery()){
                int row_count = 0;
                while (results.next()) {
                    row_count++;
                }
                book_array = new String[row_count][5];
                results.beforeFirst();
                int index = 0;
                while (results.next()) {
                    book_array[index][0] = results.getString("book_id");
                    book_array[index][1] = results.getString("title");
                    book_array[index][2] = results.getString("author");
                    book_array[index][3] = results.getString("date_published");
                    book_array[index][4] = results.getString("genre");
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

            int rows_affected = statement.executeUpdate();

            if (rows_affected > 0) {
                System.out.println("Book with book_id " + book_id + " removed successfully.");
            } else {
                System.out.println("Book with book_id " + book_id + " not found.");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void removeBook(Book new_book) {
        String sql = "DELETE FROM books WHERE book_id = ?";

        try (Connection conn = this.connect();
             PreparedStatement statement = conn.prepareStatement(sql)) {

            int book_id = new_book.getId();
            statement.setInt(1, book_id);

            int rows_affected = statement.executeUpdate();

            if (rows_affected > 0) {
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

            int rows_affected = statement.executeUpdate();

            if (rows_affected > 0) {
                System.out.println("Book added successfully.");
            } else {
                System.out.println("Failed to add book.");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void addBook(String title, String author, String genre, String publish_date){
        int book_id = getRowCount("books") + 1;
        addBook(book_id, title, author, genre, publish_date);
    }
    public void addBook(Book new_book) {
        String sql = "INSERT INTO books (book_id, title, author, genre, publish_date) VALUES (?, ?, ?, ?, ?)";

        try (Connection conn = this.connect();
             PreparedStatement statement = conn.prepareStatement(sql)) {

            statement.setInt(1, new_book.getId());
            statement.setString(2, new_book.getTitle());
            statement.setString(3, new_book.getAuthor());
            statement.setString(4, new_book.getGenre());
            statement.setString(5, new_book.getPublishDate());

            int rows_affected = statement.executeUpdate();

            if (rows_affected > 0) {
                System.out.println("Book added successfully.");
            } else {
                System.out.println("Failed to add book.");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void addUser(int user_id, String username, String password, String first_name, String last_name, String email, String user_type){
        String sql = "INSERT INTO users (user_id, username, password, first_name, last_name, email, user_type) VALUES (?, ?, ?, ?, ?, ?, ?)";

        try (Connection conn = this.connect();
             PreparedStatement statement = conn.prepareStatement(sql)) {

            statement.setInt(1, user_id);
            statement.setString(2, username);
            statement.setString(3, password);
            statement.setString(4, first_name);
            statement.setString(5, last_name);
            statement.setString(6, email);
            statement.setString(7, user_type);

            int rows_affected = statement.executeUpdate();

            if (rows_affected > 0) {
                System.out.println("User added successfully.");
            } else {
                System.out.println("Failed to add user.");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void addUser(String username, String password, String first_name, String last_name, String email, String user_type){
        int user_id = getRowCount("users") + 1;
        addUser(user_id, username, password, first_name, last_name, email, user_type);
    }

    public void addUser(User new_user){
        String sql = "INSERT INTO users (user_id, username, password, first_name, last_name, email, user_type) VALUES (?, ?, ?, ?, ?, ?, ?)";

        try (Connection conn = this.connect();
             PreparedStatement statement = conn.prepareStatement(sql)) {

            statement.setInt(1, new_user.getId());
            statement.setString(2, new_user.getUsername());
            statement.setString(3, new_user.getPassword());
            statement.setString(4, new_user.getFirstName());
            statement.setString(5, new_user.getLastName());
            statement.setString(6, new_user.getEmail());
            statement.setString(7, new_user.getRole());

            int rows_affected = statement.executeUpdate();

            if (rows_affected > 0) {
                System.out.println("User added successfully.");
            } else {
                System.out.println("Failed to add user.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
