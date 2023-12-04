package main.java.org.papz20.ui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainFrame extends JFrame {
    private JPanel main_card_panel;
    private CardLayout main_card_layout;
    private LoginPanel login_panel;
    private CreateAccountPanel create_account_panel;

    public MainFrame() {
        main_card_panel = new JPanel();
        main_card_layout = new CardLayout();
        main_card_panel.setLayout(main_card_layout);
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
                System.out.print(login_panel.getUsername());
                System.out.print(' ');
                System.out.println(login_panel.getPassword());
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
            JFrame window = new MainFrame();
            ((MainFrame)window).init();
            window.setTitle("Library");
            window.setDefaultCloseOperation(EXIT_ON_CLOSE);
            window.setSize(600, 600);
            window.setVisible(true);
            window.setLocationRelativeTo(null);
        });
    }


}
