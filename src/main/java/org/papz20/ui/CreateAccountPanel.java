package main.java.org.papz20.ui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.Arrays;

public class CreateAccountPanel extends JPanel {
    private JPanel main_panel;
    private JButton submit_button;
    private JTextField surname_field;
    private JTextField name_field;
    private JTextField username_field;
    private JTextField email_field;
    private JPasswordField password_field;
    private JPasswordField password_conformation_field;
    private JPanel inner_panel;
    private JButton backButton;
    private JLabel message_text;

    public  CreateAccountPanel(ActionListener account_creation, ActionListener go_back)
    {
        setLayout(new BorderLayout());
        add(main_panel, BorderLayout.CENTER);
        submit_button.addActionListener(account_creation);
        backButton.addActionListener(go_back);
    };

    public String getSurname() {
        return surname_field.getText();
    }

    public String getUsername() {
        return username_field.getText();
    }

    public String getName() {
        return name_field.getText();
    }

    public String getEmail() {
        return email_field.getText();
    }

    public String getPassword() {
        return new String(password_field.getPassword());
    }

    public void clearConformationPassword() {
        password_conformation_field.setText("");
    }

    public void clearAllFields() {
        username_field.setText("");
        email_field.setText("");
        name_field.setText("");
        surname_field.setText("");
        password_field.setText("");
        password_conformation_field.setText("");
    }

    public boolean arePasswordsMatching() {
        return Arrays.equals(password_field.getPassword(), password_conformation_field.getPassword());
    }

    public void showMessage(String message) {
        message_text.setForeground(new Color(0, 0, 0));
        message_text.setText(message);
    }

    public void showErrorMessage(String error_message) {
        message_text.setForeground(new Color(255, 114, 118));
        message_text.setText(error_message);
    }

    public void clearMessage() {
        message_text.setText("");
    }
}
