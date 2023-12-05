package main.java.org.papz20.ui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

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

    public  CreateAccountPanel(ActionListener account_creation, ActionListener go_back)
    {
        setLayout(new BorderLayout());
        add(main_panel, BorderLayout.CENTER);
        submit_button.addActionListener(account_creation);
        backButton.addActionListener(go_back);
    };
}
