package main.java.org.papz20.model;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class OrderTest {
    private User user = new User(1, "John", "Smith", "john.smith@gmail.com", "jsmith", "password");
    private Book book = new Book(1, "The Hobbit", "J.R.R. Tolkien", "Fantasy", "21/09/1937");
    private Copy copy = new Copy(1, book, true);
    private Order order = new Order(1, user, copy, "21/09/2021", 30, "pending");

    @Test
    public void testGetId() {
        Assertions.assertEquals(1, order.getId());
    }

    @Test
    public void testGetUser() {
        Assertions.assertEquals(user, order.getUser());
    }

    @Test
    public void testGetCopy() {
        Assertions.assertEquals(copy, order.getCopy());
    }

    @Test
    public void testGetDate() {
        Assertions.assertEquals("21/09/2021", order.getDate());
    }

    @Test
    public void testGetPeriod() {
        Assertions.assertEquals(30, order.getPeriod());
    }

    @Test
    public void testGetStatus() {
        Assertions.assertEquals("pending", order.getStatus());
    }

    @Test
    public void testSetId() {
        order.setId(2);
        Assertions.assertEquals(2, order.getId());
    }

    @Test
    public void testSetUser() {
        User user1 = new User(2, "Jane", "Doe", "jane.doe@gmail.com", "jdoe", "newpassword");

        order.setUser(user1);
        Assertions.assertEquals(user1, order.getUser());
    }

    @Test
    public void testSetCopy() {
        Book book1 = new Book(2, "The Lord of the Rings", "J.R.R. Tolkien", "Fantasy", "29/07/1954");
        Copy copy1 = new Copy(2, book1, true);

        order.setCopy(copy1);
        Assertions.assertEquals(copy1, order.getCopy());
    }

    @Test
    public void testSetDate() {
        order.setDate("22/09/2021");
        Assertions.assertEquals("22/09/2021", order.getDate());
    }

    @Test
    public void testSetPeriod() {
        order.setPeriod(14);
        Assertions.assertEquals(14, order.getPeriod());
    }

    @Test
    public void testSetStatus() {
        order.setStatus("approved");
        Assertions.assertEquals("approved", order.getStatus());
    }

    @Test
    public void testSetInvalidId() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            order.setId(-1);
        });
    }

    @Test
    public void testSetInvalidPeriod() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            order.setPeriod(-1);
        });
    }

    @Test
    public void testSetInvalidStatus() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            order.setStatus("invalid");
        });
    }
}
