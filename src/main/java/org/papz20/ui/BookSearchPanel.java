package main.java.org.papz20.ui;

import main.java.org.papz20.model.Database;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class BookSearchPanel extends JPanel{
    private JPanel main_panel;
    private JTextField title_search;
    private DefaultListModel<String> list_model;
    private JList book_list;
    private JButton searchButton;
    private JTextField author_search;
    private JTextField genre_search;

    private Database database;


    public BookSearchPanel() {
        database = new Database();
        database.connectDB();
        setLayout(new BorderLayout());
        add(main_panel, BorderLayout.CENTER);
        searchButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String title = title_search.getText();
                String author = author_search.getText();
                String genre = genre_search.getText();
                if (title.isBlank()) {
                    title = "%";
                }
                if (author.isBlank()) {
                    author = "%";
                }
                if (genre.isBlank()) {
                    genre = "%";
                }
                String[][] results = database.selectBooks(title, author, genre);
                if (results.length == 0) {
                    return;
                }
                list_model.clear();
                for (String[] book_data : results)
                {
                    list_model.addElement(String.join(" ", book_data));
                }
            }
        });
    }

    private void createUIComponents() {
        list_model = new DefaultListModel<>();
        book_list = new JList<>(list_model);
    }
}
