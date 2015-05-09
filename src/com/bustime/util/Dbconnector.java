package com.bustime.util;



import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Created by Rushan on 11/11/15.
 */


public class Dbconnector {
    private Connection connect = null;
//    private Statement statement = null;
//    private PreparedStatement preparedStatement = null;
//    private ResultSet resultSet = null;

    /**
     * this return the connection object
     * */
    public Connection connectToDb() throws ClassNotFoundException, SQLException {
        Class.forName("com.mysql.jdbc.Driver");
        //connect to the db
        String url="jdbc:mysql://localhost/bustimetracker";
        String user="root";
        String pw="1234";

        connect= DriverManager.getConnection(url, user, pw);
        return connect;
        
    }

}
