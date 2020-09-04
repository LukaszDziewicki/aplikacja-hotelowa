package com.company.MainApp;

import com.company.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import static java.awt.BorderLayout.PAGE_START;

public class App extends JFrame {
    private static boolean clicked_Night = false;
    protected JMenu operationsMenu = new JMenu("Operations");
    protected JMenuItem logoutItem = new JMenuItem("Logout");
    protected JMenuBar bar = new JMenuBar();
    protected JButton nightButton = new JButton("Light/Dark");
    protected JButton databaseUpdateButton = new JButton("Update");
    protected JPanel panel = new JPanel();
    protected JPanel roomsPanel = new JPanel();
    protected Room[] room = new Room[30];
    protected int loginId = 0;


    public App(String title) {
        super(title);
        setBounds(300, 300, 800, 480);

        setBar();
        setRoomPanel();
        databaseUpdateButton.setForeground(Color.RED);
        nightButton.setBackground(Color.WHITE);
        databaseUpdateButton.setBackground(Color.WHITE);

        setResizable(false);
        setLocationRelativeTo(null);
        setLayout(new FlowLayout());

        addListeners();

        getContentPane().add(panel, PAGE_START);
        getContentPane().add(roomsPanel, new GridLayout(3, 6));

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }

    private void setBar() {
        setMenuBar();
        bar.add(operationsMenu);
        bar.add(nightButton);
        bar.add(databaseUpdateButton);
        setJMenuBar(bar);
    }

    private void setMenuBar() {
        operationsMenu.add(logoutItem);
    }

    private void setRoomPanel() {
        setRooms();
        roomsPanel.setLayout(new GridLayout(3, 10, 10, 10));
        roomsPanel.setVisible(true);
    }

    private void setRooms() {
        int ilo_osobowy = 2;
        int nr_pokoju = 1;

        for (int i = 0; i < room.length; i++, nr_pokoju++) {
            room[i] = new Room(nr_pokoju, ilo_osobowy);

            room[i].setButton(new JButton("" + nr_pokoju));
            room[i].getButton().setPreferredSize(new Dimension(55, 110));
            room[i].getButton().setForeground(Color.GREEN);
            room[i].getButton().setBackground(new Color(153, 102, 51));
            room[i].getButton().setFont(new Font("Luminari", Font.PLAIN, 18));

            roomsPanel.add(room[i].getButton());
            if (i == 10 || i == 20) {
                ilo_osobowy++;
            }

        }
    }

    private void addNightButtonLister() {
        nightButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (clicked_Night == false) {
                    getContentPane().setBackground(Color.gray);
                    roomsPanel.setBackground(Color.GRAY);
                    panel.setBackground(Color.GRAY);
                    operationsMenu.setBackground(Color.GRAY);
                    bar.setBackground(Color.GRAY);
                    nightButton.setBackground(Color.GRAY);
                    databaseUpdateButton.setBackground(Color.GRAY);

                    clicked_Night = true;

                } else if (clicked_Night == true) {
                    getContentPane().setBackground(Color.WHITE);
                    roomsPanel.setBackground(Color.WHITE);
                    panel.setBackground(Color.WHITE);
                    operationsMenu.setBackground(Color.WHITE);
                    bar.setBackground(Color.WHITE);
                    nightButton.setBackground(Color.WHITE);
                    databaseUpdateButton.setBackground(Color.WHITE);

                    clicked_Night = false;
                }
            }
        });
    }

    private void addDatabaseUpdateButtonListener() {
        databaseUpdateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ConnectionDatabase connectionDatabase = new ConnectionDatabase();
                if (connectionDatabase.isConnection()) {
                    connectionDatabase.updateDatabase(room, room.length);
                }
            }
        });
    }

    private void addLogoutItemListener() {
        logoutItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                Logowanie logowanie = new Logowanie();
                dispose();
            }
        });
    }

    private void addRoomsButtonListener() {
        ConnectionDatabase connectionDatabase = new ConnectionDatabase();
        for (int i = 0; i < room.length; i++) {
            final int roomNumber = i;
            room[i].getButton().addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    connectionDatabase.updateDatabase(room, 30);
                    RoomInformationForm roomInformation = new RoomInformationForm(room[roomNumber], getLoginId());
                    roomInformation.setVisible(true);

                }
            });

        }
    }

    private void addListeners() {
        addRoomsButtonListener();
        addNightButtonLister();
        addLogoutItemListener();
        addDatabaseUpdateButtonListener();

    }

    private int getLoginId() {
        return loginId;
    }

    protected void setLoginId(int loginId) {
        this.loginId = loginId;
    }
}



