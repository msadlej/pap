package main.java.org.papz20.ui;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import java.awt.*;

public class MainPage extends JFrame {
    private JPanel login_panel;
    private JButton login_button;
    private JPasswordField password_field;
    private JTextField username_field;
    private JButton create_account_button;
    private JPanel log_in_square;

    public MainPage() {
        setTitle("Main PAge");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(600, 600);
        setContentPane(login_panel);
    }

}
