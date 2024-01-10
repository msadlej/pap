package main.java.org.papz20.ui;

import javax.swing.*;
import java.awt.*;

public class BookSearchListElement extends JPanel {
    private JPanel main_panel;
    private JLabel book_title;
    private JLabel book_author;
    private JLabel book_genre;
    private JLabel book_availability;
    private JButton orderButton;


    public BookSearchListElement(String title, String author, String genre, boolean isAvailable)
    {
        setLayout(new BorderLayout());
        add(main_panel, BorderLayout.CENTER);
        book_title.setText(title);
        book_author.setText(author);
        book_genre.setText(genre);
        if (isAvailable) {
            book_availability.setText("Available");
            book_availability.setForeground(new Color(170, 214, 136));
        } else {
            book_availability.setText("Not Available");
            book_availability.setForeground(new Color(255, 156, 149));
        }
    }
}
