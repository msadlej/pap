package main.java.org.papz20.ui;

import main.java.org.papz20.model.Database;
import main.java.org.papz20.model.Book;

import javax.swing.*;
import javax.xml.crypto.Data;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
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
    private int curr_user_id;


    public BookSearchPanel() {
        database = new Database();
        database.connectDB();
        setLayout(new BorderLayout());
        add(main_panel, BorderLayout.CENTER);
        query.setLayout(new BoxLayout(query, BoxLayout.Y_AXIS));
        query_books = new ArrayList<>();
        update_list(0);

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
                update_list(0);
            }
        });

        right_button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int page_nr = Integer.parseInt(page_number.getText());
                update_list(page_nr);
            }
        });
        left_button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int page_nr = Integer.parseInt(page_number.getText()) - 2;
                update_list(page_nr);
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
    }

    private void update_list(int page_nr) {
        query.removeAll();
        Database db = new Database();
        db.connectDB();
        ListIterator<Book> it = query_books.subList(page_nr * 5, Math.min(query_books.size(), page_nr * 5 + 5)).listIterator();
        while (it.hasNext()) {
            Book curr_book = it.next();
            boolean available = db.copyAvailableBook(curr_book.getId());
            query.add(new BookSearchListElement(curr_book, available, curr_user_id));
        }

        JPanel white_space = new JPanel();
        white_space.setPreferredSize(new Dimension(1, 1000));
        white_space.setOpaque(false);
        //query.add(white_space);

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
        // scroll to top
        query_scrollable.getVerticalScrollBar().setValue(query_scrollable.getVerticalScrollBar().getMinimum());
    }
    public void setUserId(int user_id) {
        curr_user_id = user_id;
    }

    public void clear() {
        curr_user_id = -1;
        title_search.setText("");
        author_search.setText("");
        genre_search.setText("");
        query.removeAll();
    }
}
