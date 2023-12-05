package main.java.org.papz20.model;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;


public class UserTest {
    @Test
    public void testGetId() {
        User user = new User(1, "John", "Smith", "john.smith@gmail.com", "jsmith", "password");
        Assertions.assertEquals(1, user.getId());
    }

    @Test
    public void testGetFirstName() {
        User user = new User(1, "John", "Smith", "john.smith@gmail.com", "jsmith", "password");
        Assertions.assertEquals("John", user.getFirstName());
    }

    @Test
    public void testGetLastName() {
        User user = new User(1, "John", "Smith", "john.smith@gmail.com", "jsmith", "password");
        Assertions.assertEquals("Smith", user.getLastName());
    }

    @Test
    public void testGetEmail() {
        User user = new User(1, "John", "Smith", "john.smith@gmail.com", "jsmith", "password");
        Assertions.assertEquals("john.smith@gmail.com", user.getEmail());
    }

    @Test
    public void testGetUsername() {
        User user = new User(1, "John", "Smith", "john.smith@gmail.com", "jsmith", "password");
        Assertions.assertEquals("jsmith", user.getUsername());
    }

    @Test
    public void testGetPassword() {
        User user = new User(1, "John", "Smith", "john.smith@gmail.com", "jsmith", "password");
        Assertions.assertEquals("password", user.getPassword());
    }

    @Test
    public void testSetId() {
        User user = new User(1, "John", "Smith", "john.smith@gmail.com", "jsmith", "password");

        user.setId(2);
        Assertions.assertEquals(2, user.getId());
    }

    @Test
    public void testSetFirstName() {
        User user = new User(1, "John", "Smith", "john.smith@gmail.com", "jsmith", "password");

        user.setFirstName("Jane");
        Assertions.assertEquals("Jane", user.getFirstName());
    }

    @Test
    public void testSetLastName() {
        User user = new User(1, "John", "Smith", "john.smith@gmail.com", "jsmith", "password");

        user.setLastName("Doe");
        Assertions.assertEquals("Doe", user.getLastName());
    }

    @Test
    public void testSetEmail() {
        User user = new User(1, "John", "Smith", "john.smith@gmail.com", "jsmith", "password");

        user.setEmail("jane.doe@gmail.com");
        Assertions.assertEquals("jane.doe@gmail.com", user.getEmail());
    }

    @Test
    public void testSetUsername() {
        User user = new User(1, "John", "Smith", "john.smith@gmail.com", "jsmith", "password");

        user.setUsername("jdoe");
        Assertions.assertEquals("jdoe", user.getUsername());
    }

    @Test
    public void testSetPassword() {
        User user = new User(1, "John", "Smith", "john.smith@gmail.com", "jsmith", "password");

        user.setPassword("newpassword");
        Assertions.assertEquals("newpassword", user.getPassword());
    }

    @Test
    public void testSetInvalidId() {
        User user = new User(1, "John", "Smith", "john.smith@gmail.com", "jsmith", "password");

        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            user.setId(-1);
        });
    }

    @Test
    public void testSetInvalidFirstName() {
        User user = new User(1, "John", "Smith", "john.smith@gmail.com", "jsmith", "password");

        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            user.setFirstName("");
        });
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            user.setFirstName("This is a very long first name that is over 30 characters");
        });
    }

    @Test
    public void testSetInvalidLastName() {
        User user = new User(1, "John", "Smith", "john.smith@gmail.com", "jsmith", "password");

        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            user.setLastName("");
        });
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            user.setLastName("This is a very long last name that is over 30 characters");
        });
    }

    @Test
    public void testSetInvalidEmail() {
        User user = new User(1, "John", "Smith", "john.smith@gmail.com", "jsmith", "password");

        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            user.setEmail("");
        });
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            user.setEmail("This is a very long email address that is over 50 characters");
        });
    }

    @Test
    public void testSetInvalidUsername() {
        User user = new User(1, "John", "Smith", "john.smith@gmail.com", "jsmith", "password");

        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            user.setUsername("");
        });
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            user.setUsername("This is a very long username that is over 30 characters");
        });
    }

    @Test
    public void testSetInvalidPassword() {
        User user = new User(1, "John", "Smith", "john.smith@gmail.com", "jsmith", "password");

        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            user.setPassword("");
        });
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            user.setPassword("This is a very long password that is over 30 characters");
        });
    }
}
