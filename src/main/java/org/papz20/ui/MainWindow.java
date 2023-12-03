package main.java.org.papz20.ui;

import javax.swing.*;
import java.awt.*;

public class MainWindow extends JFrame {
    private JPanel leftPanel;
    private JPanel rightPanel;

    public MainWindow() {
        initUI();
    }

    private void initUI() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle("Library Management System");
        setSize(800, 600);
        setLayout(new GridLayout(1, 2));

        leftPanel = new JPanel(new BorderLayout());
        rightPanel = new JPanel();

        ImageIcon background = new ImageIcon("src/main/resources/image/mainBackground.jpg");
        JLabel backgroundLabel = new JLabel(background);
        rightPanel.add(backgroundLabel);

        showLoginForm();

        add(leftPanel);
        add(rightPanel);

        setLocationRelativeTo(null);
        setVisible(true);
    }

    public void showLoginForm() {
        leftPanel.removeAll();
        leftPanel.add(new JLabel("Library Management System", SwingConstants.CENTER), BorderLayout.NORTH);

        LoginForm loginForm = new LoginForm(this);
        leftPanel.add(loginForm, BorderLayout.CENTER);
        leftPanel.revalidate();
        leftPanel.repaint();
    }

    public void showCreateAccountForm() {
        leftPanel.removeAll();
        leftPanel.add(new JLabel("Library Management System", SwingConstants.CENTER), BorderLayout.NORTH);

        CreateAccountForm createAccountForm = new CreateAccountForm(this);
        leftPanel.add(createAccountForm, BorderLayout.CENTER);
        leftPanel.revalidate();
        leftPanel.repaint();
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new MainWindow();
        });
    }
}
