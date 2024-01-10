package main.java.org.papz20.ui;

import main.java.org.papz20.model.Book;
import main.java.org.papz20.model.Database;
import main.java.org.papz20.model.Order;
import main.java.org.papz20.model.User;
import main.java.org.papz20.services.TransactionService;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.xml.crypto.Data;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class LendBooks extends JPanel {
    private JPanel main_panel;
    private JTextField user_id_field;
    private JButton get_orders;
    private JTable order_table;
    private DefaultTableModel order_model;
    private List<Order> orders;
    private JButton lend_book;
    private JButton reject_order;
    private JButton approve_order;

    public LendBooks() {
        setLayout(new BorderLayout());
        add(main_panel, BorderLayout.CENTER);
        order_model = new DefaultTableModel();
        order_model.setColumnIdentifiers(new String[]{"order_id", "user_id", "username", "copy_id", "title", "status"});
        order_table.setModel(order_model);
        approve_order.setVisible(false);
        init();
    }

    public void init() {
        ActionListener lend_listener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int selectedRow = order_table.getSelectedRow();
                if (selectedRow == -1) return;
                Order order = orders.get(selectedRow);
                new TransactionService().createTransactionFromOrder(order.getId());
                orders.remove(selectedRow);
                order_model.removeRow(selectedRow);
            }
        };
        lend_book.addActionListener(lend_listener);

        ActionListener reject_listener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int selectedRow = order_table.getSelectedRow();
                if (selectedRow == -1) return;
                Order order = orders.get(selectedRow);
                new TransactionService().createTransactionFromOrder(order.getId());
                orders.remove(selectedRow);
                order_model.removeRow(selectedRow);
            }
        };
        reject_order.addActionListener(reject_listener);

        ActionListener approve_listener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // do we need this?
            }
        };
        approve_order.addActionListener(approve_listener);

        ActionListener get_orders_listener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                order_model.setRowCount(0);
                Database db = new Database();
                db.connectDB();
                List<Order> curr_orders = db.getAllOrders();
                orders = new ArrayList<Order>();
                for (Order order : curr_orders) {
                    if (!user_id_field.getText().isBlank() && order.getUserId() != Integer.parseInt(user_id_field.getText())) continue;
                    if (order.getStatus().equals("approved")) continue;
                    orders.add(order);
                    Book book = db.getOrderBook(order.getId());
                    User user = db.fetchUser(order.getUserId());
                    order_model.addRow(new String[]{
                            String.valueOf(order.getId()),
                            String.valueOf(order.getUserId()),
                            user.getUsername(),
                            String.valueOf(order.getCopyId()),
                            book.getTitle(),
                            order.getStatus()
                    });
                }
            }
        };
        get_orders.addActionListener(get_orders_listener);
    }

    private String getUserId() {
        return user_id_field.getText();
    }
}
