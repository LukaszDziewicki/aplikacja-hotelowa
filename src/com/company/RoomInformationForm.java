package com.company;

import com.company.Const.Price;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import static com.company.Room.getIsRent;


public class RoomInformationForm extends JFrame {
    private JLabel roomNumberInfoLabel, peoplePerRoomInfoLabel, whoRentNameInfoLabel, roomPriceInfoLabel;
    private JButton closeWindowButton;
    private JCheckBox changeIsRoomClean;
    private JButton deleteReservationButton;
    private JButton saveButton;

    public RoomInformationForm(Room room, int loginId) {
        setTitle("Room nr. " + room.getRoomNumber());
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(300, 300, 210, 270);
        setResizable(false);
        setLocationRelativeTo(null);
        setLayout(new FlowLayout(FlowLayout.CENTER));

        roomNumberInfoLabel = new JLabel("Information Room nr. " + room.getRoomNumber() + "  ");
        peoplePerRoomInfoLabel = new JLabel(howManyPeopleRoom(room.getPeoplePerRoom()));
        roomPriceInfoLabel = new JLabel("Price Per night : " + (checkPrice(room.getPeoplePerRoom())));


        roomNumberInfoLabel.setBounds(0, 0, 200, 275);
        peoplePerRoomInfoLabel.setBounds(0, 0, 200, 275);
        roomPriceInfoLabel.setBounds(0, 0, 200, 275);
        deleteReservationButton = new JButton("Delete reservation");
        saveButton = new JButton(("Save"));
        closeWindowButton = new JButton("Close");
        changeIsRoomClean = new JCheckBox("Is Room " + room.getRoomNumber() + " clean ? ");

        getContentPane().add(roomNumberInfoLabel);
        getContentPane().add(peoplePerRoomInfoLabel);


        if (loginId == 2 || loginId == 1 && getIsRent() == 1) {
            try {
                CipherHelper cipherHelper = new CipherHelper();

                whoRentNameInfoLabel = new JLabel("RENTING : "
                        + CipherHelper.decipher(CipherHelper.secretKey, room.getName())
                        + " " + CipherHelper.decipher(CipherHelper.secretKey, room.getSurname()));
            } catch (Exception e) {
                e.printStackTrace();
            }

            whoRentNameInfoLabel.setBounds(0, 0, 200, 275);
            getContentPane().add(whoRentNameInfoLabel);
        }

        getContentPane().add(roomPriceInfoLabel);

        getContentPane().add(changeIsRoomClean);
        if (isAdminOrReception(loginId)) {
            getContentPane().add(deleteReservationButton);
        }
        getContentPane().add(saveButton);
        getContentPane().add(closeWindowButton);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        closeWindowButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });

        deleteReservationButton.addActionListener(new ActionListener() {
            ConnectionDatabase connectionDatabase = new ConnectionDatabase();

            @Override
            public void actionPerformed(ActionEvent e) {
                if (connectionDatabase.deleteReservation(room.getRoomNumber())) {
                    JOptionPane.showMessageDialog(
                            RoomInformationForm.this,
                            "Possible Delete" +
                                    "Reservation");
                    dispose();
                } else {
                    JOptionPane.showMessageDialog(
                            RoomInformationForm.this,
                            "Delete Reservation Error");
                    dispose();

                }
            }
        });

        saveButton.addActionListener(new ActionListener() {
            ConnectionDatabase connectionDatabase = new ConnectionDatabase();

            @Override
            public void actionPerformed(ActionEvent e) {
                if (changeIsRoomClean.isSelected() == true) {
                    connectionDatabase.changeIsRoomClean(1, room.getRoomNumber());
                    JOptionPane.showMessageDialog(
                            RoomInformationForm.this,
                            "Possible Save - Room is clean");
                } else if (changeIsRoomClean.isSelected() == false) {
                    connectionDatabase.changeIsRoomClean(0, room.getRoomNumber());
                    JOptionPane.showMessageDialog(
                            RoomInformationForm.this,
                            "Possible Save - Room need cleaning");
                }

                dispose();
            }
        });

    }

    private boolean isAdminOrReception(int loginId) {
        return loginId == 1 || loginId == 2;
    }

    public String howManyPeopleRoom(int peoplePerRoom) {
        if (peoplePerRoom == 2) {
            return "Two - person room      ";
        } else if (peoplePerRoom == 4) {
            return "Four - person room     ";
        } else if (peoplePerRoom == 6) {
            return "Six - person room      ";
        }
        return null;
    }

    public String checkRoomStatus(int isRent) {
        if (isRent == 0) {
            return "FREE           ";
        } else if (isRent == 1) {
            return "RENT           ";
        }
        return null;
    }

    public int checkPrice(int peoplePerRoom) {
        if (peoplePerRoom == 2) {
            return Price.PRICE_PER_DOUBLE_ROOM;
        } else if (peoplePerRoom == 4) {
            return Price.PRICE_PER_FOUR_ROOM;
        } else if (peoplePerRoom == 6) {
            return Price.PRICE_PER_SIX_ROOM;
        }
        return 0;
    }

    public String isClean(int isClean) {
        if (isClean == 1)
            return "Yes";
        else if (isClean == 0)
            return "No";
        return null;
    }

}
