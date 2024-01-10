package main.java.org.papz20.model;

public class Copy {
    private int id;
    private int book_id;
    private boolean available;

    public Copy(int id, int book_id, boolean available) {
        setId(id);
        setBookId(book_id);
        setAvailable(available);
    }

    public int getId() {
        return this.id;
    }

    public int getBookId() {
        return this.book_id;
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

    public void setBookId(int book_id) {
        if (book_id > 0) {
            this.book_id = book_id;
        } else {
            throw new IllegalArgumentException("Book id must be a positive integer.");
        }
    }

    public void setAvailable(boolean available) {
        this.available = available;
    }
}
