package main.java.org.papz20.ui;

import main.java.org.papz20.model.Database;
import main.java.org.papz20.model.User;

import javax.swing.*;
import javax.xml.crypto.Data;
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
    private MyBooksPanel my_books;
    private JButton my_account_select;
    private JButton logOut;
    private JLabel username_field;
    private User user;


    public Workspace() {
        // Setup card layout
        workspace_panel = new JPanel();
        workspace_layout = new CardLayout();
        workspace_panel.setLayout(workspace_layout);
        setLayout(new BorderLayout());
        add(view_panel, BorderLayout.CENTER);

        // init BookSearchPanel
        search = new BookSearchPanel();

        // init MyBooksPanel
        my_books = new MyBooksPanel();

        // Set Search as starting view
        selectBookSearch();
    }

    public void setUser(int user_id) {
        Database db = new Database();
        user = db.fetchUser(user_id);
        username_field.setText(user.getUsername());
    }

    public void init(ActionListener on_logout) {
        initLogout(on_logout);
        initBookSearch();
        initMyBooks();
    }

    private void initBookSearch() {
        ActionListener search_select_listener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                selectBookSearch();
            }
        };
        search_select.addActionListener(search_select_listener);
        workspace_panel.add(search, "search");
    }

    private void initMyBooks() {
        ActionListener my_books_select_listener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                selectMyBooks();
            }
        };
        my_books_select.addActionListener(my_books_select_listener);
        workspace_panel.add(search, "myBooks");
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

    private void selectBookSearch() {
        enable_buttons();
        search_select.setEnabled(false);
        workspace_layout.show(workspace_panel, "search");
    }

    private void selectMyBooks() {
        enable_buttons();
        my_books_select.setEnabled(false);
        workspace_layout.show(workspace_panel, "myBooks");
    }
}
