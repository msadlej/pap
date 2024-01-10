package main.java.org.papz20.services;

import main.java.org.papz20.model.Copy;
import main.java.org.papz20.model.Database;
import main.java.org.papz20.model.Order;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class OrderService {
    private final Database database;
    private final int defaultPeriod = 14;

    public OrderService() { this.database = new Database(); }

    public boolean createOrder(int bookId, int userId) {
        // get order id as the first available id from copies list
        List<Copy> copies = database.getAllCopies();
        int copyId = -1;
        for (Copy copy : copies) {
            if (copy.getBookId() == bookId && copy.getAvailable()) {
                copyId = copy.getId();
                break;
            }
        }
        if (copyId == -1) {
            return false;
        }

        String dataToHash = String.valueOf(userId) + "-" + String.valueOf(copyId) + "-" + String.valueOf(bookId);
        dataToHash += "-" + LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMddHHmmss"));
        int orderId = dataToHash.hashCode();

        Order order = new Order(orderId, userId, copyId, LocalDate.now().toString(), defaultPeriod, "pending");
        try {
            database.addOrder(order);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    //fetches all orders with a specific user id using database.getAllOrders()
    public List<Order> fetchUserOrders(int user_id) {
        List <Order> orders = database.getAllOrders();
        for (Order order : orders) {
            if (order.getUserId() != user_id) {
                orders.remove(order);
            }
        }
        return orders;
    }

    public List<Copy> fetchBookCopies(int book_id) {
        List <Copy> copies = database.getAllCopies();
        for (Copy copy : copies) {
            if (copy.getBookId() != book_id) {
                copies.remove(copy);
            }
        }
        return copies;
    }


    public boolean removeOrder(int orderID) {
        try {
            database.setOrderStatus(orderID, "hidden");
            return true;
        }
        catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}
