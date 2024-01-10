package main.java.org.papz20.ui;

import main.java.org.papz20.model.Database;
import main.java.org.papz20.model.User;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Workspace extends JPanel {
    private JPanel view_panel;
    private JPanel side_panel;
    private JPanel workspace_panel;
    private CardLayout workspace_layout;
    private JButton search_select;
    private BookSearchPanel search;
    private JButton my_books_select;
    private MyBooksPanel my_books;
    private JButton my_penalties_select;
    private MyPenalties my_penalties;
    private JButton lend_book_select;
    private JButton collect_fines_select;
    private JButton manage_members_select;
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

        // init MyPenalties
        my_penalties = new MyPenalties();

        // Set Search as starting view
        selectBookSearch();
    }

    public void login(int user_id) {
        setUser(user_id);
        setFunctionalities();
        my_books.load_data(user_id);
        selectBookSearch();
    }

    public void init(ActionListener on_logout) {
        initLogout(on_logout);
        initBookSearch();
        initMyBooks();
        initMyPenalties();
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
        workspace_panel.add(my_books, "myBooks");
    }

    private void initMyPenalties() {
        ActionListener my_penalties_select_listener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                selectMyPenalties();
            }
        };
        my_penalties_select.addActionListener(my_penalties_select_listener);
        workspace_panel.add(my_penalties, "myPenalties");
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


    private void setUser(int user_id) {
        Database db = new Database();
        user = db.fetchUser(user_id);
        username_field.setText(user.getUsername());
    }

    private void setFunctionalities() {
        if (user.getRole().equals("admin")) {
            lend_book_select.setVisible(true);
            collect_fines_select.setVisible(true);
            manage_members_select.setVisible(true);
        } else {
            lend_book_select.setVisible(false);
            collect_fines_select.setVisible(false);
            manage_members_select.setVisible(false);
        }
    }

    private void enable_buttons() {
        search_select.setEnabled(true);
        my_books_select.setEnabled(true);
        my_penalties_select.setEnabled(true);
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

    private void selectMyPenalties() {
        enable_buttons();
        my_penalties_select.setEnabled(false);
        workspace_layout.show(workspace_panel, "myPenalties");
    }
}
