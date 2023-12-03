package main.java.org.papz20.ui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CreateAccountForm extends JPanel {
    private JTextField firstNameField;
    private JTextField lastNameField;
    private JTextField emailField;
    private JTextField usernameField;
    private JPasswordField passwordField;
    private JPasswordField confirmPasswordField;

    public CreateAccountForm(MainWindow mainWindow) {
        initUI(mainWindow);
    }

    private void initUI(MainWindow mainWindow) {
        setLayout(new GridLayout(7, 2));

        JLabel firstNameLabel = new JLabel("First Name:");
        firstNameLabel.setForeground(UIConstants.FORM_TEXT_COLOR);
        firstNameField = new JTextField();
        JLabel lastNameLabel = new JLabel("Last Name:");
        lastNameLabel.setForeground(UIConstants.FORM_TEXT_COLOR);
        lastNameField = new JTextField();
        JLabel emailLabel = new JLabel("Email:");
        emailLabel.setForeground(UIConstants.FORM_TEXT_COLOR);
        emailField = new JTextField();
        JLabel usernameLabel = new JLabel("Username:");
        usernameLabel.setForeground(UIConstants.FORM_TEXT_COLOR);
        usernameField = new JTextField();
        JLabel passwordLabel = new JLabel("Password:");
        passwordLabel.setForeground(UIConstants.FORM_TEXT_COLOR);
        passwordField = new JPasswordField();
        JLabel confirmPasswordLabel = new JLabel("Confirm Password:");
        confirmPasswordLabel.setForeground(UIConstants.FORM_TEXT_COLOR);
        confirmPasswordField = new JPasswordField();

        JButton returnButton = new JButton("Return");
        JButton confirmButton = new JButton("Confirm");

        returnButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Switch back to the login form
                mainWindow.showLoginForm();
            }
        });

        confirmButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Handle create account logic
                // Switch back to the login form after account creation
                mainWindow.showLoginForm();
            }
        });

        customizeButton(returnButton);
        customizeButton(confirmButton);

        add(firstNameLabel);
        add(firstNameField);
        add(lastNameLabel);
        add(lastNameField);
        add(emailLabel);
        add(emailField);
        add(usernameLabel);
        add(usernameField);
        add(passwordLabel);
        add(passwordField);
        add(confirmPasswordLabel);
        add(confirmPasswordField);
        add(returnButton);
        add(confirmButton);
    }

    private void customizeButton(JButton button) {
        button.setBackground(UIConstants.BUTTON_COLOR);
        button.setForeground(UIConstants.BUTTON_TEXT_COLOR);
    }
}
