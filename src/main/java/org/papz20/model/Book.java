package main.java.org.papz20.model;

public class Book {

    private int book_id;
    private String title;
    private String author;
    private String genre;
    private String publish_date;

    public Book(int book_id, String title, String author, String genre, String publish_date) {
        setBookId(book_id);
        setTitle(title);
        setAuthor(author);
        setGenre(genre);
        setPublishDate(publish_date);
    }

    public int getId(){ // consistency with User class getter
        return this.book_id;
    }

    public String getTitle() {
        return this.title;
    }

    public String getAuthor() {
        return this.author;
    }

    public String getGenre() {
        return this.genre;
    }

    public String getPublishDate() {
        return this.publish_date;
    }

    public void setBookId(int book_id) throws IllegalArgumentException{
        if (book_id > 0) {
            this.book_id = book_id;
        } else {
            throw new IllegalArgumentException("Book id must be a positive integer.");
        }
    }

    public void setTitle(String title) throws IllegalArgumentException{
        if (!title.isEmpty() && title.length() <= 60) {
            this.title = title;
        } else {
            throw new IllegalArgumentException("Title cannot empty or over 60 characters.");
        }
    }

    public void setAuthor(String author) {
        if (!author.isEmpty() && author.length() <= 50) {
            this.author = author;
        } else {
            throw new IllegalArgumentException("Author cannot be empty or over 50 characters.");
        }
    }

    public void setGenre(String genre) {
        if (!genre.isEmpty() && genre.length() <= 30) {
            this.genre = genre;
        } else {
            throw new IllegalArgumentException("Genre cannot be empty or over 30 characters.");
        }
    }

    public void setPublishDate(String publish_date) {
        // Add additional validation for date format if needed
        if (!publish_date.isEmpty()) {
            this.publish_date = publish_date;
        } else{
            throw new IllegalArgumentException("Publish date cannot be empty.");
        }
    }
}
