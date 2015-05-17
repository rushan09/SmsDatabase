package com.bustime.main;

import com.bustime.logic.GsmModem;

/**
 * Created by Rushan on 4/7/2015.
 */
public class ReadSms {

    private static String port = "COM9"; //Modem Port.
    private static int bitRate = 115200;
    private static String modemName = "HUAWEI";
    private static String modemPin = "0000";
    private static String SMSC = "+9477000003"; //Message Center Number ex. Mobitel


    public static void main(String[] args) throws Exception {
        GsmModem gsmModem = new GsmModem();
        GsmModem.configModem(port, bitRate, modemName, modemPin, SMSC);

        gsmModem.Sender("+94768073555", "begin123456"); // (tp, msg)
//        gsmModem.Sender("+94768073557", "t010s003n123456"); // (tp, msg)
//        gsmModem.Sender("+94717527175", "Test SMS"); // (tp, msg)

        //get the new incoming sms
        gsmModem.Reader();

    }

}

