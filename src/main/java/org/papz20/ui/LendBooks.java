package main.java.org.papz20.ui;

import main.java.org.papz20.model.Order;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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
        order_model.setColumnIdentifiers(new String[]{"order_id", "status"});
        order_table.setModel(order_model);
        init();
    }

    public void init() {
        ActionListener lend_listener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // do stuff
            }
        };
        lend_book.addActionListener(lend_listener);

        ActionListener reject_listener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // do more stuff
            }
        };
        reject_order.addActionListener(reject_listener);

        ActionListener approve_listener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // do even more stuff
            }
        };
        approve_order.addActionListener(approve_listener);

        ActionListener get_orders_listener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // do even MORE stuff
            }
        };
        get_orders.addActionListener(get_orders_listener);
    }

    private String getUserId() {
        return user_id_field.getText();
    }
}
