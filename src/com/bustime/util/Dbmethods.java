package com.bustime.util;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 * Created by Rushan on 5/9/2015.
 */
public class Dbmethods {

    public ResultSet getBusRegNo(String MobileNo) throws ClassNotFoundException, Exception{

        //create new connection
        Dbconnector db=new Dbconnector();
        Connection con=db.connectToDb();
        // statements allow to issue SQL queries to the database
        PreparedStatement preparedStatement = (PreparedStatement)con.prepareStatement("SELECT * FROM busdetails WHERE busdetails.MobileNo=?;");
        preparedStatement.setString(1, MobileNo);

        ResultSet resultSet = preparedStatement.executeQuery();
        //con.close();
        return resultSet;
    }
}
