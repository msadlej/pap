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
}
