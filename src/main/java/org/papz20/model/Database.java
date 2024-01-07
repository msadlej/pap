package main.java.org.papz20.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.util.List;
import java.util.ArrayList;
import java.util.Objects;


public class Database {
    /// part: misc
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

    ///part: Book
    public List<String[]> selectBooks(String title_key, String author_key, String genre_key) {
        List<String[]> book_list = new ArrayList<>();

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

                while (results.next()) {
                    String[] book = new String[5];
                    book[0] = results.getString("book_id");
                    book[1] = results.getString("title");
                    book[2] = results.getString("author");
                    book[3] = results.getString("publish_date");
                    book[4] = results.getString("genre");
                    book_list.add(book);
                }
            }
        }
        catch (SQLException e){
            e.printStackTrace();
        }
        return book_list;
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

    public void removeBook(Book book) {
        String sql = "DELETE FROM books WHERE book_id = ?";

        try (Connection conn = this.connect();
             PreparedStatement statement = conn.prepareStatement(sql)) {

            int book_id = book.getId();
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

    ///part: User
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

    public void removeUser(User target_user){
        int user_id = target_user.getId();
        removeUser(user_id);
    }

    public void removeUser(int user_id){
        String sql = "DELETE FROM users WHERE user_id = ?";

        try (Connection conn = this.connect();
             PreparedStatement statement = conn.prepareStatement(sql)) {

            statement.setInt(1, user_id);

            int rows_affected = statement.executeUpdate();      //

            if (rows_affected > 0) {
                System.out.println("User removed successfully.");
            } else {
                System.out.println("Failed to remove user.");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void updateUserData(User u){
        updateUserData(u.getId(), u.getUsername(), u.getPassword(), u.getFirstName(), u.getLastName(), u.getEmail(), u.getRole());
    }

    public void updateUserData(int user_id, String username, String password, String first_name, String last_name, String email, String user_type){
        String sql = "UPDATE users SET user_id = ?, username = ?, password = ?, first_name = ?, last_name = ?, email = ?, user_type = ? WHERE user_id = ?";

        try (Connection conn = this.connect();
             PreparedStatement statement = conn.prepareStatement(sql)) {

            statement.setInt(1, user_id);
            statement.setString(2, username);
            statement.setString(3, password);
            statement.setString(4, first_name);
            statement.setString(5, last_name);
            statement.setString(6, email);
            statement.setString(7, user_type);
            statement.setInt(8, user_id);

            int rows_affected = statement.executeUpdate();

            if (rows_affected > 0) {
                System.out.println("User data updated successfully.");
            } else {
                System.out.println("Failed to update user data.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public User fetchUser(int user_id){
        String sql = "SELECT * FROM users WHERE user_id = ?";
        User result_user = null;

        try (Connection conn = this.connect();
            PreparedStatement statement = conn.prepareStatement(sql)) {
            statement.setInt(1, user_id);
            try (ResultSet result = statement.executeQuery()) {
                if (result.next()) {
                    int got_user_id = result.getInt("user_id");
                    String got_username = result.getString("username");
                    String got_password = result.getString("password");
                    String got_first_name = result.getString("first_name");
                    String got_last_name = result.getString("last_name");
                    String got_email = result.getString("email");
                    String got_user_type = result.getString("user_type");
                    if (Objects.equals(got_user_type, "member")) {
                        result_user = new Member(got_user_id, got_first_name, got_last_name, got_email, got_username, got_password);
                    } else {
                        result_user = new Admin(got_user_id, got_first_name, got_last_name, got_email, got_username, got_password);
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        if (result_user == null)
            System.out.println("Failed to fetch user data.");
        return result_user;
    }
}