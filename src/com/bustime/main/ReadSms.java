package com.bustime.main;

import com.bustime.logic.GsmModem;

/**
 * Created by Rushan on 4/7/2015.
 */
public class ReadSms {

    private static String port = "COM13"; //Modem Port.
    private static int bitRate = 115200;
    private static String modemName = "HUAWEI";
    private static String modemPin = "0000";
    private static String SMSC = "+9477000003"; //Message Center Number ex. Mobitel


    public static void main(String[] args) throws Exception {
        GsmModem gsmModem = new GsmModem();
        GsmModem.configModem(port, bitRate, modemName, modemPin, SMSC);

//        gsmModem.Sender("+94768073557", "begin123456"); // (tp, msg)
        gsmModem.Sender("+94768073557", "t030s002n123456"); // (tp, msg)
//        gsmModem.Sender("+94717527175", "t030s002n123456"); // (tp, msg)

        //get the new incoming sms
        gsmModem.Reader();

    }

}

