package main.java.org.papz20.ui;

import main.java.org.papz20.model.Database;
import main.java.org.papz20.model.Book;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import java.util.ListIterator;
import java.lang.Math;

public class BookSearchPanel extends JPanel{
    private Database database;
    private List<Book> query_books;
    private JPanel main_panel;
    private JTextField title_search;
    private JButton searchButton;
    private JTextField author_search;
    private JTextField genre_search;
    private JLabel query_report;
    private JScrollPane query_scrollable;
    private JPanel query;
    private JButton left_button;
    private JButton right_button;
    private JLabel page_number;


    public BookSearchPanel() {
        database = new Database();
        database.connectDB();
        setLayout(new BorderLayout());
        add(main_panel, BorderLayout.CENTER);
        query.setLayout(new BoxLayout(query, BoxLayout.Y_AXIS));

        searchButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String title = title_search.getText();
                String author = author_search.getText();
                String genre = genre_search.getText();

                run_query(title, author, genre);

                if (query_books.isEmpty()) {
                    query_report.setText("No titles found.");
                    query_report.setForeground(new Color(255, 114, 118));
                    return;
                }
                query_report.setForeground(new Color(0, 0, 0));
                query_report.setText(String.format("Found %d titles", query_books.size()));
            }
        });
    }

    private void run_query(String title, String author, String genre) {
        String title_key;
        String author_key;
        String genre_key;
        if (title.isBlank()) {
            title_key = "%";
        } else {
            title_key = title;
        }
        if (author.isBlank()) {
            author_key = "%";
        } else {
            author_key = author;
        }
        if (genre.isBlank()) {
            genre_key = "%";
        } else {
            genre_key = genre;
        }
        query_books = database.selectBookObjects(title_key, author_key, genre_key);
        update_list(0);
    }

    private void update_list(int page_nr) {
        query.removeAll();
        ListIterator<Book> it = query_books.subList(page_nr * 5, Math.min(query_books.size(), page_nr * 5 + 5)).listIterator();
        while (it.hasNext()) {
            Book curr_book = it.next();
            query.add(new BookSearchListElement(curr_book.getTitle(), curr_book.getAuthor(), curr_book.getGenre(), true));
        }

        JPanel white_space = new JPanel();
        white_space.setPreferredSize(new Dimension(1, 1000));
        white_space.setOpaque(false);
        query.add(white_space);

        page_number.setText(String.valueOf(page_nr + 1));
        if (page_nr == 0) {
            left_button.setEnabled(false);
        } else {
            left_button.setEnabled(true);
        }
        if (page_nr * 5 + 5 >= query_books.size()) {
            right_button.setEnabled(false);
        } else {
            right_button.setEnabled(true);
        }
    }
}
