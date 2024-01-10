package main.java.org.papz20.ui;

import javax.swing.*;
import java.awt.*;

public class MyBooksPanel extends JPanel {

    private JList borrowed_books_list;
    private JList orders_list;
    private JPanel main_panel;

    public MyBooksPanel() {
        setLayout(new BorderLayout());
        add(main_panel, BorderLayout.CENTER);
    }
}
