package main.java.org.papz20.model;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class FineTest {
    private User user = new User(1, "John", "Smith", "john.smith@gmail.com", "jsmith", "password");
    private Book book = new Book(1, "The Hobbit", "J.R.R. Tolkien", "Fantasy", "21/09/1937");
    private Copy copy = new Copy(1, book, true);
    private Transaction transaction = new Transaction(1, user, copy, "21/09/2021", "21/10/2021", "checked out");
    private Fine fine = new Fine(1, transaction, 100, "unpaid");
    @Test
    public void testGetId() {
        Assertions.assertEquals(1, fine.getId());
    }

    @Test
    public void testGetTransaction() {
        Assertions.assertEquals(transaction, fine.getTransaction());
    }

    @Test
    public void testGetAmount() {
        Assertions.assertEquals(100, fine.getAmount());
    }

    @Test
    public void testGetStatus() {
        Assertions.assertEquals("unpaid", fine.getStatus());
    }

    @Test
    public void testSetId() {
        fine.setId(2);
        Assertions.assertEquals(2, fine.getId());
    }

    @Test
    public void testSetTransaction() {
        Transaction transaction1 = new Transaction(2, user, copy, "21/09/2021", "21/10/2021", "checked out");
        fine.setTransaction(transaction1);
        Assertions.assertEquals(transaction1, fine.getTransaction());
    }

    @Test
    public void testSetAmount() {
        fine.setAmount(200);
        Assertions.assertEquals(200, fine.getAmount());
    }

    @Test
    public void testSetStatus() {
        fine.setStatus("paid");
        Assertions.assertEquals("paid", fine.getStatus());
    }

    @Test
    public void testSetInvalidId() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            fine.setId(-1);
        });
    }

    @Test
    public void testSetInvalidAmount() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            fine.setAmount(-1);
        });
    }

    @Test
    public void testSetInvalidStatus() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            fine.setStatus("invalid");
        });
    }
}
