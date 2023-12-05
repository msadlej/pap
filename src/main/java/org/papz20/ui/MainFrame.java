package main.java.org.papz20.ui;

import main.java.org.papz20.services.AuthenticationService;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainFrame extends JFrame {
    private final JPanel main_card_panel;
    private final CardLayout main_card_layout;
    private UserAuthenticationPanel user_authentication_panel;

    public MainFrame() {
        main_card_panel = new JPanel();
        main_card_layout = new CardLayout();
        main_card_panel.setLayout(main_card_layout);
        user_authentication_panel = new UserAuthenticationPanel();

    }
    public void init() {
        setContentPane(main_card_panel);
        user_authentication_panel.init();
        main_card_panel.add(user_authentication_panel, "authentication");
        main_card_layout.show(main_card_panel, "authentication");
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            MainFrame window = new MainFrame();
            window.init();
            window.setTitle("Library");
            window.setDefaultCloseOperation(EXIT_ON_CLOSE);
            window.setSize(600, 600);
            window.setVisible(true);
            window.setLocationRelativeTo(null);
        });
    }


}
