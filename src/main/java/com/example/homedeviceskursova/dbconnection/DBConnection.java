package com.example.homedeviceskursova.dbconnection;

import java.sql.*;

public class DBConnection {
    static String dbUrl = "jdbc:mysql://localhost:3306/DBDevices?characterEncoding=latin1";
    static String User = "root";
    static String Password = "11111";

    public static Connection GetCon() throws SQLException, ClassNotFoundException {
        Class.forName("com.mysql.jdbc.Driver");
        Connection MyCon = (Connection) DriverManager.getConnection(dbUrl,User,Password);
        return MyCon;
    }
}
