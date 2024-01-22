package test.main.java.org.papz20.services;

import main.java.org.papz20.model.Database;
import main.java.org.papz20.services.AuthenticationService;

import static org.junit.jupiter.api.Assertions.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

class AuthenticationTest {
    @org.junit.jupiter.api.Test
    void authenticate() {
        AuthenticationService authenticationService = new AuthenticationService();
        Integer result = authenticationService.authenticateUser("admin", "password");
        assertFalse(result > 0);

        result = authenticationService.authenticateUser("admin1", "admin1234");
        assertTrue(result > 0);
    }
    @org.junit.jupiter.api.Test
    void changePassword() {
        AuthenticationService authenticationService = new AuthenticationService();
        boolean result = authenticationService.changePassword("admin2", "admin2345", "great_admin");
        assertTrue(result);
        String query = "SELECT * FROM users WHERE username = ?";
        try (
                Connection connection = Database.getInstance().connectDB();
                PreparedStatement preparedStatement = connection.prepareStatement(query)
        ) {
            preparedStatement.setString(1, "admin2");
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    assertEquals("great_admin", resultSet.getString("password"));
                } else {
                    fail();
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            fail();
        }
    }
}
