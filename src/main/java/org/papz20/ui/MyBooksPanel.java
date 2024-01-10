package main.java.org.papz20.ui;

import main.java.org.papz20.model.Book;
import main.java.org.papz20.model.Database;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

public class MyBooksPanel extends JPanel {

    private JPanel main_panel;
    private JTable books_table;
    private DefaultTableModel book_model;
    private JTable orders_table;
    private DefaultTableModel orders_model;


    public MyBooksPanel() {
        setLayout(new BorderLayout());
        add(main_panel, BorderLayout.CENTER);

        book_model = new DefaultTableModel();
        book_model.setColumnIdentifiers(new String[]{"Title", "Order Date", "Return Date"});
        books_table.setModel(book_model);

        orders_model = new DefaultTableModel();
        orders_model.setColumnIdentifiers(new String[]{"Title", "Date", "Whatever"});
        orders_table.setModel(orders_model);
    }

    public void load_data(int user_id) {
        book_model.setRowCount(0);
        Database db = new Database();
        List<Book> borrowed_books = db.findBorrowedBooks(user_id);
        for (Book book : borrowed_books) {
            book_model.addRow(new String[]{book.getTitle(), book.getAuthor(), book.getGenre()});
        }
    }
}
