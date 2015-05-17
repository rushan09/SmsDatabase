package com.bustime.logic;

import com.bustime.model.BusStopDetailsModel;
import com.bustime.model.SmsDetailsModel;
import com.bustime.util.Dbmethods;

import java.sql.SQLException;
import java.util.List;

/**
 * Created by Rushan on 4/16/2015.
 */

public class FilterSms {

    private static SmsDetailsModel smsDetails;
    private static float longitude=0;
    private static float latitude=0;
    private static Dbmethods db = new Dbmethods();

    public static void filterSmsDetails(String sms){

        smsDetails = new SmsDetailsModel();

        StringBuffer sb1 = new StringBuffer(sms);

        smsDetails.setMobileNumber(String.valueOf(sb1.substring(0, 12)));

        sb1.replace( 0, (sb1.toString().length()), ((sb1.toString()).split("lat: ")[1]) );
        smsDetails.setLatitude(Float.parseFloat(sb1.substring(0, 7)));

        sb1.replace( 0, (sb1.toString().length()), ((sb1.toString()).split("long: ")[1]) );
        smsDetails.setLongitude(Float.parseFloat(sb1.substring(0, 7)));

        sb1.replace( 0, (sb1.toString().length()), ((sb1.toString()).split("speed: ")[1]) );
        smsDetails.setSpeed(Float.parseFloat(sb1.substring(0,4)));
        smsDetails.setDate(sb1.substring(5, 13));
        smsDetails.setTime(sb1.substring(14, 19));

        sb1.replace( 0, (sb1.toString().length()), ((sb1.toString()).split("F:")[1]) );
        smsDetails.setBattery(sb1.substring(0, 5));
        smsDetails.setCharging(Integer.parseInt(sb1.substring(6,7)));

        sb1.replace( 0, (sb1.toString().length()), ((sb1.toString()).split("Signal:")[1]) );
        smsDetails.setGPS_Signal(sb1.substring(0, 1));

        sb1.replace( 0, (sb1.toString().length()), ((sb1.toString()).split("imei:")[1]) );
        smsDetails.setImei(sb1.substring(0, 15));

        if((latitude==smsDetails.getLatitude())&&(longitude==smsDetails.getLongitude())){
//        if(((longitude!=0)&&(latitude!=0))||((latitude==smsDetails.getLatitude())&&(longitude==smsDetails.getLongitude()))){

//            System.out.println("msg received! ");
            System.out.println();
        }
        else {

            saveToDB(smsDetails);
            longitude=smsDetails.getLongitude();
            latitude=smsDetails.getLatitude();
        }

    }

    private static void saveToDB(SmsDetailsModel smsDetails) {
        //To Do
        System.out.println("MobileNo: "+ smsDetails.getMobileNumber());
        System.out.println("Latitude: "+ smsDetails.getLatitude());
        System.out.println("Longitude: "+ smsDetails.getLongitude());
        System.out.println("Speed: "+ smsDetails.getSpeed());
        System.out.println("Date: "+ smsDetails.getDate());
        System.out.println("Time: "+ smsDetails.getTime());
        System.out.println("Battery: "+ smsDetails.getBattery());
        System.out.println("Charging: "+ smsDetails.getCharging());
        System.out.println("GPS_Signal: "+ smsDetails.getGPS_Signal());
        System.out.println("Imei: "+ smsDetails.getImei());
        System.out.println();
        int currentBusStop = getCurrentBusStopNo(String.valueOf((getBusRouteNo(smsDetails.getMobileNumber()))),smsDetails.getLongitude(),smsDetails.getLatitude());
        try {
            db.saveSmsDetailsToDB(smsDetails,currentBusStop,"Pettah","AT-4245");
//            db.saveSmsDetailsToDB(smsDetails,currentBusStop,"Pettah",getBusRegNo(smsDetails.getMobileNumber()));
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    private static int getBusRouteNo(String MobileNo){
        int BusRouteNo=-1;
        try {
            BusRouteNo = db.GetBusRouteNo(MobileNo);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return BusRouteNo;
    }

    private static String getBusRegNo(String MobileNo){
        String BusRegNo = null;
        try {
            BusRegNo = db.getBusRegNo(MobileNo);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return BusRegNo;
    }

    private static String getDirection(String BusRegNo,int BusRouteNo,float Longitude,float Latitude){
        List<BusStopDetailsModel> directions = null;
        try {
            directions = db.getBusRouteDirectionsDetails(BusRouteNo);
        } catch (Exception e) {
            e.printStackTrace();
        }
        BusStopDetailsModel oldDetails = null;
        try {
            oldDetails = db.getOldDetails(BusRegNo);
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        if(directions.get(0).getHoltName()==oldDetails.getHoltName()){
            double nowLength = Math.sqrt(Math.pow((directions.get(0).getLongitude() - Longitude), 2) + Math.pow((directions.get(0).getLatitude() - Latitude), 2));
            double oldLength= Math.sqrt(Math.pow((directions.get(0).getLongitude() - oldDetails.getLongitude()), 2) + Math.pow((directions.get(0).getLatitude() - oldDetails.getLatitude()), 2));

            if(nowLength>oldLength){
                oldDetails.setHoltName(directions.get(1).getHoltName());
            }
        }else {
            double nowLength = Math.sqrt(Math.pow((directions.get(1).getLongitude() - Longitude), 2) + Math.pow((directions.get(1).getLatitude() - Latitude), 2));
            double oldLength= Math.sqrt(Math.pow((directions.get(1).getLongitude() - oldDetails.getLongitude()), 2) + Math.pow((directions.get(1).getLatitude() - oldDetails.getLatitude()), 2));

            if(nowLength>oldLength){
                oldDetails.setHoltName(directions.get(0).getHoltName());
            }
        }
        return oldDetails.getHoltName();
    }

    private static int getCurrentBusStopNo(String BusRoute, float Longitude, float Latitude){
        int a = 0;
        List<BusStopDetailsModel> BusStopDetailsList = null;
        try {
            BusStopDetailsList = db.getBusStopDetailsList("route138");
        } catch (Exception e) {
            e.printStackTrace();
        }
        double length;
        double shortestLength = Math.sqrt(Math.pow((BusStopDetailsList.get(0).getLongitude() - Longitude), 2) + Math.pow((BusStopDetailsList.get(0).getLatitude() - Latitude), 2));

        for(int i=1;i<BusStopDetailsList.size();i++){
            length=Math.sqrt(Math.pow((BusStopDetailsList.get(i).getLongitude()-Longitude),2)+Math.pow((BusStopDetailsList.get(i).getLatitude()-Latitude),2));
            if(shortestLength > length){
                a=i;

                shortestLength=length;
            }
        }

        return BusStopDetailsList.get(a).getBusStopNo();
    }
}
