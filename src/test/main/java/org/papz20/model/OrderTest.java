package main.java.org.papz20.model;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class OrderTest {
    private Order order = new Order(1, 1, 1, "21/09/2021", 30, "pending");

    @Test
    public void testGetId() {
        Assertions.assertEquals(1, order.getId());
    }

    @Test
    public void testGetUserId() {
        Assertions.assertEquals(1, order.getUserId());
    }

    @Test
    public void testGetCopyId() {
        Assertions.assertEquals(1, order.getCopyId());
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
    public void testSetUserId() {
        order.setUserId(2);
        Assertions.assertEquals(2, order.getUserId());
    }

    @Test
    public void testSetCopyId() {
        order.setCopyId(2);
        Assertions.assertEquals(2, order.getCopyId());
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
    public void testSetInvalidUserId() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            order.setUserId(-1);
        });
    }

    @Test
    public void testSetInvalidCopyId() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            order.setCopyId(-1);
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
