package main.java.org.papz20.ui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class LoginPanel extends JPanel {

    private JPanel login_panel;
    private JButton login_button;
    private JPasswordField password_field;
    private JTextField username_field;
    private JButton create_account_button;
    private JPanel log_in_square;
    private JLabel message_text;

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

    public void clearUsernamePassword() {
        username_field.setText("");
        password_field.setText("");
    }

    public void clearPasswordField() {
        password_field.setText("");
    }

    public void showMessage(String Message)
    {
        message_text.setText(Message);
    }

    public void clearMessage() {
        message_text.setText("");
    }
}