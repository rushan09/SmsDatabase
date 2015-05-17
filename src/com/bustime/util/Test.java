package com.bustime.util;

import com.bustime.model.BusStopDetailsModel;
import com.bustime.model.SmsDetailsModel;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Rushan on 5/9/2015.
 */
public class Test {


    private static Dbmethods db = new Dbmethods();

    public static void main(String[] args) throws Exception {

        Dbmethods db = new Dbmethods();


        String mobileno = "+94717527175";

        List<BusStopDetailsModel> BusStopDetailsList = new ArrayList<BusStopDetailsModel>();
        BusStopDetailsList = db.getBusStopDetailsList("route4");
//        int busRegNo= db.GetBusRouteNo(mobileno);
//        String busRegNo= db.getBusRegNo(mobileno);
//        ResultSet result= db.getBusRegNo(mobileno);


//            System.out.println(busRegNo);

//        for (BusStopDetailsModel name : BusStopDetailsList) {
//            System.out.print(name.getBusStopNo()+" ");
//            System.out.print(name.getHoltName() + " ");
//            System.out.print(name.getLatitude() + " ");
//            System.out.println(name.getLongitude());
//        }

//         while (result.next()) {
//            System.out.println(result.getString("BusStopNo"));
//            System.out.println(result.getString("Latitude"));
//            System.out.println(result.getString("Longitude"));
//
//        }
//        System.out.print(getCurrentBusStopNo("route4", 79.8503f, 7.300f));
        SmsDetailsModel smsDetails = new SmsDetailsModel();
        smsDetails.setLatitude(7.0010f);
        smsDetails.setLongitude(79.0014f);
        smsDetails.setSpeed(25);
        smsDetails.setDate("2015-05-17");
        smsDetails.setTime("9.24");

        db.saveSmsDetailsToDB(smsDetails,5,"Test","AA-1685");
        System.out.println("dONE");
    }
}


