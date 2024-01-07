package main.java.org.papz20.ui;

import main.java.org.papz20.model.Database;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class BookSearchPanel extends JPanel{
    private JPanel main_panel;
    private JTextField title_search;
    private JButton searchButton;
    private JTextField author_search;
    private JTextField genre_search;
    private JLabel query_report;
    private JScrollPane query_scroll;

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
                List<String[]> results = database.selectBooks(title, author, genre);

                if (results.isEmpty()) {
                    query_report.setText(String.format("No titles found.", results.size()));
                    query_report.setForeground(new Color(255, 114, 118));
                    return;
                }
                query_report.setForeground(new Color(0, 0, 0));
                query_report.setText(String.format("Found %d titles", results.size()));
                for (String[] book_data : results)
                {
                    for (String book_field: book_data) System.out.print(book_field);
                    System.out.println();
                }
            }
        });
    }
}
