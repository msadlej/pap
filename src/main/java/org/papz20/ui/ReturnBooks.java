package main.java.org.papz20.ui;

import main.java.org.papz20.model.Book;
import main.java.org.papz20.model.Database;
import main.java.org.papz20.model.Transaction;
import main.java.org.papz20.model.User;
import main.java.org.papz20.services.BookService;
import main.java.org.papz20.services.ReceiveReturnedBookService;
import main.java.org.papz20.services.TransactionService;

import javax.swing.*;
import javax.xml.crypto.Data;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ReturnBooks extends JPanel {
    private JPanel main_panel;
    private JTextField copy_field;
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
    private JLabel borrower_name;
    private JLabel borrower_id;
    private Book borrowed_book;
    private Transaction transaction;
    private User borrower;

    public ReturnBooks() {
        setLayout(new BorderLayout());
        add(main_panel, BorderLayout.CENTER);

        selectButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                clear();
                TransactionService transactionService = new TransactionService();
                int copy_id;
                if (copy_field.getText().isBlank()) {
                    select_message.setText("Field is empty!");
                    return;
                }
                try {
                    copy_id = Integer.parseInt(copy_field.getText());
                } catch (NumberFormatException exception) {
                    select_message.setText("Not a copy id!");
                    return;
                }
                transaction = transactionService.getBookTransaction(copy_id);
                if (transaction == null) {
                    select_message.setText("No book found!");
                    return;
                }
                Database db = new Database();
                borrowed_book = db.fetchBook(db.fetchCopy(transaction.getCopyId()).getBookId());
                borrower = db.fetchUser(transaction.getUserId());
                show_info();
            }
        });
        return_book.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (transaction == null) {
                    return;
                }
                ReceiveReturnedBookService rrbs = new ReceiveReturnedBookService();
                rrbs.receiveReturnedBook(transaction.getId());
                transaction = null;
                return_book.setEnabled(false);
                message.setText("Book successfully returned!");
            }
        });
        clear();
    }
    private void clear() {
        book_panel.setVisible(false);
        return_book.setVisible(false);
        select_message.setText("");
        message.setText("");
    }
    private void show_info() {
        book_panel.setVisible(true);
        return_book.setVisible(true);
        return_book.setEnabled(true);
        book_title.setText(borrowed_book.getTitle());
        book_author.setText(borrowed_book.getAuthor());
        book_genre.setText(borrowed_book.getGenre());
        borrowed_date.setText(String.format("Borrowed: %s", transaction.getCheckoutDate()));
        return_date.setText(String.format("Return Date: %s", transaction.getDueDate()));
        borrower_name.setText(String.format("Borrower: %s", borrower.getUsername()));
        borrower_id.setText(String.format("Borrower's id: %d", borrower.getId()));
    }
}
