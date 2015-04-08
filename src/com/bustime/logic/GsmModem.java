package com.bustime.logic;

import org.smslib.*;
import org.smslib.modem.SerialModemGateway;


/**
 * Created by Rushan on 3/27/2015.
 */

public class GsmModem {
    private static String port;
    private static int bitrate;
    private static String modem;
    private static String modemPin;
    private static String smsc;
    private static String tp;
    private static String msgBody;

    public GsmModem() {
    }


    SerialModemGateway gateway;
    public static void configModem(String p, int b, String m, String pi, String s) {
        port = p;
        bitrate = b;
        modem = m;
        modemPin = pi;
        smsc = s;
    }

    public void Sender(String tpnum, String message) throws Exception {
        tp = tpnum;
        msgBody = message;
        this.doIt();
    }
    public String Reader() throws Exception {
        // TO DO
        //Should return the recieved new sms
        this.readIt();
        return null;
    }
    public void readIt() throws Exception {
        gateway = new SerialModemGateway("modem.com1", port, bitrate, modem, "");
        gateway.setInbound(true);
        Service.getInstance().setInboundMessageNotification(new GsmModem.InboundNotification());
        Service.getInstance().addGateway(gateway);
        Service.getInstance().startService();
        System.in.read();
        Service.getInstance().stopService();
    }

    public void doIt() throws Exception {
        GsmModem.OutboundNotification outboundNotification = new GsmModem.OutboundNotification();
        gateway = new SerialModemGateway("modem.com1", port, bitrate, modem, "");
        gateway.setOutbound(true);
        gateway.setSimPin(modemPin);
        gateway.setSmscNumber(smsc);
        Service.getInstance().setOutboundMessageNotification(outboundNotification);
        Service.getInstance().addGateway(gateway);
        Service.getInstance().startService();
        OutboundMessage msg = new OutboundMessage(tp, msgBody);
        Service.getInstance().sendMessage(msg);
        System.out.println(msg);
        Service.getInstance().stopService();
    }

    public class InboundNotification implements IInboundMessageNotification{

        @Override
        public void process(AGateway aGateway, Message.MessageTypes messageTypes, InboundMessage msg) {

            switch (messageTypes)
            {
                case INBOUND:
                    System.out.println(">>> New Inbound message detected from " + "+"
                            + msg.getOriginator()
                            + " "
                            + msg.getText() );
                    break;
                case STATUSREPORT:

                    break;
            }
        }
    }


    public class OutboundNotification implements IOutboundMessageNotification {
        public OutboundNotification() {
        }

        public void process(AGateway gateway, OutboundMessage msg) {
            System.out.println("Outbound handler called from Gateway: " + gateway.getGatewayId());
            System.out.println(msg);
        }
    }
}



//    public void doIt() throws Exception {
//        GsmModem.OutboundNotification outboundNotification = new GsmModem.OutboundNotification();
//        System.out.println("-----------------------------");
//        System.out.println("---Rushans GSM Modem Class---");
//        System.out.println("Example: Send message from a serial gsm modem.");
//        System.out.println(Library.getLibraryDescription());
//        System.out.println("Version: " + Library.getLibraryVersion());
//        gateway = new SerialModemGateway("modem.com1", port, bitrate, modem, "");
//        gateway.setOutbound(true);
//        gateway.setSimPin(modemPin);
//        gateway.setSmscNumber(smsc);
//        Service.getInstance().setOutboundMessageNotification(outboundNotification);
//        Service.getInstance().addGateway(gateway);
//        Service.getInstance().startService();
//        System.out.println();
//        System.out.println("Modem Information:");
//        System.out.println("  Manufacturer: " + gateway.getManufacturer());
//        System.out.println("  Model: " + gateway.getModel());
//        System.out.println("  Serial No: " + gateway.getSerialNo());
//        System.out.println("  SIM IMSI: " + gateway.getImsi());
//        System.out.println("  Signal Level: " + gateway.getSignalLevel() + " dBm");
//        System.out.println("  Battery Level: " + gateway.getBatteryLevel() + "%");
//        System.out.println();
//        OutboundMessage msg = new OutboundMessage(tp, msgBody);
//        Service.getInstance().sendMessage(msg);
//        System.out.println(msg);
////        System.out.println("Now Sleeping - Hit <enter> to terminate.");
////        System.in.read();
//
////        System.out.println("read!");
////        Service.getInstance().stopService();
//    }