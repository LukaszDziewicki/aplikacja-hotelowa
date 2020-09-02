package com.company;

import javax.swing.*;
import java.awt.*;
import java.sql.*;

public class ConnectionDatabase {
    private final String URL = "jdbc:mysql://localhost:3306/rooms?useSSL=false";
    private final String USER = "root";
    private final String PASSWORD = "admin";


    public boolean isConnection() {
        try {
            Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
            System.out.println("Connected");
            return true;
        } catch (SQLException ex) {
            ex.printStackTrace();
            return false;
        }
    }

    public void updateDatabase(Room[] room, int roomQuantity) {
        try {
            Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
            int counter = 1;
            for (int i = 0; i <= roomQuantity; i++) {

                PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM room Where roomNumber = " + counter++);
                ResultSet resultSet = preparedStatement.executeQuery();
                while (resultSet.next()) {
                    int roomNumber = resultSet.getInt("roomNumber");
                    int peoplePerRoom = resultSet.getInt("peoplePerRoom");
                    String arrivalDate = resultSet.getString("arrivalDate");
                    int howManyDays = resultSet.getInt("howManyDays");
                    String name = resultSet.getString("name");
                    String surname = resultSet.getString("surname");
                    int isClean = resultSet.getInt("isClean");
                    int isRent = resultSet.getInt("isRent");

                    room[i].setRoomNumber(roomNumber);
                    room[i].setPeoplePerRoom(peoplePerRoom);
                    Room.setHowManyDays(howManyDays);
                    Room.setArrivalDate(arrivalDate);
                    room[i].setName(name);
                    room[i].setSurname(surname);
                    Room.setIsClean(isClean);
                    Room.setIsRent(isRent);

                    if (isRent == 0)
                        room[i].button.setForeground(Color.GREEN);
                    else if (isRent == 1)
                        room[i].button.setForeground(Color.RED);
                    else
                        System.out.println("EXCEPTION isClean() or isRent() from downloading Room Database");

                    System.out.println(room[i].toString());
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public JComboBox getComboBoxFreeRooms(int peoplePerRoom) {
        JComboBox freeRoomsComboBox = new JComboBox();
        try {
            Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT roomNumber FROM room WHERE peoplePerRoom = ? AND isRent = ?");
            preparedStatement.setInt(1, peoplePerRoom);
            preparedStatement.setInt(2, 0);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                int roomNumber = resultSet.getInt("roomNumber");
                freeRoomsComboBox.addItem(roomNumber);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return freeRoomsComboBox;
    }

    public boolean makeReservation(Room room) {
        try {
            Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);


            CipherHelper cipherHelper = new CipherHelper();
            String secretKey = "01234567";

            PreparedStatement prepStmt = connection.prepareStatement(
                    "UPDATE room SET arrivalDate = ?, howManyDays = ?, name = ?, surname = ?, isClean = ?, isRent = ? WHERE roomNumber = ? ");
            prepStmt.setString(1, (Room.getArrivalDate()));
            prepStmt.setInt(2, Room.getHowManyDays());
            prepStmt.setString(3, CipherHelper.cipher(secretKey, room.getName()));
            prepStmt.setString(4, CipherHelper.cipher(secretKey, room.getSurname()));
            prepStmt.setInt(5, Room.getIsClean());
            prepStmt.setInt(6, Room.getIsRent());
            prepStmt.setInt(7, room.getRoomNumber());
            prepStmt.execute();
        } catch (SQLException e) {
            System.err.println("Blad przy wstawianiu pokoju");
            e.printStackTrace();
            return false;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return true;
    }

    public boolean deleteReservation(int roomNumber) {
        try {
            Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
            PreparedStatement prepStmt = connection.prepareStatement(
                    "UPDATE room SET arrivalDate = ?, howManyDays = ?, isClean = ?, isRent = ?, name = ?, surname = ? WHERE roomNumber = ? ");
            prepStmt.setNull(1, Types.VARCHAR);
            prepStmt.setNull(2, Types.INTEGER);
            prepStmt.setString(5, "");
            prepStmt.setString(6, "");
            prepStmt.setInt(3, 1);
            prepStmt.setInt(4, 0);
            prepStmt.setInt(7, roomNumber);
            prepStmt.execute();
        } catch (SQLException e) {
            System.err.println("Blad przy usuwaniu rezerwacji");
            e.printStackTrace();
            return false;
        }
        return true;
    }

    public boolean changeIsRoomClean(int isCleanValue, int roomNumber) {
        try {
            Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
            PreparedStatement prepStmt = connection.prepareStatement(
                    "UPDATE room SET isClean = ? WHERE roomNumber = ? ");
            prepStmt.setInt(1, isCleanValue);
            prepStmt.setInt(2, roomNumber);
            prepStmt.execute();
        } catch (SQLException e) {
            System.err.println("Blad przy sprzątaniu pokoju");
            e.printStackTrace();
            return false;
        }
        return true;
    }

}




