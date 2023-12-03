package main.java.org.papz20.ui;

import javax.swing.*;
import java.awt.*;

public class MainWindow extends JFrame {
    private JPanel leftPanel;
    private JPanel rightPanel;
    private JLabel titleLabel;
    private LoginForm loginForm;
    private CreateAccountForm createAccountForm;

    public MainWindow() {
        initUI();
    }

    private void initUI() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle("Library Management System");
        setSize(800, 600);
        setLayout(new GridLayout(1, 2));

        leftPanel = new JPanel(new BorderLayout());
        leftPanel.setBackground(UIConstants.BACKGROUND_COLOR);
        leftPanel.setBorder(BorderFactory.createEmptyBorder(
                UIConstants.FORM_MARGIN_TOP,
                UIConstants.FORM_MARGIN_SIDES,
                UIConstants.FORM_MARGIN_BOTTOM,
                UIConstants.FORM_MARGIN_SIDES
                ));
        rightPanel = new JPanel();
        rightPanel.setBackground(UIConstants.BACKGROUND_COLOR);

        ImageIcon background = new ImageIcon(getClass().getResource("/images/mainBackground.jpg"));
        Image resizedBackground = background.getImage().getScaledInstance(800, 600, Image.SCALE_SMOOTH);
        JLabel backgroundLabel = new JLabel(new ImageIcon(resizedBackground));
        rightPanel.add(backgroundLabel);

        showLoginForm();

        add(leftPanel);
        add(rightPanel);

        setLocationRelativeTo(null);
        setVisible(true);
    }

    private void showMainTitle() {
        titleLabel = new JLabel(UIConstants.MAIN_WINDOW_TITLE, SwingConstants.CENTER);
        titleLabel.setBounds(270, 1, 200, 100);
        titleLabel.setFont(new Font("Geomanist", Font.BOLD, 25));
        titleLabel.setForeground(UIConstants.FORM_TEXT_COLOR);
        leftPanel.add(titleLabel, BorderLayout.NORTH);
    }

    public void showLoginForm() {
        leftPanel.removeAll();
        showMainTitle();

        loginForm = new LoginForm(this);
        leftPanel.add(loginForm, BorderLayout.CENTER);
        leftPanel.revalidate();
        leftPanel.repaint();
    }

    public void showCreateAccountForm() {
        leftPanel.removeAll();
        showMainTitle();

        createAccountForm = new CreateAccountForm(this);
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
