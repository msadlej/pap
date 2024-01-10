package main.java.org.papz20.ui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Workspace extends JPanel {
    private JPanel view_panel;
    private JPanel side_panel;
    private JPanel workspace_panel;
    private final CardLayout workspace_layout;
    private JButton search_select;
    private BookSearchPanel search;
    private JButton my_books_select;
    private JButton my_account_select;
    private JButton logOut;
    private JLabel username_field;


    public Workspace() {
        // Setup card layout
        workspace_panel = new JPanel();
        workspace_layout = new CardLayout();
        workspace_panel.setLayout(workspace_layout);
        setLayout(new BorderLayout());
        add(view_panel, BorderLayout.CENTER);

        // init BookSearchPanel and corresponding button
        search = new BookSearchPanel();

        // Set Search as starting view
        search_select.setEnabled(false);
        workspace_layout.show(workspace_panel ,"search");
    }

    public void setUsername(String username) {
        username_field.setText(username);
    }

    public void init(ActionListener on_logout) {
        initLogout(on_logout);
        initBookSearch();
    }

    private void initBookSearch() {
        ActionListener search_select_listener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                enable_buttons();
                search_select.setEnabled(false);
                workspace_layout.show(workspace_panel, "BookSearch");
            }
        };
        search_select.addActionListener(search_select_listener);
        workspace_panel.add(search, "search");
    }

    private void initLogout(ActionListener on_logout) {
        ActionListener logout_listener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                on_logout.actionPerformed(e);
            }
        };
        logOut.addActionListener(logout_listener);
    }

    private void enable_buttons() {
        search_select.setEnabled(true);
        my_books_select.setEnabled(true);
        my_account_select.setEnabled(true);
    }
}
