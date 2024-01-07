package main.java.org.papz20.model;

public class Copy {
    private int id;
    private Book book;
    private boolean available;

    public Copy(int id, Book book, boolean available) {
        setId(id);
        setBook(book);
        setAvailable(available);
    }

    public int getId() {
        return this.id;
    }

    public Book getBook() {
        return this.book;
    }

    public boolean getAvailable() {
        return this.available;
    }

    public void setId(int id) throws IllegalArgumentException {
        if (id > 0) {
            this.id = id;
        } else {
            throw new IllegalArgumentException("Copy id must be a positive integer.");
        }
    }

    public void setBook(Book book) {
        this.book = book;
    }

    public void setAvailable(boolean available) {
        this.available = available;
    }
}
