package com.company.MainApp;


import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AppAdmin extends App {
    private JMenu loginTo = new JMenu("Login to");
    private JMenuItem receptionItem = new JMenuItem("Reception");
    private JMenuItem hotelKeeperItem = new JMenuItem("Hotel Keeper");

    public AppAdmin() {
        super("Admin");
        setLoginId(1);
        setMenuBar();
        addMenuBarListeners();
    }

    private void setMenuBar() {
        operationsMenu.add(loginTo);
        loginTo.add(receptionItem);
        loginTo.add(hotelKeeperItem);
    }

    private void addMenuBarListeners() {
        receptionItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(
                        AppAdmin.this,
                        "You have been logged to Reception");
                AppReception appReception = new AppReception();
                appReception.setVisible(true);
                appReception.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
            }
        });

        hotelKeeperItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(
                        AppAdmin.this,
                        "You have been logged to Hotel Keeper");
                AppHotelKeeper appHotelKeeper = new AppHotelKeeper();
                appHotelKeeper.setVisible(true);
                appHotelKeeper.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
            }
        });

    }


}
