package main.java.com.example.app.ui;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainPage extends JFrame {
    private JPanel panelMain;
    private JLabel page;
    private JButton clickMeButton;
    private JTable dataTable;

    public MainPage () {
        setContentPane(panelMain);
        setTitle("Simple GUI App");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(960, 600);


        setVisible(true);
        clickMeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(MainPage.this, "Hello World");
            }
        });
    }

    public static void main(String[] args) {
        new MainPage();
    }
}
