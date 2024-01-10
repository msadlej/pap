package main.java.org.papz20.model;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;


public class UserTest {
    private User user = new User(1, "John", "Smith", "john.smith@gmail.com", "jsmith", "password");

    @Test
    public void testGetId() {
        Assertions.assertEquals(1, user.getId());
    }

    @Test
    public void testGetFirstName() {
        Assertions.assertEquals("John", user.getFirstName());
    }

    @Test
    public void testGetLastName() {
        Assertions.assertEquals("Smith", user.getLastName());
    }

    @Test
    public void testGetEmail() {
        Assertions.assertEquals("john.smith@gmail.com", user.getEmail());
    }

    @Test
    public void testGetUsername() {
        Assertions.assertEquals("jsmith", user.getUsername());
    }

    @Test
    public void testGetPassword() {
        Assertions.assertEquals("password", user.getPassword());
    }

    @Test
    public void testSetId() {
        user.setId(2);
        Assertions.assertEquals(2, user.getId());
    }

    @Test
    public void testSetFirstName() {
        user.setFirstName("Jane");
        Assertions.assertEquals("Jane", user.getFirstName());
    }

    @Test
    public void testSetLastName() {
        user.setLastName("Doe");
        Assertions.assertEquals("Doe", user.getLastName());
    }

    @Test
    public void testSetEmail() {
        user.setEmail("jane.doe@gmail.com");
        Assertions.assertEquals("jane.doe@gmail.com", user.getEmail());
    }

    @Test
    public void testSetUsername() {
        user.setUsername("jdoe");
        Assertions.assertEquals("jdoe", user.getUsername());
    }

    @Test
    public void testSetPassword() {
        user.setPassword("newpassword");
        Assertions.assertEquals("newpassword", user.getPassword());
    }

    @Test
    public void testSetInvalidId() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            user.setId(-1);
        });
    }

    @Test
    public void testSetInvalidFirstName() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            user.setFirstName("");
        });
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            user.setFirstName("This is a very long first name that is over 30 characters");
        });
    }

    @Test
    public void testSetInvalidLastName() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            user.setLastName("");
        });
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            user.setLastName("This is a very long last name that is over 30 characters");
        });
    }

    @Test
    public void testSetInvalidEmail() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            user.setEmail("");
        });
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            user.setEmail("This is a very long email address that is over 50 characters");
        });
    }

    @Test
    public void testSetInvalidUsername() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            user.setUsername("");
        });
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            user.setUsername("This is a very long username that is over 30 characters");
        });
    }

    @Test
    public void testSetInvalidPassword() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            user.setPassword("");
        });
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            user.setPassword("This is a very long password that is over 30 characters");
        });
    }
}
