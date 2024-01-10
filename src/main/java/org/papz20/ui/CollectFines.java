package main.java.org.papz20.ui;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CollectFines extends JPanel {
    private JPanel main_panel;
    private JTextField user_id_field;
    private JButton get_fines;
    private JTable fine_table;
    private DefaultTableModel fine_model;
    private JButton collect_fine;

    public CollectFines() {
        setLayout(new BorderLayout());
        add(main_panel, BorderLayout.CENTER);

        fine_model = new DefaultTableModel();
        fine_model.setColumnIdentifiers(new String[]{"fine_id", "amount", "status"});
        fine_table.setModel(fine_model);
        init();
    }

    private void init() {
        ActionListener get_fines_listener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // TODO: get user id, request fines, add to model
            }
        };
        get_fines.addActionListener(get_fines_listener);

        ActionListener collect_fine_listener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // TODO: set fines as paid, clear model
            }
        };
        collect_fine.addActionListener(collect_fine_listener);
    }
}
