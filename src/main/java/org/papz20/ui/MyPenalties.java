package main.java.org.papz20.ui;

import main.java.org.papz20.model.Book;
import main.java.org.papz20.model.Database;
import main.java.org.papz20.model.Fine;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

public class MyPenalties extends JPanel {
    private JPanel main_panel;
    private JTable penalties_table;
    private JLabel penalties_total;
    private DefaultTableModel penalties_model;

    public MyPenalties() {
        setLayout(new BorderLayout());
        add(main_panel, BorderLayout.CENTER);

        penalties_model = new DefaultTableModel();
        penalties_model.setColumnIdentifiers(new String[]{"amount", "status"});
        penalties_table.setModel(penalties_model);
    }

    public void load_penalties(int user_id) {
        penalties_model.setRowCount(0);
        Database db = new Database();
        List<Fine> fines = db.getAllFines();
        int total = 0;
        for (Fine fine : fines) {
            if (fine.getStatus().equals("unpaid")) {
                total += fine.getAmount();
                penalties_model.addRow(new String[]{String.valueOf(fine.getAmount()), fine.getStatus()});
            }
        }
        penalties_total.setText(String.valueOf(total));
    }
}
