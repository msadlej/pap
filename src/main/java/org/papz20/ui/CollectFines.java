package main.java.org.papz20.ui;

import main.java.org.papz20.model.Database;
import main.java.org.papz20.model.Fine;
import main.java.org.papz20.services.FineService;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class CollectFines extends JPanel {
    private JPanel main_panel;
    private JTextField user_id_field;
    private JButton get_fines;
    private JTable fine_table;
    private DefaultTableModel fine_model;
    private List<Fine> fines;
    private JButton collect_fine;
    private JLabel fine_total;

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
                int user_id = Integer.parseInt(user_id_field.getText());
                fines = new Database().viewFines(user_id);
                int total = 0;
                for (Fine fine : fines) {
                    int gr = fine.getAmount() % 100;
                    int zl = fine.getAmount() / 100;
                    fine_model.addRow(
                            new String[]{
                                    String.valueOf(fine.getId()),
                                    String.format("%d.%02d PLN", zl, gr),
                                    fine.getStatus()
                            }
                    );
                    if (fine.getStatus().equals("unpaid")) {
                        total += fine.getAmount();
                    }
                }
                int total_gr = total % 100;
                int total_zl = total / 100;
                fine_total.setText(String.format("%d.%02d PLN", total_zl, total_gr));
            }
        };
        get_fines.addActionListener(get_fines_listener);

        ActionListener collect_fine_listener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                FineService service = new FineService();
                int i = 0;
                for (Fine fine : fines) {
                    service.setPaid(fine);
                    fine_model.setValueAt("paid", i++, 2);
                    fine_total.setText(String.format("0.00 PLN"));
                }
            }
        };
        collect_fine.addActionListener(collect_fine_listener);
    }
}
