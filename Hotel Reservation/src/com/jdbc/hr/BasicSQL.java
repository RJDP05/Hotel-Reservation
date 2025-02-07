package com.jdbc.hr;

public class BasicSQL {
    public static String SQL_URL = "jdbc:mysql://localhost:3306/hotel-db";
    public static final String USERNAME = "root";
    public static final String PASSWORD = "Rjdp@123";
    public static final String INSERT = "INSERT INTO reservation (guest_name, room_number, contact_number) ";
    public static final String VIEW = "SELECT reservation_id, guest_name, room_number, contact_number, reservation_time FROM reservation";
    public static final String ROOMNUMBER = "SELECT room_number,guest_name FROM reservation ";
    public static final String UPDATE = "UPDATE reservation SET ";
    public static final String DELETE = "DELETE FROM reservation WHERE reservation_id = ";
    public static final String ID = "SELECT reservation_id FROM reservation WHERE reservation_id = ";


}
