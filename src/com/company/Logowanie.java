package com.company;


import com.company.Const.Account;
import com.company.MainApp.App;
import com.company.MainApp.AppAdmin;
import com.company.MainApp.AppHotelKeeper;
import com.company.MainApp.AppReception;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Logowanie extends JFrame {

    private JPanel textPanel = new JPanel();
    private JPanel buttonPanel = new JPanel();
    private JLabel userIdLabel;
    private JTextField userIdField;
    private JLabel passwordLabel;
    private JPasswordField passwordField;
    private JButton loginButton;
    private JButton closeButton;

    public Logowanie() {
        super("Logowanie");
        setLoginPanel();
        addCloseButtonListener();
        addLoginButtonListener();
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        getContentPane().add(textPanel, BorderLayout.CENTER);
        getContentPane().add(buttonPanel, BorderLayout.PAGE_END);
        pack();
        setLocationRelativeTo(null);
        setResizable(false);
        setVisible(true);
    }

    private void setLoginPanel() {
        userIdLabel = new JLabel("ID:");
        textPanel.add(userIdLabel);

        userIdField = new JTextField(5);
        textPanel.add(userIdField);

        passwordLabel = new JLabel("Password:");
        textPanel.add(passwordLabel);

        passwordField = new JPasswordField(15);
        textPanel.add(passwordField);


        loginButton = new JButton("Login");
        buttonPanel.add(loginButton);

        closeButton = new JButton("Close");
        buttonPanel.add(closeButton);
    }

    private void addCloseButtonListener() {
        closeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                System.exit(0);
            }
        });
    }

    private void addLoginButtonListener() {
        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                login();

            }
        });
    }

    private void login() {
        if (Account.whoLogged(Integer.parseInt(getUserId()), getPassword()) == 1) {
            JOptionPane.showMessageDialog(
                    Logowanie.this,
                    "You have been logged as Admin");
            dispose();
            App appAdmin = new AppAdmin();
            appAdmin.setVisible(true);

        } else if (Account.whoLogged(Integer.parseInt(getUserId()), getPassword()) == 2) {
            JOptionPane.showMessageDialog(
                    Logowanie.this,
                    "You have been logged to Reception");
            dispose();
            App appReception = new AppReception();
            appReception.setVisible(true);

        } else if (Account.whoLogged(Integer.parseInt(getUserId()), getPassword()) == 3) {
            JOptionPane.showMessageDialog(
                    Logowanie.this,
                    "You have been logged as hotel keeper");
            dispose();
            App appHotelKeeper = new AppHotelKeeper();
            appHotelKeeper.setVisible(true);
        } else
            JOptionPane.showMessageDialog(
                    Logowanie.this,
                    "You've insert wrong ID or Password"
            );
    }

    private String getUserId() {
        return userIdField.getText().trim();
    }

    private String getPassword() {
        return new String(passwordField.getPassword());
    }

}

