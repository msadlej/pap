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

    public int getNextId(String tableName) {
        String id_name = getIdString(tableName);

        String sql = "SELECT MAX(" + id_name + ") FROM " + tableName;

        try (Connection conn = this.connect();
            PreparedStatement statement = conn.prepareStatement(sql);
             ResultSet result = statement.executeQuery()) {
                if (result.next()) {
                    return result.getInt(1) + 1;
                }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    private static String getIdString(String tableName) {
        String id_name = "none";
        if (Objects.equals(tableName, "users"))
            id_name = "user_id";
        if (Objects.equals(tableName, "books"))
            id_name = "book_id";
        if (Objects.equals(tableName, "copies"))
            id_name = "copy_id";
        if (Objects.equals(tableName, "orders"))
            id_name = "order_id";
        if (Objects.equals(tableName, "transactions"))
            id_name = "transaction_id";
        if (Objects.equals(tableName, "fines"))
            id_name = "copy_id";

        if (Objects.equals((id_name), "none"))
            throw new IllegalArgumentException("Invalid table name");
        return id_name;
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

        String sql = "SELECT * FROM books WHERE title LIKE ? AND author LIKE ? AND genre LIKE ?";

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
             PreparedStatement statement = conn.prepareStatement(sql)){

            statement.setString(1, title_key);
            statement.setString(2, author_key);
            statement.setString(3, genre_key);

            try (ResultSet results = statement.executeQuery()){

                while (results.next()) {
                    String[] book = new String[5];
                    book[0] = results.getString("book_id");
                    book[1] = results.getString("title");
                    book[2] = results.getString("author");
                    book[3] = results.getString("genre");
                    book[4] = results.getString("publish_date");
                    book_list.add(book);
                }
            }
        }
        catch (SQLException e){
            e.printStackTrace();
        }
        return book_list;
    }

    public List<Book> selectBookObjects(String title_key, String author_key, String genre_key) {
        List<String[]> selected_book_strings = selectBooks(title_key, author_key, genre_key);
        List<Book> book_list = new ArrayList<>();

        for (String[] book_strings : selected_book_strings) {
            int book_id = Integer.parseInt(book_strings[0]);
            String title = book_strings[1];
            String author = book_strings[2];
            String genre = book_strings[3];
            String publish_date = book_strings[4];

            Book book = new Book(book_id, title, author, genre, publish_date);
            book_list.add(book);
        }
        return book_list;
    }

    public Book selectBookObject(int book_id) {
        String sql = "SELECT * FROM books WHERE book_id = ?";
        Book selected_book = null;

        try (Connection conn = this.connect();
             PreparedStatement statement = conn.prepareStatement(sql)){

            statement.setInt(1, book_id);

            try (ResultSet results = statement.executeQuery()){
                if (results.next()) {
                    book_id = results.getInt("book_id");
                    String title = results.getString("title");
                    String author = results.getString("author");
                    String genre = results.getString("genre");
                    String publish_date = results.getString("publish_date");
                    selected_book = new Book(book_id, title, author, genre, publish_date);
                }
            }
        }
        catch (SQLException e){
            e.printStackTrace();
        }
        return selected_book;
    }

    public List<Book> findBorrowedBooks(int user_id) {
        List<Book> borrowed_books = new ArrayList<>();
        String sql = "SELECT books.book_id, books.title, books.author, books.genre, books.publish_date " +
                "FROM books " +
                "JOIN copies ON books.book_id = copies.book_id " +
                "JOIN orders ON copies.copy_id = orders.copy_id " +
                "WHERE orders.user_id = ?";

        try (Connection conn = this.connect();
             PreparedStatement statement = conn.prepareStatement(sql)){
            statement.setInt(1, user_id);

            try(ResultSet results = statement.executeQuery()){
                while (results.next()) {
                    int book_id = results.getInt("book_id");
                    String title = results.getString("title");
                    String author = results.getString("author");
                    String genre = results.getString("genre");
                    String publish_date = results.getString("publish_date");
                    borrowed_books.add(new Book(book_id, title, author, genre, publish_date));
                }
            }
        }
        catch (SQLException e){
            e.printStackTrace();
        }
        return borrowed_books;
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
        int book_id = getNextId("books");
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

    boolean copyAvailableBook(int book_id){

        String sql = "SELECT COUNT(*) FROM copies WHERE book_id = ? AND available = true";

        try (Connection conn = this.connect();
             PreparedStatement statement = conn.prepareStatement(sql)){

            statement.setInt(1, book_id);

            try (ResultSet results = statement.executeQuery()){
                if (results.next()) {
                    return (results.getInt(1) > 0);
                }
            }
        }
        catch (SQLException e){
            e.printStackTrace();
        }
        return false;
    }


    ///part: Copy
    public void addCopy(Copy new_copy){
        String sql = "INSERT INTO copies (copy_id, book_id, available) VALUES (?, ?, ?)";

        try (Connection conn = this.connect();
             PreparedStatement statement = conn.prepareStatement(sql)) {

            statement.setInt(1, new_copy.getId());
            statement.setInt(2, new_copy.getBookId());
            statement.setBoolean(3, new_copy.getAvailable());

            int rows_affected = statement.executeUpdate();

            if (rows_affected > 0) {
                System.out.println("Copy added successfully.");
            } else {
                System.out.println("Failed to add copy.");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void addCopy(Book new_book){
        String sql = "INSERT INTO copies (copy_id, book_id, available) VALUES (?, ?, ?)";

        try (Connection conn = this.connect();
            PreparedStatement statement = conn.prepareStatement(sql)) {

            statement.setInt(1, getNextId("copies"));
            statement.setInt(2, new_book.getId());
            statement.setBoolean(3, true);

            int rows_affected = statement.executeUpdate();

            if (rows_affected > 0) {
                System.out.println("Copy added successfully.");
            } else {
                System.out.println("Failed to add copy.");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void setAvailableCopy(int copy_id, boolean new_availability){
        String sql = "UPDATE copies SET available = ? WHERE copy_id = ?";

        try (Connection conn = this.connect();
             PreparedStatement statement = conn.prepareStatement(sql)) {

            statement.setBoolean(1, new_availability);
            statement.setInt(2, copy_id);

            int rows_affected = statement.executeUpdate();

            if (rows_affected > 0) {
                System.out.println("Copy availability changed successfully.");
            } else {
                System.out.println("Failed to change copy availability.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void setAvailableCopy(Copy target_copy, boolean new_availability){
        int copy_id = target_copy.getId();
        setAvailableCopy(copy_id, new_availability);
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
        int user_id = getNextId("users");
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