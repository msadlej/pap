package main.java.org.papz20.services;

import main.java.org.papz20.model.Database;
import main.java.org.papz20.model.Book;
import java.sql.SQLException;
import java.util.List;

public class BookService {
    private Database database;

    public BookService() { this.database = new Database(); }

    private void addBook(Book book) {
        database.addBook(book);
    }

    private void addBook(int bookId, String title, String author, String genre, String publishDate) {
        database.addBook(bookId, title, author, genre, publishDate);
    }

    private void addBook(String title, String author, String genre, String publishDate) {
        database.addBook(title, author, genre, publishDate);
    }

    private void removeBook(Book book) {
        database.removeBook(book);
    }

    private void removeBook(int bookId) {
        database.removeBook(bookId);
    }

    public List<Book> findBorrowedBooks(int user_id) {
        return database.findBorrowedBooks(user_id);
    }
}
