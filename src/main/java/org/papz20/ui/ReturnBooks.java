package main.java.org.papz20.ui;

import main.java.org.papz20.services.BookService;
import main.java.org.papz20.services.LendBookService;
import main.java.org.papz20.services.ReceiveReturnedBookService;
import main.java.org.papz20.services.TransactionService;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ReturnBooks extends JPanel {
    private JPanel main_panel;
    private JTextField textField1;
    private JButton selectButton;
    private JLabel select_message;
    private JLabel book_title;
    private JLabel book_author;
    private JLabel book_genre;
    private JLabel borrowed_date;
    private JLabel return_date;
    private JButton return_book;
    private JLabel message;
    private JPanel book_panel;

    public ReturnBooks() {
        setLayout(new BorderLayout());
        add(main_panel, BorderLayout.CENTER);

        selectButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            }
        });

        clear();
    }
    private void clear() {
        book_panel.setVisible(false);
        return_book.setVisible(false);
    }
}
