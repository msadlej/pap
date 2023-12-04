package main.java.org.papz20.ui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LoginPanel extends JPanel {

    private JPanel login_panel;
    private JButton login_button;
    private JPasswordField password_field;
    private JTextField username_field;
    private JButton create_account_button;
    private JPanel log_in_square;

    public LoginPanel(ActionListener login_listener, ActionListener account_creaton_listener) {
        setLayout(new BorderLayout());
        add(login_panel, BorderLayout.CENTER);
        login_button.addActionListener(login_listener);
        create_account_button.addActionListener(account_creaton_listener);
    }

    public String getUsername() {
        return username_field.getText();
    }

    public String getPassword() {
        return new String(password_field.getPassword());
    }

    public void clearFields() {
        username_field.setText("");
        password_field.setText("");
    }
}