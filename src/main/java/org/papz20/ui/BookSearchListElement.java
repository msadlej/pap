package main.java.org.papz20.ui;

import main.java.org.papz20.model.Book;
import main.java.org.papz20.services.OrderService;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class BookSearchListElement extends JPanel {
    private JPanel main_panel;
    private JLabel book_title;
    private JLabel book_author;
    private JLabel book_genre;
    private JLabel book_availability;
    private JButton orderButton;
    private int curr_user_id;
    private Book book;


    public BookSearchListElement(Book book_p, boolean isAvailable, int user_id)
    {
        setLayout(new BorderLayout());
        add(main_panel, BorderLayout.CENTER);

        book = book_p;
        curr_user_id = user_id;
        book_title.setText(book.getTitle());
        book_author.setText(book.getAuthor());
        book_genre.setText(book.getGenre());
        if (isAvailable) {
            book_availability.setText("Available");
            book_availability.setForeground(new Color(170, 214, 136));
        } else {
            book_availability.setText("Not Available");
            book_availability.setForeground(new Color(255, 156, 149));
            orderButton.setEnabled(false);
        }
        orderButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new OrderService().createOrder(book.getId(), curr_user_id);
                System.out.println("Order!");
            }
        });
    }
}
