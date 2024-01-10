package main.java.org.papz20.ui;

import main.java.org.papz20.model.Database;
import main.java.org.papz20.model.User;
import main.java.org.papz20.services.ChangeUserInfoService;

import javax.swing.*;
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
    private JButton change_password;
    private JTextField new_password_field;
    private JLabel new_password_prompt;
    private JLabel deletion_prompt;
    private User selected_user;

    public ManageMembers() {
        setLayout(new BorderLayout());
        add(main_panel, BorderLayout.CENTER);

        ActionListener select_listener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                clear();
                // Load data into card and set result as selected user
                int user_id = Integer.parseInt(user_id_field.getText());
                selected_user = new Database().fetchUser(user_id); // TODO: check if valid result
                if (selected_user == null) {
                    clear();
                    return;
                }
                showComponents();
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
                // TODO: check for unpaid fines and not returned books
                if (selected_user.getRole().equals("admin")) {
                    deletion_prompt.setText("Cannot delete admins account!");
                    return;
                }
                new Database().removeUser(selected_user.getId());
                clear();
                deletion_prompt.setText("User deleted successfully!");
                selected_user = null;
            }
        };
        delete_account.addActionListener(delete_listener);

        ActionListener new_password_listener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (new_password_field.getText().isBlank()) {
                    new_password_prompt.setText("New password cannot be empty");
                    return;
                }
                new ChangeUserInfoService().ChangeUserPassword(selected_user.getUsername(), new_password_field.getText());
                new_password_prompt.setText("Changed password successfully");
            }
        };
        change_password.addActionListener(new_password_listener);

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
        change_password.setVisible(false);
        new_password_field.setVisible(false);
        new_password_prompt.setVisible(false);
        new_password_prompt.setText("New password");
        deletion_prompt.setText("");
    }

    public void showComponents() {
        user_card.setVisible(true);
        delete_account.setVisible(true);
        change_password.setVisible(true);
        new_password_field.setVisible(true);
        new_password_prompt.setVisible(true);
    }
}
