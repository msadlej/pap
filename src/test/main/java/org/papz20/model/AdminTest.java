package main.java.org.papz20.model;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class AdminTest {
    @Test
    void testGetRole() {
        Admin admin = new Admin(1, "John", "Smith", "john.smith@gmail.com", "jsmith", "password");
        Assertions.assertEquals("admin", admin.getRole());
    }
}
