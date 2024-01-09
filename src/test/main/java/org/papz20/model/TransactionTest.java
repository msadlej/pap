package main.java.org.papz20.model;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class TransactionTest {
    private Transaction transaction = new Transaction(1, 1,1, 1, "21/09/2021", "21/10/2021", "checked out");

    @Test
    public void testGetId() {
        Assertions.assertEquals(1, transaction.getId());
    }

    @Test
    public void testGetOrderId() {
        Assertions.assertEquals(1, transaction.getOrderId());
    }

    @Test
    public void testGetUserId() {
        Assertions.assertEquals(1, transaction.getUserId());
    }

    @Test
    public void testGetCopyId() {
        Assertions.assertEquals(1, transaction.getCopyId());
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
    public void testSetOrderId() {
        transaction.setOrderId(2);
        Assertions.assertEquals(2, transaction.getOrderId());
    }

    @Test
    public void testSetUserId() {
        transaction.setUserId(2);
        Assertions.assertEquals(2, transaction.getUserId());
    }

    @Test
    public void testSetCopyId() {
        transaction.setCopyId(2);
        Assertions.assertEquals(2, transaction.getCopyId());
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

    @Test
    public void testSetInvalidOrderId() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            transaction.setOrderId(-1);
        });
    }

    @Test
    public void testSetInvalidUserId() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            transaction.setUserId(-1);
        });
    }

    @Test
    public void testSetInvalidCopyId() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            transaction.setCopyId(-1);
        });
    }

    @Test
    public void testSetInvalidStatus() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            transaction.setStatus("invalid");
        });
    }
}
