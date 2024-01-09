package main.java.org.papz20.model;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class FineTest {
    private Fine fine = new Fine(1, 1, 100, "unpaid");
    @Test
    public void testGetId() {
        Assertions.assertEquals(1, fine.getId());
    }

    @Test
    public void testGetTransactionId() {
        Assertions.assertEquals(1, fine.getTransactionId());
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
    public void testSetTransactionId() {
        fine.setTransactionId(2);
        Assertions.assertEquals(2, fine.getTransactionId());
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
    public void testSetInvalidTransactionId() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            fine.setTransactionId(-1);
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
