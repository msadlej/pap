package main.java.org.papz20.ui;

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
        penalties_model.setRowCount(0); // delete existing data
        Database db = Database.getInstance();
        db.connectDB();
        List<Fine> user_fines = db.viewFines(user_id);
        int total = 0;
        for (Fine fine : user_fines) {
            if (fine.getStatus().equals("unpaid")) {
                total += fine.getAmount();
                int gr = fine.getAmount() % 100;
                int zl = fine.getAmount() / 100;
                penalties_model.addRow(new String[]{String.format("%d.%02d PLN", zl, gr), fine.getStatus()}); // add new data
            }
        }
        int total_gr = total % 100;
        int total_zl = total / 100;
        penalties_total.setText(String.format("%d.%02d PLN", total_zl, total_gr));
    }
}
