package com.company;

import javax.swing.*;

public class Room {
    public static String arrivalDate;
    public static int howManyDays;
    public static int isClean = 1;
    public static int isRent = 0;
    public JButton button;
    public int roomNumber;
    public int peoplePerRoom;
    public String name;
    public String surname;

    public Room() {
    }

    public Room(int roomNumber, int peoplePerRoom) {
        this.roomNumber = roomNumber;
        this.peoplePerRoom = peoplePerRoom;
    }

    public static String getArrivalDate() {
        return arrivalDate;
    }

    public static void setArrivalDate(String arrivalDate) {
        Room.arrivalDate = arrivalDate;
    }

    public static int getHowManyDays() {
        return howManyDays;
    }

    public static void setHowManyDays(int howManyDays) {
        Room.howManyDays = howManyDays;
    }

    public static int getIsClean() {
        return isClean;
    }

    public static void setIsClean(int isClean) {
        Room.isClean = isClean;
    }

    public static int getIsRent() {
        return Room.isRent;
    }

    public static void setIsRent(int isRent) {
        Room.isRent = isRent;
    }

    public JButton getButton() {
        return button;
    }

    public void setButton(JButton button) {
        this.button = button;
    }

    public int getRoomNumber() {
        return roomNumber;
    }

    public void setRoomNumber(int roomNumber) {
        this.roomNumber = roomNumber;
    }

    public int getPeoplePerRoom() {
        return peoplePerRoom;
    }

    public void setPeoplePerRoom(int peoplePerRoom) {
        this.peoplePerRoom = peoplePerRoom;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    @Override
    public String toString() {

        return "Room number " + "[" + roomNumber + "]" +
                ", arrivalDate = " + "[" + getArrivalDate() + "]" +
                ", " + getHowManyDays() + " days" +
                ", " + name + " " + surname +
                ", is Clean = " + isClean +
                ", is Rent = " + isRent + "\n";
    }

}


