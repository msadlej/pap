package main.java.org.papz20.model;

import javax.xml.crypto.Data;
import java.io.File;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.util.List;
import java.util.ArrayList;
import java.util.Objects;
import java.time.LocalDate;


public class Database {
    /// part: misc
    private static Database instance;
    private static final String DB_URL = "database/appDB.db";
    private Database() {}
    public static Database getInstance() {
        if (instance == null) {
            instance = new Database();
        }
        return instance;
    }
    private Connection connect() throws SQLException {
        String IDEPath = "src/main/resources/database/appDB.db";
        String url = "jdbc:sqlite:" + IDEPath;
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(url);
        } catch (SQLException e) {
            String currentPath = Database.class.getProtectionDomain().getCodeSource().getLocation().getPath();
            currentPath = currentPath.substring(0, currentPath.lastIndexOf("/"));
            System.out.println(currentPath);
            String JARPath = "/database/appDB.db";
            url = "jdbc:sqlite:" + currentPath + JARPath;
            try {
                conn = DriverManager.getConnection(url);
            }
            catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
        return conn;
    }
    public Connection connectDB() {
        try {
            Connection conn = this.connect();
            conn.setAutoCommit(true);
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
            e.printStackTrace();
        }
    }

    public int getNextId(String table_name) {
        String id_name = getIdString(table_name);

        String sql = "SELECT MAX(" + id_name + ") FROM " + table_name;

        try (Connection conn = this.connect()) {
            PreparedStatement statement = conn.prepareStatement(sql);
            ResultSet result = statement.executeQuery();
            if (result.next()) {
                return result.getInt(1) + 1;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    private static String getIdString(String table_name) {
        String id_name = "none";
        if (Objects.equals(table_name, "users"))
            id_name = "user_id";
        if (Objects.equals(table_name, "books"))
            id_name = "book_id";
        if (Objects.equals(table_name, "copies"))
            id_name = "copy_id";
        if (Objects.equals(table_name, "orders"))
            id_name = "order_id";
        if (Objects.equals(table_name, "transactions"))
            id_name = "transaction_id";
        if (Objects.equals(table_name, "fines"))
            id_name = "fine_id";

        if (Objects.equals((id_name), "none"))
            throw new IllegalArgumentException("Invalid table name");
        return id_name;
    }

    public int getRowCount(String table_name) {
        String sql = "SELECT COUNT(*) FROM " + table_name;

        try (Connection conn = this.connect()) {
            PreparedStatement statement = conn.prepareStatement(sql);
            ResultSet result = statement.executeQuery();

            if (result.next()) {
                return result.getInt(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    ///part: Book
    public List<Book> getAllBooks(){
        List<Book> all_books = new ArrayList<>();

        String sql = "SELECT * FROM books";

        try (Connection conn = this.connect()) {
            PreparedStatement statement = conn.prepareStatement(sql);
            ResultSet results = statement.executeQuery();

            while (results.next()) {
                int book_id = results.getInt("book_id");
                String title = results.getString("title");
                String author = results.getString("author");
                String genre = results.getString("genre");
                String publish_date = results.getString("publish_date");
                all_books.add(new Book(book_id, title, author, genre, publish_date));
            }
        }
        catch (SQLException e){
            e.printStackTrace();
        }
        return all_books;
    }

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

        try (Connection conn = this.connect()){
            PreparedStatement statement = conn.prepareStatement(sql);

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

    public Book fetchBook(int book_id) {
        String sql = "SELECT * FROM books WHERE book_id = ?";
        Book selected_book = null;

        try (Connection conn = this.connect()){
            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setInt(1, book_id);
            ResultSet results = statement.executeQuery();

            if (results.next()) {
                book_id = results.getInt("book_id");
                String title = results.getString("title");
                String author = results.getString("author");
                String genre = results.getString("genre");
                String publish_date = results.getString("publish_date");
                selected_book = new Book(book_id, title, author, genre, publish_date);
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

        try (Connection conn = this.connect()){
            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setInt(1, user_id);

            ResultSet results = statement.executeQuery();
            while (results.next()) {
                int book_id = results.getInt("book_id");
                String title = results.getString("title");
                String author = results.getString("author");
                String genre = results.getString("genre");
                String publish_date = results.getString("publish_date");
                borrowed_books.add(new Book(book_id, title, author, genre, publish_date));
            }
        }
        catch (SQLException e){
            e.printStackTrace();
        }
        return borrowed_books;
    }

    public void removeBook(int book_id) {
        String sql = "DELETE FROM books WHERE book_id = ?";

        try (Connection conn = this.connect()) {
            PreparedStatement statement = conn.prepareStatement(sql);
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

        try (Connection conn = this.connect()) {
            PreparedStatement statement = conn.prepareStatement(sql);
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

        try (Connection conn = this.connect()) {
            PreparedStatement statement = conn.prepareStatement(sql);

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

        try (Connection conn = this.connect()) {
            PreparedStatement statement = conn.prepareStatement(sql);

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
    public boolean copyAvailableBook(int book_id){
        String sql = "SELECT COUNT(*) FROM copies WHERE book_id = ? AND available = true";

        try (Connection conn = this.connect()){
            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setInt(1, book_id);
            ResultSet results = statement.executeQuery();

            if (results.next()) {
                return (results.getInt(1) > 0);
            }
        }
        catch (SQLException e){
            e.printStackTrace();
        }
        return false;
    }

    public int getPenaltyBookId(int penalty_id){
        int penalty_book_id;

        String sql = "SELECT books.book_id " +
                "FROM fines " +
                "JOIN transactions ON fines.transaction_id = transactions.transaction_id " +
                "JOIN orders ON transactions.order_id = orders.order_id " +
                "JOIN copies ON orders.copy_id = copies.copy_id " +
                "JOIN books on copies.book_id = books.book_id " +
                "WHERE fines.fine_id = ?";

        try (Connection conn = this.connect()) {
            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setInt(1, penalty_id);

            ResultSet results = statement.executeQuery();
            if (results.next()) {
                penalty_book_id = results.getInt("book_id");
                return penalty_book_id;
            }
        }
        catch (SQLException e){
            e.printStackTrace();
        }
        return -1;
    }

    public Book getOrderBook(int order_id){
        Book target_book = null;

        String sql = "SELECT books.book_id " +
                "FROM orders "  +
                "JOIN copies ON orders.copy_id = copies.copy_id " +
                "JOIN books on copies.book_id = books.book_id " +
                "WHERE orders.order_id = ?";

        try (Connection conn = this.connect()) {
            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setInt(1, order_id);

            ResultSet results = statement.executeQuery();
            if (results.next()) {
                int order_book_id = results.getInt("book_id");
                target_book = fetchBook(order_book_id);
            }
        }
        catch (SQLException e){
            e.printStackTrace();
        }
        return target_book;
    }

    ///part: Copy
    public List<Copy> getAllCopies(){
        List<Copy> all_copies = new ArrayList<>();

        String sql = "SELECT * FROM copies";

        try (Connection conn = this.connect()) {
            PreparedStatement statement = conn.prepareStatement(sql);
            ResultSet results = statement.executeQuery();

            while (results.next()) {
                int copy_id = results.getInt("copy_id");
                int book_id = results.getInt("book_id");
                boolean available = results.getBoolean("available");
                all_copies.add(new Copy(copy_id, book_id, available));
            }
        }
        catch (SQLException e){
            e.printStackTrace();
        }
        return all_copies;
    }

    public Copy fetchCopy(int copy_id) {
        String sql = "SELECT * FROM copies WHERE copy_id = ?";
        Copy selected_copy = null;

        try (Connection conn = this.connect()){
            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setInt(1, copy_id);
            ResultSet results = statement.executeQuery();

            if (results.next()) {
                copy_id = results.getInt("copy_id");
                int book_id = results.getInt("book_id");
                boolean available = results.getBoolean("available");

                selected_copy = new Copy(copy_id, book_id, available);
            }
        }
        catch (SQLException e){
            e.printStackTrace();
        }
        return selected_copy;
    }

    public void addCopy(Copy new_copy){
        String sql = "INSERT INTO copies (copy_id, book_id, available) VALUES (?, ?, ?)";

        try (Connection conn = this.connect()){
            PreparedStatement statement = conn.prepareStatement(sql);

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

        try (Connection conn = this.connect()) {
            PreparedStatement statement = conn.prepareStatement(sql);

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

    public void addCopy(int book_id){
        String sql = "INSERT INTO copies (copy_id, book_id, available) VALUES (?, ?, ?)";

        try (Connection conn = this.connect()) {
            PreparedStatement statement = conn.prepareStatement(sql);

            statement.setInt(1, getNextId("copies"));
            statement.setInt(2, book_id);
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

        try (Connection conn = this.connect()){
            PreparedStatement statement = conn.prepareStatement(sql);
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
    public List<User> getAllUsers(){
        List<User> all_users = new ArrayList<>();

        String sql = "SELECT * FROM users";

        try (Connection conn = this.connect()) {
            PreparedStatement statement = conn.prepareStatement(sql);
            ResultSet results = statement.executeQuery();
            while (results.next()) {
                int user_id = results.getInt("user_id");
                String username = results.getString("username");
                String password = results.getString("password");
                String first_name = results.getString("first_name");
                String last_name = results.getString("last_name");
                String email = results.getString("email");
                String user_type = results.getString("user_type");

                if (Objects.equals(user_type, "member")){
                    all_users.add(new Member(user_id, first_name, last_name, email, username, password));
                }else{
                    all_users.add(new Admin(user_id, first_name, last_name, email, username, password));
                }
            }
        }
        catch (SQLException e){
            e.printStackTrace();
        }
        return all_users;
    }

    public void addUser(int user_id, String username, String password, String first_name, String last_name, String email, String user_type){
        String sql = "INSERT INTO users (user_id, username, password, first_name, last_name, email, user_type) VALUES (?, ?, ?, ?, ?, ?, ?)";

        try (Connection conn = this.connect()) {
            PreparedStatement statement = conn.prepareStatement(sql);

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

        try (Connection conn = this.connect()) {
            PreparedStatement statement = conn.prepareStatement(sql);

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

        try (Connection conn = this.connect()) {
            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setInt(1, user_id);

            int rows_affected = statement.executeUpdate();

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

        try (Connection conn = this.connect()) {
            PreparedStatement statement = conn.prepareStatement(sql);

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

        try (Connection conn = this.connect()) {
            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setInt(1, user_id);
            ResultSet result = statement.executeQuery();
            if (result.next()) {
                user_id = result.getInt("user_id");
                String username = result.getString("username");
                String password = result.getString("password");
                String first_name = result.getString("first_name");
                String last_name = result.getString("last_name");
                String email = result.getString("email");
                String user_type = result.getString("user_type");
                if (Objects.equals(user_type, "member")) {
                    result_user = new Member(user_id, first_name, last_name, email, username, password);
                } else {
                    result_user = new Admin(user_id, first_name, last_name, email, username, password);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        if (result_user == null)
            System.out.println("Failed to fetch user data.");
        return result_user;
    }

    public int getUsernameId(String username){
        String query = "SELECT user_id FROM users WHERE username = ?";
        try (Connection connection = this.connect()){
            PreparedStatement preparedStatement = connection.prepareStatement(query);

            preparedStatement.setString(1, username);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()){
                return resultSet.getInt("user_id");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return -1;
    }

    ///part: Order
    public List<Order> getAllOrders(){
        List<Order> all_orders = new ArrayList<>();

        String sql = "SELECT * FROM orders";

        try (Connection conn = this.connect()) {
            PreparedStatement statement = conn.prepareStatement(sql);
            ResultSet results = statement.executeQuery();

            while (results.next()) {
                int order_id = results.getInt("order_id");
                int user_id = results.getInt("user_id");
                int copy_id = results.getInt("copy_id");
                String order_date = results.getString("order_date");
                int order_period = results.getInt("order_period");
                String order_status = results.getString("order_status");

                all_orders.add(new Order(order_id, user_id, copy_id, order_date, order_period, order_status));
            }
        }
        catch (SQLException e){
            e.printStackTrace();
        }
        return all_orders;
    }

    public Order fetchOrder(int order_id){
        String sql = "SELECT * FROM orders WHERE order_id = ?";
        Order selected_order  = null;

        try (Connection conn = this.connect()) {
            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setInt(1, order_id);
            ResultSet result = statement.executeQuery();
            if (result.next()) {
                order_id = result.getInt("order_id");
                int user_id = result.getInt("user_id");
                int copy_id = result.getInt("copy_id");
                String order_date = result.getString("order_date");
                int order_period = result.getInt("order_period");
                String order_status = result.getString("order_status");

                selected_order = new Order(order_id, user_id, copy_id, order_date, order_period, order_status);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return selected_order;
    }

    public void addOrder(Order new_order){
        int order_id = new_order.getId();
        int user_id = new_order.getUserId();
        int copy_id = new_order.getCopyId();
        String date = new_order.getDate();
        int period = new_order.getPeriod();
        String status = new_order.getStatus();

        addOrder(order_id, user_id, copy_id, date, period, status);
    }

    public void addOrder(int order_id, int user_id, int copy_id, String date, int period, String status){
        String sql = "INSERT INTO orders (order_id, user_id, copy_id, order_date, order_period, order_status) VALUES (?, ?, ?, ?, ?, ?)";

        try (Connection conn = this.connect()) {
            PreparedStatement statement = conn.prepareStatement(sql);

            statement.setInt(1, order_id);
            statement.setInt(2, user_id);
            statement.setInt(3, copy_id);
            statement.setString(4, date);
            statement.setInt(5, period);
            statement.setString(6, status);

            int rows_affected = statement.executeUpdate();

            if (rows_affected > 0) {
                System.out.println("Order added successfully.");
            } else {
                System.out.println("Failed to add order.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void addOrder(int user_id, int copy_id, String date, int period){
        addOrder(user_id, copy_id, date, period, "pending");
    }

    public void addOrder(int user_id, int copy_id, String date, int period, String status){
        int order_id = getNextId("orders");
        addOrder(order_id, user_id, copy_id, date, period, status);
    }

    public void setOrderStatus(int order_id, String new_status){
        String sql = "UPDATE orders SET order_status = ? WHERE order_id = ?";

        try (Connection conn = this.connect()) {
            PreparedStatement statement = conn.prepareStatement(sql);

            statement.setString(1, new_status);
            statement.setInt(2, order_id);

            int rows_affected = statement.executeUpdate();

            if (rows_affected > 0) {
                System.out.println("Order status changed successfully.");
            } else {
                System.out.println("Failed to change order status.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void setOrderStatus(Order target_order, String new_status){
        int order_id = target_order.getId();
        setOrderStatus(order_id, new_status);
    }

    ///part: Transaction
    public List<Transaction> getAllTransactions(){
        List<Transaction> all_transactions = new ArrayList<>();

        String sql = "SELECT * FROM transactions";

        try (Connection conn = this.connect()) {
            PreparedStatement statement = conn.prepareStatement(sql);

            ResultSet results = statement.executeQuery();

            while (results.next()) {
                int transaction_id = results.getInt("transaction_id");
                int order_id = results.getInt("order_id");
                int user_id = results.getInt("user_id");
                int copy_id = results.getInt("copy_id");
                String checkout_date = results.getString("checkout_date");
                String due_date = results.getString("due_date");
                String transaction_status = results.getString("transaction_status");
                all_transactions.add(new Transaction(transaction_id, order_id, user_id, copy_id, checkout_date, due_date, transaction_status));
            }
        }
        catch (SQLException e){
            e.printStackTrace();
        }
        return all_transactions;
    }

    public Transaction fetchTransaction(int transaction_id){
        String sql = "SELECT * FROM transactions WHERE transaction_id = ?";
        Transaction selected_transaction = null;

        try (Connection conn = this.connect()) {
            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setInt(1, transaction_id);
            ResultSet result = statement.executeQuery();

            if (result.next()) {
                transaction_id = result.getInt("transaction_id");
                int order_id = result.getInt("order_id");
                int user_id = result.getInt("user_id");
                int copy_id = result.getInt("copy_id");
                String checkout_date = result.getString("checkout_date");
                String due_date = result.getString("due_date");
                String transaction_status = result.getString("transaction_status");

                selected_transaction = new Transaction(transaction_id, order_id, user_id, copy_id, checkout_date, due_date, transaction_status);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return selected_transaction;
    }

    public void addTransaction(Transaction new_transaction){
        int transaction_id = new_transaction.getId();
        int order_id = new_transaction.getOrderId();
        int user_id = new_transaction.getUserId();
        int copy_id = new_transaction.getCopyId();
        String checkout_date = new_transaction.getCheckoutDate();
        String due_date = new_transaction.getDueDate();
        String status = new_transaction.getStatus();
        addTransaction(transaction_id, order_id, user_id, copy_id, checkout_date, due_date, status);
    }

    public void addTransaction(int transaction_id, int order_id, int user_id, int copy_id, String checkout_date, String due_date, String status){
        String sql = "INSERT INTO transactions (transaction_id, order_id, user_id, copy_id, checkout_date, due_date, transaction_status) VALUES (?, ?, ?, ?, ?, ?, ?)";
        try (Connection conn = this.connect()) {
            PreparedStatement statement = conn.prepareStatement(sql);

            statement.setInt(1, transaction_id);
            statement.setInt(2, order_id);
            statement.setInt(3, user_id);
            statement.setInt(4, copy_id);
            statement.setString(5, checkout_date);
            statement.setString(6, due_date);
            statement.setString(7, status);

            int rows_affected = statement.executeUpdate();

            if (rows_affected > 0) {
                System.out.println("Transaction added successfully.");
            } else {
                System.out.println("Failed to add transaction.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void addTransaction(int order_id, int user_id, int copy_id, String checkout_date, String due_date, String status){
        int transaction_id = getNextId("transactions");
        addTransaction(transaction_id, order_id, user_id, copy_id, checkout_date, due_date, status);
    }

    public void setTransactionStatus(int transaction_id, String new_status){
        String sql = "UPDATE transactions SET transaction_status = ? WHERE transaction_id = ?";

        try(Connection conn = this.connect()){
            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setString(1, new_status);
            statement.setInt(2, transaction_id);

            int rows_affected = statement.executeUpdate();

            if (rows_affected > 0) {
                System.out.println("Transaction status changed successfully.");
            } else {
                System.out.println("Failed to change transaction status.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void setTransactionStatus(Transaction target_transaction, String new_status){
        int transaction_id = target_transaction.getId();
        setTransactionStatus(transaction_id, new_status);
    }

    public boolean isLateTransaction(int transaction_id){
        String sql = "SELECT due_date FROM transactions WHERE transaction_id = ?";

        try (Connection conn = this.connect()) {
            PreparedStatement statement = conn.prepareStatement(sql);

            statement.setInt(1, transaction_id);
            ResultSet results = statement.executeQuery();

            if (results.next()) {
                LocalDate due_date = LocalDate.parse(results.getString("due_date"));
                LocalDate current_date = LocalDate.now();
                return current_date.isAfter(due_date);
            }
        }
        catch (SQLException e){
            e.printStackTrace();
        }
        return false;
    }

    public boolean isLateTransaction(Transaction target_transaction){
        LocalDate due_date = LocalDate.parse(target_transaction.getDueDate());
        LocalDate current_date = LocalDate.now();
        return current_date.isAfter(due_date);
    }

    public List<Transaction> getLateTransactions(){
        List<Transaction> all_transactions = getAllTransactions();
        List<Transaction> late_transactions = new ArrayList<>();

        for (Transaction this_transaction: all_transactions){
            if (isLateTransaction(this_transaction)){
                late_transactions.add(this_transaction);
            }
        }
        return late_transactions;
    }

    public List<Transaction> getUserTransactions(int user_id) {
        List<Transaction> user_transactions = new ArrayList<>();
        String sql = "SELECT * FROM transactions WHERE user_id = ?";

        try (Connection conn = this.connect()){
            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setInt(1, user_id);

            ResultSet results = statement.executeQuery();
            while (results.next()) {
                int transaction_id = results.getInt("transaction_id");
                int order_id = results.getInt("order_id");
                int copy_id = results.getInt("copy_id");
                String status = results.getString("transaction_status");
                String checkout_date = results.getString("checkout_date");
                String due_date = results.getString("due_date");
                user_transactions.add(new Transaction(transaction_id, order_id, user_id, copy_id, checkout_date, due_date, status));
            }
        }
        catch (SQLException e){
            e.printStackTrace();
        }
        return user_transactions;
    }

    ///part: Fine
    public Fine fetchFine(int fine_id){
        String sql = "SELECT * FROM fines WHERE fine_id = ?";
        Fine selected_fine = null;

        try (Connection conn = this.connect()) {
            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setInt(1,fine_id);
            ResultSet result = statement.executeQuery();

            if (result.next()) {
                fine_id = result.getInt("fine_id");
                int transaction_id = result.getInt("transaction_id");
                int fine_amount = result.getInt("fine_amount");
                String fine_status = result.getString("fine_status");

                selected_fine = new Fine(fine_id, transaction_id, fine_amount, fine_status);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return selected_fine;
    }

    public List<Fine> getAllFines(){
        List<Fine> all_fines = new ArrayList<>();

        String sql = "SELECT * FROM fines";

        try (Connection conn = this.connect()) {
            PreparedStatement statement = conn.prepareStatement(sql);

            ResultSet results = statement.executeQuery();
            while (results.next()) {
                int fine_id = results.getInt("fine_id");
                int transaction_id = results.getInt("transaction_id");
                int fine_amount = results.getInt("fine_amount");
                String fine_status = results.getString("fine_status");
                all_fines.add(new Fine(fine_id, transaction_id, fine_amount, fine_status));
            }
        }
        catch (SQLException e){
            e.printStackTrace();
        }
        return all_fines;
    }

    public void addFine(Fine new_fine){
        int fine_id = new_fine.getId();
        int transaction_id = new_fine.getTransactionId();
        int amount = new_fine.getAmount();
        String status = new_fine.getStatus();

        addFine(fine_id, transaction_id, amount, status);
    }

    public void addFine(int fine_id, int transaction_id, int amount, String status) {
        String sql = "INSERT INTO fines (fine_id, transaction_id, fine_amount, fine_status) VALUES (?, ?, ?, ?)";

        try (Connection conn = this.connect()) {
            PreparedStatement statement = conn.prepareStatement(sql);

            statement.setInt(1, fine_id);
            statement.setInt(2, transaction_id);
            statement.setInt(3, amount);
            statement.setString(4, status);

            int rows_affected = statement.executeUpdate();

            if (rows_affected > 0) {
                System.out.println("Fine added successfully.");
            } else {
                System.out.println("Failed to add fine.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void addFine(int transaction_id, int amount, String status) {
        int fine_id = getNextId("fines");
        addFine(fine_id, transaction_id, amount, status);
    }

    public void addFine(int transaction_id, int amount) {
        int fine_id = getNextId("fines");
        addFine(fine_id, transaction_id, amount, "unpaid");
    }

    public void setFineStatus(int fine_id, String new_status){
        String sql = "UPDATE fines SET fine_status = ? WHERE fine_id = ?";

        try (Connection conn = this.connect()) {
            PreparedStatement statement = conn.prepareStatement(sql);

            statement.setString(1, new_status);
            statement.setInt(2, fine_id);

            int rows_affected = statement.executeUpdate();

            if (rows_affected > 0) {
                System.out.println("Fine status changed successfully.");
            } else {
                System.out.println("Failed to change fine status.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void setFineStatus(Fine target_fine, String new_status){
        int fine_id = target_fine.getId();
        setFineStatus(fine_id, new_status);
    }

    public List<Fine> viewFines(int user_id) {
        List<Fine> fines = new ArrayList<>();
        String sql = "SELECT fines.fine_id, fines.transaction_id, fines.fine_amount, fines.fine_status " +
                "FROM fines " +
                "JOIN transactions ON fines.transaction_id = transactions.transaction_id " +
                "WHERE transactions.user_id = ?";

        try (Connection conn = this.connect()) {
            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setInt(1, user_id);
            ResultSet results = statement.executeQuery();

            while (results.next()) {
                int fine_id = results.getInt("fine_id");
                int transaction_id = results.getInt("transaction_id");
                int fine_amount = results.getInt("fine_amount");
                String fine_status = results.getString("fine_status");
                fines.add(new Fine(fine_id, transaction_id, fine_amount, fine_status));
            }
        }
        catch (SQLException e){
            e.printStackTrace();
        }
        return fines;
    }
}
