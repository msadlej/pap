package main.java.org.papz20.ui;

import javax.swing.*;
import java.awt.*;

public class MainFrame extends JFrame {
    private JPanel main_panel_card;

    public MainFrame() {
        main_panel_card = new JPanel(new CardLayout());
        setContentPane(main_panel_card);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            JFrame window = new MainFrame();
            window.setTitle("Library");
            window.setDefaultCloseOperation(EXIT_ON_CLOSE);
            window.setSize(600, 600);
            window.setVisible(true);
            window.setLocationRelativeTo(null);

        });
    }


}
