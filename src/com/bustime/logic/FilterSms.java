package com.bustime.logic;

import com.bustime.model.SmsDetailsModel;

/**
 * Created by Rushan on 4/16/2015.
 */

public class FilterSms {

    private static SmsDetailsModel smsDetails;
    private static float longitude=0;
    private static float latitude=0;

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

            System.out.println("Saved once! ");
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

    }
}
