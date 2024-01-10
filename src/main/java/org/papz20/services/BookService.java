package main.java.org.papz20.services;

import main.java.org.papz20.model.Database;
import main.java.org.papz20.model.Book;
import java.util.List;

public class BookService {
    private Database database;

    public BookService() { this.database = new Database(); }

    public void addBook(Book book) {
        database.addBook(book);
    }

    public void addBook(int bookId, String title, String author, String genre, String publishDate) {
        database.addBook(bookId, title, author, genre, publishDate);
    }

    public void addBook(String title, String author, String genre, String publishDate) {
        database.addBook(title, author, genre, publishDate);
    }

    public void removeBook(Book book) {
        removeBook(book.getId());
    }

    public void removeBook(int bookId) throws IllegalArgumentException{
        if (database.fetchBook(bookId) != null) {
            database.removeBook(bookId);
        } else {
            throw new IllegalArgumentException("Book does not exist.");
        }
    }

    public List<Book> findBorrowedBooks(int user_id) {
        return database.findBorrowedBooks(user_id);
    }
}
