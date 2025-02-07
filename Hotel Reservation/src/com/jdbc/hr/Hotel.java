package com.jdbc.hr;

import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Connection;
import java.util.Scanner;
import java.sql.Statement;
import java.sql.ResultSet;


public class Hotel {

    public static void main(String[] args){

        //Driver Loading
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            System.out.println("Driver Loaded!");
        }catch (ClassNotFoundException e){
            System.out.println(e.getMessage());
        }

        //Connection
        try{
            Connection con = DriverManager.getConnection(BasicSQL.SQL_URL,BasicSQL.USERNAME,BasicSQL.PASSWORD);
            System.out.println("DataBase Connected!!");


            UncloseableStatement uncloseable = new UncloseableStatement(con.createStatement());
            Statement statement = uncloseable.getStatement();


            //USER CHOICE
            while (true){
                System.out.println();
                System.out.println("HOTEL MANAGEMENT SYSTEM");

                Scanner scn = new Scanner(System.in);

                System.out.println("1. RESERVE A ROOM");
                System.out.println("2. VIEW RESERVATIONS");
                System.out.println("3. GET ROOM NUMBER");
                System.out.println("4. UPDATE RESERVATION");
                System.out.println("5. DELETE RESERVATION");
                System.out.println("0. EXIT");
                System.out.print("Choose an Option: ");

                int choice = scn.nextInt();

                switch (choice){
                    case 1:
                        reserveRoom(scn , uncloseable , statement);
                        break;
                    case 2:
                        viewReservation(uncloseable , statement);
                        break;
                    case 3:
                        getRoomNumber(scn , uncloseable , statement);
                        break;
                    case 4:
                        updateInfo(scn , uncloseable, statement);
                        break;
                    case 5:
                        deleteInfo(scn , uncloseable, statement);
                        break;
                    case 0:
                        exitHotel(statement);
                        scn.close();
                        return;
                    default:
                        System.out.println("Invalid Choice.");
                }

            }
        }catch (SQLException | InterruptedException e){
            e.printStackTrace();
        }
    }

    private static void reserveRoom(Scanner scanner, UncloseableStatement uncloseableStatement, Statement statement ) {

        try {
                //Details
                User user = new User();
                details(scanner,user);

            String sql = BasicSQL.INSERT + "VALUES ('" + user.getName() + "' , '" + user.getRoom() + "' , '" + user.getNumber() + "')";

            if (statement.isClosed()){
                try {
                    statement = uncloseableStatement.getStatement();
                    int affectedRows = statement.executeUpdate(sql);

                    if (affectedRows > 0) {
                        System.out.println();
                        System.out.println("Reservation Successful !");
                        System.out.println();
                    } else {
                        System.out.println();
                        System.out.println("Reservation Failed !!");
                        System.out.println();
                    }
                }catch (SQLException e){
                    e.printStackTrace();
                }
            }
            else {
                    try {
                        int affectedRows = statement.executeUpdate(sql);

                        if (affectedRows > 0) {
                            System.out.println();
                            System.out.println("Reservation Successful !");
                            System.out.println();
                        } else {
                            System.out.println();
                            System.out.println("Reservation Failed !!");
                            System.out.println();
                        }
                    }catch (SQLException e){
                        e.printStackTrace();
                    }
                }
            }catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private static void viewReservation(UncloseableStatement uncloseableStatement, Statement statement) {

        String sql = BasicSQL.VIEW;

        try {
            if(statement.isClosed()){
                statement = uncloseableStatement.getStatement();
                try ( ResultSet resultSet = statement.executeQuery(sql) ) {

                    System.out.println("Current Reservations:");
                    System.out.println("+----------------+-----------------+---------------+----------------------+-------------------------+");
                    System.out.println("| Reservation ID | Guest           | Room Number   | Contact Number       | Reservation Date        |");
                    System.out.println("+----------------+-----------------+---------------+----------------------+-------------------------+");

                    while (resultSet.next()) {
                        int reservationId = resultSet.getInt("reservation_id");
                        String guestName = resultSet.getString("guest_name");
                        String roomNumber = resultSet.getString("room_number");
                        int contactNumber = resultSet.getInt("contact_number");
                        String reservationDate = resultSet.getTimestamp("reservation_time").toString();

                        // Format and display the reservation data in a table-like format
                        System.out.printf("| %-14d | %-15s | %-13s | %-20d | %-19s   |\n",
                                reservationId, guestName, roomNumber, contactNumber, reservationDate);
                    }

                    System.out.println("+----------------+-----------------+---------------+----------------------+-------------------------+");
                }
            }

            else {
                try ( ResultSet resultSet = statement.executeQuery(sql) ) {

                    System.out.println("Current Reservations:");
                    System.out.println("+----------------+-----------------+---------------+----------------------+-------------------------+");
                    System.out.println("| Reservation ID | Guest           | Room Number   | Contact Number       | Reservation Date        |");
                    System.out.println("+----------------+-----------------+---------------+----------------------+-------------------------+");

                    while (resultSet.next()) {
                        int reservationId = resultSet.getInt("reservation_id");
                        String guestName = resultSet.getString("guest_name");
                        String roomNumber = resultSet.getString("room_number");
                        int contactNumber = resultSet.getInt("contact_number");
                        String reservationDate = resultSet.getTimestamp("reservation_time").toString();

                        // Format and display the reservation data in a table-like format
                        System.out.printf("| %-14d | %-15s | %-13s | %-20d | %-19s   |\n",
                                reservationId, guestName, roomNumber, contactNumber, reservationDate);
                    }

                    System.out.println("+----------------+-----------------+---------------+----------------------+-------------------------+");
                }
            }
        }catch (SQLException e){
                e.printStackTrace();
            }
        }

    private static void getRoomNumber(Scanner scanner, UncloseableStatement uncloseableStatement, Statement statement) {

            User user = new User();
                id(scanner,user);

            String sql = BasicSQL.ROOMNUMBER + "WHERE reservation_id = " + user.getId();
            try{
                if(statement.isClosed()){
                    statement = uncloseableStatement.getStatement();
                    try ( ResultSet resultSet = statement.executeQuery(sql) ) {

                        if (resultSet.next()) {
                            String roomNumber = resultSet.getString("room_number");
                            String guestName = resultSet.getString("guest_name");
                            System.out.println();
                            System.out.println("Room number for Reservation ID " + user.getId() +
                                    " and Guest '" + guestName + "' is: '" + roomNumber);
                            System.out.println();
                        } else {
                            System.out.println();
                            System.out.println("Reservation not found for the given ID !!");
                            System.out.println();
                        }
                    }
                }

                else {
                    try ( ResultSet resultSet = statement.executeQuery(sql) ) {

                        if (resultSet.next()) {
                            String roomNumber =  resultSet.getString("room_number");
                            String guestName =  resultSet.getString("guest_name");
                            System.out.println();
                            System.out.println("Room number for Reservation ID " + user.getId() +
                                    " and Guest " + guestName + " is: " + roomNumber);
                            System.out.println();
                        } else {
                            System.out.println();
                            System.out.println("Reservation not found for the given ID !!");
                            System.out.println();
                        }
                    }
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
    }

    private static void updateInfo(Scanner scanner, UncloseableStatement uncloseableStatement, Statement statement) {
            User user = new User();
             id(scanner,user);

        try {
            if (!reservationExists(user.getId(), uncloseableStatement, statement)) {
                System.out.println("+----------------+-----------------+---------------+----------------------+-------------------------+");
                System.out.println("                               Reservation not found for the given ID.                               ");
                System.out.println("+----------------+-----------------+---------------+----------------------+-------------------------+");
                return;
            }

            details(scanner,user);

            String sql = BasicSQL.UPDATE + "guest_name = '" + user.getName() + "'," +
                    "room_number = '" + user.getRoom() + "', " +
                    "contact_number = " + user.getNumber() + " " +
                    "WHERE reservation_id = " + user.getId();

            if(statement.isClosed()){
                try {
                    statement = uncloseableStatement.getStatement();
                    int affectedRows = statement.executeUpdate(sql);

                    if (affectedRows > 0) {
                        System.out.println("+----------------+-----------------+---------------+----------------------+-------------------------+");
                        System.out.println("                                    Reservation updated successfully!                                ");
                        System.out.println("+----------------+-----------------+---------------+----------------------+-------------------------+");

                    } else {
                        System.out.println();
                        System.out.println("Reservation update failed.");
                        System.out.println();
                    }
                }catch (SQLException e){
                    e.printStackTrace();
                }
            }
            else {
                try {
                    int affectedRows = statement.executeUpdate(sql);
                    if (affectedRows > 0) {
                        System.out.println("+----------------+-----------------+---------------+----------------------+-------------------------+");
                        System.out.println("                                    Reservation updated successfully!                                ");
                        System.out.println("+----------------+-----------------+---------------+----------------------+-------------------------+");
                    } else {
                        System.out.println();
                        System.out.println("Reservation update failed.");
                        System.out.println();
                    }
                }catch (SQLException e){
                    e.printStackTrace();
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private static void deleteInfo(Scanner scanner, UncloseableStatement uncloseableStatement, Statement statement) {
           User user = new User();
                id(scanner,user);

        try {
            if (!reservationExists(user.getId(), uncloseableStatement, statement)){
                System.out.println("+----------------+-----------------+---------------+----------------------+-------------------------+");
                System.out.println("                               Reservation not found for the given ID.                               ");
                System.out.println("+----------------+-----------------+---------------+----------------------+-------------------------+");
                return;
            }

            String sql = BasicSQL.DELETE + user.getId();

            if(statement.isClosed()){
                try {
                    statement = uncloseableStatement.getStatement();
                    int affectedRows = statement.executeUpdate(sql);

                    if (affectedRows > 0) {
                        System.out.println("+----------------+-----------------+---------------+----------------------+-------------------------+");
                        System.out.println("                                    Reservation deleted successfully!                                ");
                        System.out.println("+----------------+-----------------+---------------+----------------------+-------------------------+");
                    } else {
                        System.out.println();
                        System.out.println("Reservation deletion failed.");
                        System.out.println();
                    }
                }catch (SQLException e){
                    e.printStackTrace();
                }
            }
            else {
                try {
                    int affectedRows = statement.executeUpdate(sql);
                    if (affectedRows > 0) {
                        System.out.println("+----------------+-----------------+---------------+----------------------+-------------------------+");
                        System.out.println("                                    Reservation deleted successfully!                                ");
                        System.out.println("+----------------+-----------------+---------------+----------------------+-------------------------+");
                    } else {
                        System.out.println();
                        System.out.println("Reservation deletion failed.");
                        System.out.println();
                    }
                }catch (SQLException e){
                    e.printStackTrace();
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private static boolean reservationExists(int reservationId,UncloseableStatement uncloseableStatement, Statement statement) {

            String sql = BasicSQL.ID + reservationId;

            try {
                if(statement.isClosed()){
                    statement = uncloseableStatement.getStatement();
                    try ( ResultSet resultSet = statement.executeQuery(sql)) {
                        return resultSet.next();// If there's a result, the reservation exists
                    }
                }
                else {
                    try ( ResultSet resultSet = statement.executeQuery(sql)) {
                        return resultSet.next();// If there's a result, the reservation exists
                    }
                }
            }catch (SQLException e) {
                e.printStackTrace();
                return false; // Handle database errors as needed
            }

    }

    private static void details(Scanner scanner, User user){
        System.out.println("Enter guest name: ");
        String guestName = scanner.next();
        scanner.nextLine();
        System.out.println("Enter Phone Number: ");
        int phoneNumber = scanner.nextInt();
        System.out.println("Enter Room Number: ");
        String roomNumber = scanner.next();

        user.setName(guestName);
        user.setNumber(phoneNumber);
        user.setRoom(roomNumber);
    }

    private static void id(Scanner scanner, User user){
        System.out.print("Enter reservation ID: ");
        int reservationId = scanner.nextInt();
//        scanner.nextLine();
        // Consume the newline character

        user.setId(reservationId);
    }

    public static void exitHotel(Statement statement) throws InterruptedException {
        System.out.print("Exiting Hotel");
        int i = 5;
        while(i!=0){
            System.out.print(".");
            Thread.sleep(1000);
            i--;
        }

        try {
            statement.close();
        }catch (SQLException e){
            e.printStackTrace();
        }

        System.out.println();
        System.out.println("ThankYou For Using Hotel Reservation System!!!");
    }

}
