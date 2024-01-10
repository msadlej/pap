package main.java.org.papz20.model;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class CopyTest {
    private Copy copy = new Copy(1, 1, true);

    @Test
    public void testGetId() {
        Assertions.assertEquals(1, copy.getId());
    }

    @Test
    public void testGetBook() {
        Assertions.assertEquals(1, copy.getBookId());
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
    public void testSetBookId() {
        copy.setBookId(2);
        Assertions.assertEquals(2, copy.getBookId());
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

    @Test
    public void testSetInvalidBookId() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            copy.setBookId(-1);
        });
    }
}
