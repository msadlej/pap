package main.java.org.papz20.ui;

import main.java.org.papz20.model.Book;
import main.java.org.papz20.model.Database;
import main.java.org.papz20.model.Order;
import main.java.org.papz20.model.Transaction;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

public class MyBooksPanel extends JPanel {

    private JPanel main_panel;
    private JTable books_table;
    private DefaultTableModel book_model;
    private JTable orders_table;
    private DefaultTableModel orders_model;


    public MyBooksPanel() {
        setLayout(new BorderLayout());
        add(main_panel, BorderLayout.CENTER);

        book_model = new DefaultTableModel();
        book_model.setColumnIdentifiers(new String[]{"Title", "Order Date", "Return Date", "Copy id"});
        books_table.setModel(book_model);

        orders_model = new DefaultTableModel();
        orders_model.setColumnIdentifiers(new String[]{"Title", "Order Date", "Valid Period Days", "Status"});
        orders_table.setModel(orders_model);
    }

    public void load_data(int user_id) {
        // clear list
        book_model.setRowCount(0);
        orders_model.setRowCount(0);

        Database db = Database.getInstance();
        db.connectDB();

        List<Transaction> transactions = db.getAllTransactions();
        for (Transaction transaction : transactions) {
            if (transaction.getUserId() != user_id) continue;
            if (transaction.getStatus().equals("returned")) continue;
            Book book = db.getOrderBook(transaction.getOrderId());
            book_model.addRow(new String[]{
                    book.getTitle(),
                    transaction.getCheckoutDate(),
                    transaction.getCheckoutDate(),
                    String.valueOf(transaction.getCopyId())
            });
        }

        List<Order> orders = db.getAllOrders();
        for (Order order : orders) {
            if (order.getUserId() != user_id) continue;
            if (order.getStatus().equals("approved")) continue;
            Book book = db.getOrderBook(order.getId());
            orders_model.addRow(new String[]{book.getTitle(), order.getDate(), String.valueOf(order.getPeriod()), order.getStatus()});
        }


    }
}
