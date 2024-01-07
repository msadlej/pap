package main.java.org.papz20.model;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class CopyTest {
    private Book book = new Book(1, "The Hobbit", "J.R.R. Tolkien", "Fantasy", "21/09/1937");
    private Copy copy = new Copy(1, book, true);

    @Test
    public void testGetId() {
        Assertions.assertEquals(1, copy.getId());
    }

    @Test
    public void testGetBook() {
        Assertions.assertEquals(book, copy.getBook());
    }

    @Test
    public void testGetAvailable() {
        Assertions.assertTrue(copy.getAvailable());
    }

    @Test
    public void testSetId() {
        copy.setId(2);
        Assertions.assertEquals(2, copy.getId());
    }

    @Test
    public void testSetBook() {
        Book book1 = new Book(2, "Pan Tadeusz", "Adam Mickiewicz", "Epic Poem", "28/06/1834");

        copy.setBook(book1);
        Assertions.assertEquals(book1, copy.getBook());
    }

    @Test
    public void testSetAvailable() {
        copy.setAvailable(false);
        Assertions.assertFalse(copy.getAvailable());
    }

    @Test
    public void testSetInvalidId() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            copy.setId(-1);
        });
    }
}
