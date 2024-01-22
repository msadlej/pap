package main.java.org.papz20;


import main.java.org.papz20.model.Database;
import main.java.org.papz20.ui.MainFrame;

import javax.swing.*;
import static javax.swing.WindowConstants.EXIT_ON_CLOSE;

public class App {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            MainFrame window = new MainFrame();
            window.init();
            window.setTitle("Library");
            window.setDefaultCloseOperation(EXIT_ON_CLOSE);
            window.setSize(800, 600);
            window.setVisible(true);
            window.setLocationRelativeTo(null);
        });
    }
}