package main.java.org.papz20.ui;

import main.java.org.papz20.model.Database;
import main.java.org.papz20.model.User;

import javax.swing.*;
import javax.xml.crypto.Data;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ManageMembers extends JPanel{
    private JPanel main_panel;
    private JTextField user_id_field;
    private JButton selectButton;
    private JButton delete_account;
    private JPanel user_card;
    private JLabel name_surname;
    private JLabel username;
    private JLabel email;
    private JLabel role;
    private User selected_user;

    public ManageMembers() {
        setLayout(new BorderLayout());
        add(main_panel, BorderLayout.CENTER);

        ActionListener select_listener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                clear();
                // Load data into card and set result as selected user
                // TODO: check if valid user id ie.
                //  valid input,
                //  database returns valid user.
                //  if not return early
                delete_account.setVisible(true);
                int user_id = Integer.parseInt(user_id_field.getText());
                selected_user = new Database().fetchUser(user_id); // TODO: check if valid result
                user_card.setVisible(true);
                name_surname.setText(String.format("name surname: %s %s", selected_user.getFirstName(), selected_user.getLastName()));
                username.setText(String.format("username: %s", selected_user.getUsername()));
                email.setText(String.format("email: %s", selected_user.getEmail()));
                role.setText(String.format("role: %s", selected_user.getRole()));
            }
        };
        selectButton.addActionListener(select_listener);

        ActionListener delete_listener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // TODO: check if valid user id ie.
                //  not null,
                //  user doesnt have unpaid penalties
                //  or not returned books and user cannot delete self.
                //new Database().removeUser(selected_user.getId());
                clear();
            }
        };
        delete_account.addActionListener(delete_listener);

        clear();
    }

    public void clear() {
        selected_user = null;
        user_card.setVisible(false);
        name_surname.setText("name surname:");
        username.setText("username:");
        email.setText("email:");
        role.setText("role:");
        delete_account.setVisible(false);
    }

    // TODO: add change password text field and button

}
