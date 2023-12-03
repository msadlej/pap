package main.java.org.papz20.ui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LoginForm extends JPanel {
    private JTextField usernameField;
    private JPasswordField passwordField;

    public LoginForm(MainWindow mainWindow) {
        initUI(mainWindow);
    }

    private void initUI(MainWindow mainWindow) {
        setLayout(new GridLayout(5, 2));

        JLabel usernameLabel = new JLabel("Username:");
        usernameField = new JTextField();
        JLabel passwordLabel = new JLabel("Password:");
        passwordField = new JPasswordField();

        JButton signInButton = new JButton("Sign In");
        JButton exitButton = new JButton("Exit");
        JButton createAccountButton = new JButton("Create Account");

        signInButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // TODO: Handle sign-in logic
                // TODO: Check user credentials
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

        add(usernameLabel);
        add(usernameField);
        add(passwordLabel);
        add(passwordField);
        add(signInButton);
        add(exitButton);
        add(new JLabel()); // Empty label for spacing
        add(createAccountButton);
    }

}
