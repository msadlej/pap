package main.java.org.papz20.ui;

import main.java.org.papz20.services.AuthenticationService;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class UserAuthenticationPanel extends JPanel {
    private LoginPanel login_panel;
    private CreateAccountPanel create_account_panel;
    private final JPanel main_card_panel;
    private final CardLayout main_card_layout;
    private AuthenticationService authentication_service;

    public UserAuthenticationPanel() {
        main_card_panel = new JPanel();
        main_card_layout = new CardLayout();
        main_card_panel.setLayout(main_card_layout);
        setLayout(new BorderLayout());
        add(main_card_panel, BorderLayout.CENTER);
        authentication_service = new AuthenticationService();
    }

    public void init() {
        initLoginPanel();
        initCreateAccountPanel();
    }

    private void initLoginPanel() {
        ActionListener login_listener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //TODO: Handle loging in
                String username = login_panel.getUsername();
                String password = login_panel.getPassword();
                boolean isAuthenticated = authentication_service.authenticateUser(username, password);
                if (isAuthenticated) {
                    System.out.println("Login successful!");
                } else {
                    login_panel.clearPasswordField();
                    login_panel.showMessage("Invalid username or password!");
                }
            }
        };

        ActionListener account_creation_listener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                login_panel.clearUsernamePassword();
                login_panel.clearMessage();
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
                if (!create_account_panel.arePasswordsMatching())
                {
                    create_account_panel.showErrorMessage("Passwords aren't matching!");
                    create_account_panel.clearConformationPassword();
                    return;
                }
                create_account_panel.clearMessage();
                String username = create_account_panel.getUsername();
                String email = create_account_panel.getEmail();
                String name = create_account_panel.getName();
                String surname = create_account_panel.getSurname();
                String password = create_account_panel.getPassword();
                create_account_panel.showMessage("Account creation request received!");
            }
        };
        ActionListener back_listener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                create_account_panel.clearAllFields();
                create_account_panel.clearMessage();
                main_card_layout.show(main_card_panel, "Login");
            }
        };
        create_account_panel = new CreateAccountPanel(account_creation_listener, back_listener);
        main_card_panel.add(create_account_panel, "CreateAccount");
    }
}
