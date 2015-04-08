package com.bustime.main;

import com.bustime.logic.GsmModem;
import com.bustime.model.SmsDetailsModel;

/**
 * Created by Rushan on 4/7/2015.
 */
public class ReadSms {

    private static String port = "COM13"; //Modem Port.
    private static int bitRate = 115200;
    private static String modemName = "HUAWEI";
    private static String modemPin = "0000";
    private static String SMSC = "+9477000003"; //Message Center Number ex. Mobitel
    public static String Sms = "";

    public static void main(String[] args) throws Exception {
        GsmModem gsmModem = new GsmModem();
        GsmModem.configModem(port, bitRate, modemName, modemPin, SMSC);
//        gsmModem.Sender("+94717527175", "Test Message"); // (tp, msg)
        //get the new incoming sms
        Sms = gsmModem.Reader();

        SmsDetailsModel smsDetails;
        smsDetails = filterSms(Sms);
//        thre//
        saveToDB(smsDetails);

    }

    private static void saveToDB(SmsDetailsModel smsDetails) {
        //To Do
    }

    public static SmsDetailsModel filterSms(String massage){
        //To Do
        //This function returns a model
        SmsDetailsModel smsDetails = new SmsDetailsModel();
        return smsDetails;
    }
}

