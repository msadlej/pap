package main.java.org.papz20.model;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class CopyTest {
    @Test
    public void testGetId() {
        Book book = new Book(1, "The Hobbit", "J.R.R. Tolkien", "Fantasy", "21/09/1937");
        Copy copy = new Copy(1, book, true);
        Assertions.assertEquals(1, copy.getId());
    }

    @Test
    public void testGetBook() {
        Book book = new Book(1, "The Hobbit", "J.R.R. Tolkien", "Fantasy", "21/09/1937");
        Copy copy = new Copy(1, book, true);
        Assertions.assertEquals(book, copy.getBook());
    }

    @Test
    public void testGetAvailable() {
        Book book = new Book(1, "The Hobbit", "J.R.R. Tolkien", "Fantasy", "21/09/1937");
        Copy copy = new Copy(1, book, true);
        Assertions.assertTrue(copy.getAvailable());
    }

    @Test
    public void testSetId() {
        Book book = new Book(1, "The Hobbit", "J.R.R. Tolkien", "Fantasy", "21/09/1937");
        Copy copy = new Copy(1, book, true);

        copy.setId(2);
        Assertions.assertEquals(2, copy.getId());
    }

    @Test
    public void testSetBook() {
        Book book1 = new Book(1, "The Hobbit", "J.R.R. Tolkien", "Fantasy", "21/09/1937");
        Book book2 = new Book(2, "Pan Tadeusz", "Adam Mickiewicz", "Epic Poem", "28/06/1834");
        Copy copy = new Copy(1, book1, true);

        copy.setBook(book2);
        Assertions.assertEquals(book2, copy.getBook());
    }

    @Test
    public void testSetAvailable() {
        Book book = new Book(1, "The Hobbit", "J.R.R. Tolkien", "Fantasy", "21/09/1937");
        Copy copy = new Copy(1, book, true);

        copy.setAvailable(false);
        Assertions.assertFalse(copy.getAvailable());
    }

    @Test
    public void testSetInvalidId() {
        Book book = new Book(1, "The Hobbit", "J.R.R. Tolkien", "Fantasy", "21/09/1937");
        Copy copy = new Copy(1, book, true);

        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            copy.setId(-1);
        });
    }
}
