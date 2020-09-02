package com.company;


import javax.swing.*;
import javax.swing.text.BadLocationException;
import javax.swing.text.Document;

public class StatisticsForm extends JFrame {
    private JTextPane textPane;
    private JButton closeButton;
    private JPanel statisticsPanel;

    public StatisticsForm(int loginId, Room[] room) {
        super("Statistics");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setContentPane(statisticsPanel);
        setLocationRelativeTo(null);
        setBounds(500, 350, 850, 850);
        pack();
        setVisible(true);

        if (loginId == 1) {

            textPane.setText("Double-bed Rooms : " + "\n");
            for (int i = 0; i < 10; i++) {
                append(room[i].toString());
            }
            append("Three-bed Rooms : " + "\n");
            for (int i = 10; i < 20; i++) {
                append(room[i].toString());
            }
            append("Six-bed Rooms : " + "\n");
            for (int i = 20; i < 30; i++) {
                append(room[i].toString());
            }

            textPane.setVisible(true);
        } else if (loginId == 2) {

        } else if (loginId == 3) {

        }

    }

    public void append(String s) {
        try {
            Document document = textPane.getDocument();
            document.insertString(document.getLength(), s, null);
        } catch (BadLocationException exc) {
            exc.printStackTrace();
        }
    }

}
