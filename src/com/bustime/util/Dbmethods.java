package com.bustime.util;

import com.bustime.model.BusStopDetailsModel;
import com.bustime.model.SmsDetailsModel;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Rushan on 5/9/2015.
 */
public class Dbmethods {

    public String getBusRegNo(String MobileNo) throws ClassNotFoundException, Exception{

        //create new connection
        Dbconnector db=new Dbconnector();
        Connection con=db.connectToDb();
        // statements allow to issue SQL queries to the database
        PreparedStatement preparedStatement = (PreparedStatement)con.prepareStatement("SELECT BusRegNo FROM busdetails WHERE busdetails.MobileNo=?;");
        preparedStatement.setString(1, MobileNo);

        ResultSet result = preparedStatement.executeQuery();
        String BusRegNo = null;
        if (result.next()) {
            //Retrieve by column name
            BusRegNo = result.getString("BusRegNo");
        }
        //con.close();
        return BusRegNo;
    }

    public int GetBusRouteNo(String MobileNo) throws ClassNotFoundException, Exception{

        //create new connection
        Dbconnector db=new Dbconnector();
        Connection con=db.connectToDb();
        // statements allow to issue SQL queries to the database
        PreparedStatement preparedStatement = (PreparedStatement)con.prepareStatement("SELECT BusRoute FROM busdetails WHERE busdetails.MobileNo=?;");
        preparedStatement.setString(1, MobileNo);

        ResultSet result = preparedStatement.executeQuery();
        int BusRouteNo = -1;
        if (result.next()) {
            //Retrieve by column name
            BusRouteNo = result.getInt("BusRoute");
        }
        //con.close();
        return BusRouteNo;
    }

    public List<BusStopDetailsModel> getBusStopDetailsList(String BusRoute)throws ClassNotFoundException, Exception{
        //create new connection
        Dbconnector db=new Dbconnector();
        Connection con=db.connectToDb();
        // statements allow to issue SQL queries to the database
        PreparedStatement preparedStatement = (PreparedStatement)con.prepareStatement("SELECT * FROM "+BusRoute);
        ResultSet resultSet = preparedStatement.executeQuery();
        List<BusStopDetailsModel> BusStopDetailsList = new ArrayList<BusStopDetailsModel>();
        while(resultSet.next()){
            BusStopDetailsModel BusStopDetails = new BusStopDetailsModel();
            //Retrieve by column name
            BusStopDetails.setBusStopNo(resultSet.getInt("BusStopNo"));
            BusStopDetails.setLatitude(resultSet.getFloat("Latitude"));
            BusStopDetails.setLongitude(resultSet.getFloat("Longitude"));
            BusStopDetails.setHoltName(resultSet.getString("HoltName"));

            BusStopDetailsList.add(BusStopDetails);
        }
        return BusStopDetailsList;
    }

    public void saveSmsDetailsToDB(SmsDetailsModel smsDetails,int currentBusStop,String direction,String busNo)throws ClassNotFoundException, Exception{
        //create new connection
        Dbconnector db=new Dbconnector();
        Connection con=db.connectToDb();
//        PreparedStatement preparedStatement = (PreparedStatement)con.prepareStatement("UPDATE buscurruntposition set  Latitude= ? WHERE Speed = ?;");
//        PreparedStatement preparedStatement = (PreparedStatement)con.prepareStatement("update buscurruntposition set  Latitude= ?, Longitude= ?, Speed= ?, CurrenrBusStop= ? where BusNo = ?");
        PreparedStatement preparedStatement = (PreparedStatement)con.prepareStatement("update buscurruntposition set  Latitude= ? ,Longitude= ?,Speed= ?,Date =?, CurruntTime= ?,CurruntBusStop= ?,Direction= ?  where BusNo = ?");
        preparedStatement.setFloat(1, smsDetails.getLatitude());
        preparedStatement.setFloat(2,smsDetails.getLongitude());
        preparedStatement.setFloat(3,smsDetails.getSpeed());
        preparedStatement.setString(4, smsDetails.getDate());
        preparedStatement.setString(5,smsDetails.getTime());
        preparedStatement.setInt(6,currentBusStop);
        preparedStatement.setString(7,direction);
        preparedStatement.setString(8, busNo);

        System.out.println(smsDetails.getLatitude());
        System.out.println(smsDetails.getLongitude());
        System.out.println(smsDetails.getSpeed());
        System.out.println(smsDetails.getDate());
        System.out.println(smsDetails.getTime());
        System.out.println(currentBusStop);
        System.out.println(direction);
        System.out.println(busNo);



        preparedStatement.executeUpdate();
//        ResultSet result = preparedStatement.executeQuery();
        System.out.println("done3");
    }

    public List<BusStopDetailsModel> getBusRouteDirectionsDetails(int busRouteNo)throws ClassNotFoundException, Exception{
        //create new connection
        Dbconnector db=new Dbconnector();
        Connection con=db.connectToDb();
        PreparedStatement preparedStatement = (PreparedStatement)con.prepareStatement("SELECT direction.From,direction.To FROM direction WHERE direction.RouteNo=?;");
        preparedStatement.setInt(1, busRouteNo);

        ResultSet result = preparedStatement.executeQuery();
        List<BusStopDetailsModel> directions = null;

        if (result.next()) {
            //Retrieve by column name
            directions.add(getBusStopDetails("route"+busRouteNo,result.getString("From")));
            directions.add(getBusStopDetails("route" + busRouteNo, result.getString("To")));
        }
        return directions;
    }

    public BusStopDetailsModel getBusStopDetails(String BusRoute,String BusStop)throws ClassNotFoundException, Exception{
        //create new connection
        Dbconnector db=new Dbconnector();
        Connection con=db.connectToDb();
        // statements allow to issue SQL queries to the database
        PreparedStatement preparedStatement = (PreparedStatement)con.prepareStatement("SELECT * FROM "+BusRoute+" WHERE HoltName=?");
        preparedStatement.setString(1, BusStop);
        ResultSet resultSet = preparedStatement.executeQuery();
        BusStopDetailsModel BusStopDetails = new BusStopDetailsModel();
        if(resultSet.next()){
            //Retrieve by column name
            BusStopDetails.setBusStopNo(resultSet.getInt("BusStopNo"));
            BusStopDetails.setHoltName(resultSet.getString("HoltName"));
            BusStopDetails.setLatitude(resultSet.getFloat("Latitude"));
            BusStopDetails.setLongitude(resultSet.getFloat("Longitude"));
        }
        return BusStopDetails;
    }

    public BusStopDetailsModel getOldDetails(String busRegNo) throws SQLException, ClassNotFoundException {
        //create new connection
        Dbconnector db=new Dbconnector();
        Connection con=db.connectToDb();
        // statements allow to issue SQL queries to the database
        PreparedStatement preparedStatement = (PreparedStatement)con.prepareStatement("SELECT Longitude,Latitude,Direction FROM buscurruntposition WHERE BusNo=?");
        preparedStatement.setString(1, busRegNo);
        ResultSet result = preparedStatement.executeQuery();
        BusStopDetailsModel nowDirection = null;
        if(result.next()){
            nowDirection.setHoltName(result.getString("Direction"));
            nowDirection.setLongitude(result.getFloat("Longitude"));
            nowDirection.setLatitude(result.getFloat("Latitude"));
        }
        return nowDirection;
    }
}