package main.java.org.papz20.model;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class BookTest {
    @Test
    public void testGetId() {
        Book book = new Book(1, "The Hobbit", "J.R.R. Tolkien", "Fantasy", "21/09/1937");
        Assertions.assertEquals(1, book.getId());
    }

    @Test
    public void testGetTitle() {
        Book book = new Book(1, "The Hobbit", "J.R.R. Tolkien", "Fantasy", "21/09/1937");
        Assertions.assertEquals("The Hobbit", book.getTitle());
    }

    @Test
    public void testGetAuthor() {
        Book book = new Book(1, "The Hobbit", "J.R.R. Tolkien", "Fantasy", "21/09/1937");
        Assertions.assertEquals("J.R.R. Tolkien", book.getAuthor());
    }

    @Test
    public void testGetGenre() {
        Book book = new Book(1, "The Hobbit", "J.R.R. Tolkien", "Fantasy", "21/09/1937");
        Assertions.assertEquals("Fantasy", book.getGenre());
    }

    @Test
    public void testGetPublishDate() {
        Book book = new Book(1, "The Hobbit", "J.R.R. Tolkien", "Fantasy", "21/09/1937");
        Assertions.assertEquals("21/09/1937", book.getPublishDate());
    }

    @Test
    public void testSetId() {
        Book book = new Book(1, "The Hobbit", "J.R.R. Tolkien", "Fantasy", "21/09/1937");

        book.setId(2);
        Assertions.assertEquals(2, book.getId());
    }

    @Test
    public void testSetTitle() {
        Book book = new Book(1, "The Hobbit", "J.R.R. Tolkien", "Fantasy", "21/09/1937");

        book.setTitle("Pan Tadeusz");
        Assertions.assertEquals("Pan Tadeusz", book.getTitle());
    }

    @Test
    public void testSetAuthor() {
        Book book = new Book(1, "The Hobbit", "J.R.R. Tolkien", "Fantasy", "21/09/1937");

        book.setAuthor("Adam Mickiewicz");
        Assertions.assertEquals("Adam Mickiewicz", book.getAuthor());
    }

    @Test
    public void testSetGenre() {
        Book book = new Book(1, "The Hobbit", "J.R.R. Tolkien", "Fantasy", "21/09/1937");

        book.setGenre("Epic Poem");
        Assertions.assertEquals("Epic Poem", book.getGenre());
    }

    @Test
    public void testSetPublishDate() {
        Book book = new Book(1, "The Hobbit", "J.R.R. Tolkien", "Fantasy", "21/09/1937");

        book.setPublishDate("28/06/1834");
        Assertions.assertEquals("28/06/1834", book.getPublishDate());
    }

    @Test
    public void testSetInvalidId() {
        Book book = new Book(1, "The Hobbit", "J.R.R. Tolkien", "Fantasy", "21/09/1937");

        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            book.setId(-1);
        });
    }

    @Test
    public void testSetInvalidTitle() {
        Book book = new Book(1, "The Hobbit", "J.R.R. Tolkien", "Fantasy", "21/09/1937");

        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            book.setTitle("");
        });
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            book.setTitle("This is a very long title that is over 30 characters");
        });
    }

    @Test
    public void testSetInvalidAuthor() {
        Book book = new Book(1, "The Hobbit", "J.R.R. Tolkien", "Fantasy", "21/09/1937");

        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            book.setAuthor("");
        });
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            book.setAuthor("This is a very long author name that is over 30 characters");
        });
    }

    @Test
    public void testSetInvalidGenre() {
        Book book = new Book(1, "The Hobbit", "J.R.R. Tolkien", "Fantasy", "21/09/1937");

        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            book.setGenre("");
        });
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            book.setGenre("This is a very long genre name that is over 30 characters");
        });
    }

    @Test
    public void testSetInvalidPublishDate() {
        Book book = new Book(1, "The Hobbit", "J.R.R. Tolkien", "Fantasy", "21/09/1937");

        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            book.setPublishDate("");
        });
    }
}
