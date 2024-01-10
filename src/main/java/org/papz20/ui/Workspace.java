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
    private LendBooks lend_books;
    private JButton collect_fines_select;
    private CollectFines collect_fines;
    private JButton manage_members_select;
    private ManageMembers manage_members;
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

        // init LendBooks
        lend_books = new LendBooks();

        // init CollectFines
        collect_fines = new CollectFines();

        // init ManageMember
        manage_members = new ManageMembers();

        // Set Search as starting view
        selectBookSearch();
    }

    public void login(int user_id) {
        setUser(user_id);
        setFunctionalities();
        my_books.load_data(user_id);
        my_penalties.load_penalties(user_id);
        selectBookSearch();
    }

    public void init(ActionListener on_logout) {
        initLogout(on_logout);
        initBookSearch();
        initMyBooks();
        initMyPenalties();
        initLendBooks();
        initCollectFines();
        initManageMembers();
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

    private void initLendBooks() {
        ActionListener lend_books_select_listener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                selectLendBooks();
            }
        };
        lend_book_select.addActionListener(lend_books_select_listener);
        workspace_panel.add(lend_books, "lendBooks");
    }

    private void initCollectFines() {
        ActionListener collect_fines_select_listener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                selectCollectFines();
            }
        };
        collect_fines_select.addActionListener(collect_fines_select_listener);
        workspace_panel.add(collect_fines, "collectFines");
    }

    private void initManageMembers() {
        ActionListener manage_members_listener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                selectManageMembers();
            }
        };
        manage_members_select.addActionListener(manage_members_listener);
        workspace_panel.add(manage_members, "manageMembers");
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
        lend_book_select.setEnabled(true);
        collect_fines_select.setEnabled(true);
        manage_members_select.setEnabled(true);
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

    private void selectLendBooks() {
        enable_buttons();
        lend_book_select.setEnabled(false);
        workspace_layout.show(workspace_panel, "lendBooks");
    }

    private void selectCollectFines() {
        enable_buttons();
        collect_fines_select.setEnabled(false);
        workspace_layout.show(workspace_panel, "collectFines");
    }

    private void selectManageMembers() {
        enable_buttons();
        manage_members_select.setEnabled(false);
        workspace_layout.show(workspace_panel, "manageMembers");
    }
}
