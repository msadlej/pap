package test.main.java.org.papz20.services;

import main.java.org.papz20.services.CreateAccountService;

import static org.junit.jupiter.api.Assertions.*;

class CreateAccountServiceTest {

    @org.junit.jupiter.api.Test
    void createAccount() {
        CreateAccountService createAccountService = new CreateAccountService();
        boolean result = createAccountService.createAccount(
                "John",
                "Doe",
                "john_doe@gmail.com",
                "johndoe",
                "password",
                "member"
        );
        assertTrue(result);
        // Delete the user that was just created
    }

    @org.junit.jupiter.api.Test
    void createAccount2() {
        CreateAccountService createAccountService = new CreateAccountService();
        boolean result = createAccountService.createAccount(
                "John",
                "Doe",
                "john_doe@gmail.com",
                "johndoe",
                "password",
                "user"
        );
        assertFalse(result);
    }
}