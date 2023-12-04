package main.java.org.papz20.ui;

import main.java.org.papz20.services.AuthenticationService;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LoginForm extends JPanel {
    private JTextField usernameField;
    private JPasswordField passwordField;
    private AuthenticationService authenticationService;

    public LoginForm(MainWindow mainWindow) {
        this.authenticationService = new AuthenticationService();
        initUI(mainWindow);
    }

    private void initUI(MainWindow mainWindow) {
        setLayout(new GridLayout(5, 2));
        setBackground(UIConstants.FORM_BACKGROUND_COLOR);

        JLabel usernameLabel = new JLabel("Username:");
        usernameLabel.setForeground(UIConstants.FORM_TEXT_COLOR);
        usernameField = new JTextField();
        JLabel passwordLabel = new JLabel("Password:");
        passwordLabel.setForeground(UIConstants.FORM_TEXT_COLOR);
        passwordField = new JPasswordField();

        JButton signInButton = new JButton("Sign In");
        JButton exitButton = new JButton("Exit");
        JButton createAccountButton = new JButton("Create Account");

        signInButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String username = usernameField.getText();
                String password = new String(passwordField.getPassword());

                boolean isAuthenticated = authenticationService.authenticateUser(username, password);
                if (isAuthenticated) {
                    JOptionPane.showMessageDialog(LoginForm.this, "Login successful!");
                }
                else {
                    JOptionPane.showMessageDialog(LoginForm.this, "Invalid username or password!");
                }
            }
        });

        exitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });

        createAccountButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mainWindow.showCreateAccountForm();
            }
        });

        customizeButton(signInButton);
        customizeButton(exitButton);
        customizeButton(createAccountButton);

        add(usernameLabel);
        add(usernameField);
        add(passwordLabel);
        add(passwordField);
        add(signInButton);
        add(exitButton);
        add(new JLabel()); // Empty label for spacing
        add(createAccountButton);
    }

    private void customizeButton(JButton button) {
        button.setBackground(UIConstants.BUTTON_COLOR);
        button.setForeground(UIConstants.BUTTON_TEXT_COLOR);
        button.setFocusPainted(false);
    }
}
