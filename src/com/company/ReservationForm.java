package com.company;

import com.company.Const.DateHelper;
import com.company.Const.Price;
import com.company.MainApp.SelectDate;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ReservationForm extends JFrame {
    private JButton calculateButton;
    private JButton confirmButton;
    private JTextField howManyDaysField;
    private JTextField nameField;
    private JComboBox howManyPeopleBox;
    private JLabel howManyPeopleLabel;
    private JLabel arrivalDateLabel;
    private JLabel howManyDaysLabel;
    private JLabel nameLabel;
    private JLabel surnameLabel;
    private JPanel makeReservationPanel;
    private JTextField arrivalDateField;
    private JTextPane priceTextPane;
    private JTextField surnameField;


    public  JButton dateButton;
    private JButton makeReservationButton = new JButton("Make Reservation");
    private JLabel availableRoomsLabel = new JLabel("Available rooms :");
    private JFrame freeRoomsFrame = new JFrame("Available Rooms");
    private JPanel freeRoomsPanel = new JPanel();
    private String date;


    public ReservationForm() {
        super("Making Reservation");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setContentPane(makeReservationPanel);
        setLocationRelativeTo(null);
        setBounds(500, 250, 500, 500);
        pack();
        setVisible(true);


        calculateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (howManyPeopleBox.getSelectedItem() == "2") {
                    System.out.println("2");
                    int price = Price.PRICE_PER_DOUBLE_ROOM * (Integer.parseInt(howManyDaysField.getText()));
                    priceTextPane.setText(Integer.toString(price));
                } else if (howManyPeopleBox.getSelectedItem() == "4") {
                    System.out.println("4");
                    int price = Price.PRICE_PER_FOUR_ROOM * (Integer.parseInt(howManyDaysField.getText()));
                    priceTextPane.setText(Integer.toString(price));
                } else {
                    System.out.println("6");
                    int price = Price.PRICE_PER_SIX_ROOM * (Integer.parseInt(howManyDaysField.getText()));
                    priceTextPane.setText(Integer.toString(price));
                }

            }
        });

        confirmButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ConnectionDatabase connectionDatabase = new ConnectionDatabase();
                Integer howManyPeopleBoxSelectedItem = Integer.parseInt(howManyPeopleBox.getSelectedItem().toString());
                Room reservationRoom = createReservationRoom();
                reservationRoom.setPeoplePerRoom(howManyPeopleBoxSelectedItem);

                switch (howManyPeopleBoxSelectedItem) {
                    case 2:
                        displayFreeRoomsFrame(connectionDatabase.getComboBoxFreeRooms(2), reservationRoom);
                        dispose();
                        break;
                    case 4:
                        displayFreeRoomsFrame(connectionDatabase.getComboBoxFreeRooms(4), reservationRoom);
                        dispose();
                        break;
                    case 6:
                        displayFreeRoomsFrame(connectionDatabase.getComboBoxFreeRooms(6), reservationRoom);
                        dispose();
                        break;
                }
            }
        });

        dateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
              SelectDate selectDate =  new SelectDate();

            }
        });
    }



    public Room createReservationRoom() {
        Room reservationRoom = new Room();

        Room.setArrivalDate(DateHelper.date);
        Room.setHowManyDays(Integer.parseInt(howManyDaysField.getText().trim()));
        reservationRoom.setName(nameField.getText().trim());
        reservationRoom.setSurname(surnameField.getText().trim());
        Room.setIsClean(0);
        Room.setIsRent(1);
        return reservationRoom;
    }

    public void displayFreeRoomsFrame(JComboBox freeRoomsComboBox, Room reservationRoom) {
        freeRoomsFrame.setLocationRelativeTo(null);
        freeRoomsFrame.setBounds(500, 250, 500, 500);
        freeRoomsPanel.add(availableRoomsLabel);
        freeRoomsPanel.add(freeRoomsComboBox);
        freeRoomsPanel.add(makeReservationButton);
        freeRoomsFrame.getContentPane().add(freeRoomsPanel);
        freeRoomsFrame.pack();
        freeRoomsFrame.setVisible(true);
        addMakeReservationButtonListener(freeRoomsComboBox, reservationRoom);
    }

    public void addMakeReservationButtonListener(JComboBox freeRoomsComboBox, Room reservationRoom) {
        makeReservationButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ConnectionDatabase connectionDatabase = new ConnectionDatabase();
                reservationRoom.setRoomNumber(Integer.parseInt(freeRoomsComboBox.getSelectedItem().toString()));

                System.out.println(reservationRoom.getRoomNumber());

                if (connectionDatabase.makeReservation(reservationRoom)) {

                    JOptionPane.showMessageDialog(
                            ReservationForm.this,
                            "Possible Reservation");
                    dispose();
                    freeRoomsFrame.dispose();

                } else {
                    JOptionPane.showMessageDialog(
                            ReservationForm.this,
                            "Reservation ERROR");
                }

            }
        });
    }

    public JButton getConfirmButton() {
        return confirmButton;
    }

    public JComboBox getHowManyPeopleBox() {
        return howManyPeopleBox;
    }

    public JPanel getMakeReservationPanel() {
        return makeReservationPanel;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}