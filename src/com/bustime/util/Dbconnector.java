package com.bustime.util;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Created by Rushan on 5/9/2015.
 */


public class Dbconnector {
    private Connection connect = null;
//    private Statement statement = null;
//    private PreparedStatement preparedStatement = null;
//    private ResultSet resultSet = null;

    /**
     * this return the connection object
     * */
//    public Connection connectToDb() throws ClassNotFoundException, SQLException {
//        Class.forName("com.mysql.jdbc.Driver");
//        //connect to the db
//        String url="jdbc:mysql://localhost/bustimetracker";
//        String user="root";
//        String pw="1234";
//
//        connect= DriverManager.getConnection(url, user, pw);
//        return connect;

//    }

    public Connection connectToDb() throws ClassNotFoundException, SQLException {
        Class.forName("com.mysql.jdbc.Driver");
        //connect to the db
        String url="jdbc:mysql://tesla.ce.pdn.ac.lk/e10030";
        String user="e10030";
        String pw="7SY7ZcBN6q8dGc7d";

        connect= DriverManager.getConnection(url, user, pw);
        return connect;

    }

}
