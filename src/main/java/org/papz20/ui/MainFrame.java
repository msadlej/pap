package main.java.org.papz20.ui;

import main.java.org.papz20.services.AuthenticationService;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainFrame extends JFrame {
    private JPanel main_card_panel;
    private CardLayout main_card_layout;
    private LoginPanel login_panel;
    private CreateAccountPanel create_account_panel;
    private AuthenticationService authentication_service;

    public MainFrame() {
        main_card_panel = new JPanel();
        main_card_layout = new CardLayout();
        main_card_panel.setLayout(main_card_layout);
        authentication_service = new AuthenticationService();

    }

    public void init() {
        setContentPane(main_card_panel);
        initLoginPanel();
        initCreateAccountPanel();
    }

    private void initLoginPanel()
    {
        ActionListener login_listener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //TODO: Handle loging in
                String username = login_panel.getUsername();
                String password = login_panel.getPassword();
                System.out.format("Login: %s\nPassword: %s\n", username, password);
                boolean isAuthenticated = authentication_service.authenticateUser(username, password);
                if (isAuthenticated) {
                    System.out.println("Login successful!");
                }
                else {
                    System.out.println("Invalid username or password!");
                }
            }
        };
        ActionListener account_creation_listener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                login_panel.clearFields();
                main_card_layout.show(main_card_panel, "CreateAccount");
            }
        };
        login_panel = new LoginPanel(login_listener, account_creation_listener);
        main_card_panel.add(login_panel, "Login");
    }

    private void initCreateAccountPanel()
    {
        ActionListener account_creation_listener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //TODO: Handle creating account
                System.out.println("Account creation requested!");
            }
        };
        ActionListener back_listener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                main_card_layout.show(main_card_panel, "Login");
            }
        };
        create_account_panel = new CreateAccountPanel(account_creation_listener, back_listener);
        main_card_panel.add(create_account_panel, "CreateAccount");
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
