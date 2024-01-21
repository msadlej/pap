package main.java.org.papz20.ui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainFrame extends JFrame {
    private final JPanel main_card_panel;
    private final CardLayout main_card_layout;
    final private UserAuthenticationPanel user_authentication_panel;
    final private Workspace workspace;

    public MainFrame() {
        main_card_panel = new JPanel();
        main_card_layout = new CardLayout();
        main_card_panel.setLayout(main_card_layout);
        user_authentication_panel = new UserAuthenticationPanel();
        workspace = new Workspace();
    }

    public void init() {
        setContentPane(main_card_panel);
        initAuthenticationPanel();
        initWorkspace();
        main_card_panel.add(user_authentication_panel, "authentication");
        main_card_panel.add(workspace, "workspace");
        main_card_layout.show(main_card_panel, "authentication");
    }

    private void initAuthenticationPanel() {
        ActionListener on_login = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("Logging in!");
                workspace.login(user_authentication_panel.getUserId());
                user_authentication_panel.resetUserId();
                main_card_layout.show(main_card_panel, "workspace");
            }
        };
        user_authentication_panel.init(on_login);
    }

    private void initWorkspace() {
        ActionListener on_logout = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("Logging out!");
                main_card_layout.show(main_card_panel, "authentication");
            }
        };
        workspace.init(on_logout);
    }
}
