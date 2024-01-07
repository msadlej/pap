package main.java.org.papz20.model;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class TransactionTest {
    private User user = new User(1, "John", "Smith", "john.smith@gmail.com", "jsmith", "password");
    private Book book = new Book(1, "The Hobbit", "J.R.R. Tolkien", "Fantasy", "21/09/1937");
    private Copy copy = new Copy(1, book, true);
    private Transaction transaction = new Transaction(1, user, copy, "21/09/2021", "21/10/2021", "checked out");

    @Test
    public void testGetId() {
        Assertions.assertEquals(1, transaction.getId());
    }

    @Test
    public void testGetUser() {
        Assertions.assertEquals(user, transaction.getUser());
    }

    @Test
    public void testGetCopy() {
        Assertions.assertEquals(copy, transaction.getCopy());
    }

    @Test
    public void testGetCheckoutDate() {
        Assertions.assertEquals("21/09/2021", transaction.getCheckoutDate());
    }

    @Test
    public void testGetDueDate() {
        Assertions.assertEquals("21/10/2021", transaction.getDueDate());
    }

    @Test
    public void testGetStatus() {
        Assertions.assertEquals("checked out", transaction.getStatus());
    }

    @Test
    public void testSetId() {
        transaction.setId(2);
        Assertions.assertEquals(2, transaction.getId());
    }

    @Test
    public void testSetUser() {
        User user1 = new User(2, "Jane", "Doe", "jane.doe@gmail.com", "jdoe", "newpassword");

        transaction.setUser(user1);
        Assertions.assertEquals(user1, transaction.getUser());
    }

    @Test
    public void testSetCopy() {
        Book book1 = new Book(2, "The Lord of the Rings", "J.R.R. Tolkien", "Fantasy", "29/07/1954");
        Copy copy1 = new Copy(2, book1, true);

        transaction.setCopy(copy1);
        Assertions.assertEquals(copy1, transaction.getCopy());
    }

    @Test
    public void testSetCheckoutDate() {
        transaction.setCheckoutDate("22/09/2021");
        Assertions.assertEquals("22/09/2021", transaction.getCheckoutDate());
    }

    @Test
    public void testSetDueDate() {
        transaction.setDueDate("22/10/2021");
        Assertions.assertEquals("22/10/2021", transaction.getDueDate());
    }

    @Test
    public void testSetStatus() {
        transaction.setStatus("returned");
        Assertions.assertEquals("returned", transaction.getStatus());
    }

    @Test
    public void testSetInvalidId() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            transaction.setId(-1);
        });
    }
}
