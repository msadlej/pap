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
        removeBook(book.getId());
    }

    private void removeBook(int bookId) throws IllegalArgumentException{
        if (database.selectBookObject(bookId) != null) {
            database.removeBook(bookId);
        } else {
            throw new IllegalArgumentException("Book does not exist.");
        }
    }

    public List<Book> findBorrowedBooks(int user_id) {
        return database.findBorrowedBooks(user_id);
    }
}
