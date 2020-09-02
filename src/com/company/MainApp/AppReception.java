package com.company.MainApp;

import com.company.ConnectionDatabase;
import com.company.ReservationForm;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class AppReception extends App {
    private JButton makeReservationButton = new JButton("Make reservation");
    private ConnectionDatabase connectionDatabase = new ConnectionDatabase();

    public AppReception() {
        super("Reception");
        setLoginId(2);
        panel.add(makeReservationButton);

        makeReservationButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                connectionDatabase.updateDatabase(room, room.length);
                ReservationForm reservationForm = new ReservationForm();
            }
        });
    }
}
