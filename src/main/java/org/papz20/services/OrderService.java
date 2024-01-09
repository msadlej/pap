package main.java.org.papz20.services;

import main.java.org.papz20.model.Database;
import main.java.org.papz20.model.Order;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class OrderService {
    private Database database;

    public OrderService() { this.database = new Database(); }

    private boolean insertQuery(Order order) {
        String query = "INSERT INTO orders (order_id, user_id, copy_id, order_date, order_period, order_status) VALUES (?, ?, ?, ?, ?, ?)";
        try (
                Connection connection = database.connectDB();
                PreparedStatement preparedStatement = connection.prepareStatement(query)
        ) {
            preparedStatement.setInt(1, order.getId());
            preparedStatement.setInt(2, order.getUserId());
            preparedStatement.setInt(3, order.getCopyId());
            preparedStatement.setString(4, order.getDate());
            preparedStatement.setInt(5, order.getPeriod());
            preparedStatement.setString(6, order.getStatus());
            preparedStatement.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    private boolean updateQuery(int orderID, Order newOrder) {
        String query = "UPDATE orders SET order_id = ?, user_id = ?, copy_id = ?, order_date = ?, order_period = ?, order_status = ? WHERE order_id = ?";
        try (
                Connection connection = database.connectDB();
                PreparedStatement preparedStatement = connection.prepareStatement(query)
        ) {
            preparedStatement.setInt(1, newOrder.getId());
            preparedStatement.setInt(2, newOrder.getUserId());
            preparedStatement.setInt(3, newOrder.getCopyId());
            preparedStatement.setString(4, newOrder.getDate());
            preparedStatement.setInt(5, newOrder.getPeriod());
            preparedStatement.setString(6, newOrder.getStatus());
            preparedStatement.setInt(7, orderID);
            preparedStatement.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    private boolean deleteQuery(int orderID) {
        String query = "DELETE FROM orders WHERE order_id = ?";
        try (
                Connection connection = database.connectDB();
                PreparedStatement preparedStatement = connection.prepareStatement(query)
        ) {
            preparedStatement.setInt(1, orderID);
            preparedStatement.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean createOrder(int orderID, int userID, int copyID, String date, int period, String status) {
        Order order = new Order(orderID, userID, copyID, date, period, status);
        return this.insertQuery(order);
    }

    public boolean modifyOrder(int orderID, int newOrderID, int userID, int copyID, String date, int period, String status) {
        Order newOrder = new Order(newOrderID, userID, copyID, date, period, status);
        return this.updateQuery(orderID, newOrder);
    }

    public boolean removeOrder(int orderID) {
        return this.deleteQuery(orderID);
    }
}
