package com.bustime.logic;

import org.smslib.*;
import org.smslib.modem.SerialModemGateway;

import java.io.IOException;

import static com.bustime.logic.FilterSms.filterSmsDetails;


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
    String rcvdSms;

    SerialModemGateway gateway = null;

    public GsmModem() {

    }
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
        this.sendIt();
    }
    public void sendIt() throws Exception {
        GsmModem.OutboundNotification outboundNotification = new GsmModem.OutboundNotification();
        gateway = new SerialModemGateway("modem.com1", port, bitrate, modem, "");
        gateway.setOutbound(true);
        gateway.setInbound(true);
        gateway.setSimPin(modemPin);
        gateway.setSmscNumber(smsc);
        Service.getInstance().setOutboundMessageNotification(outboundNotification);
        Service.getInstance().setInboundMessageNotification(new GsmModem.InboundNotification());
        Service.getInstance().addGateway(gateway);
        Service.getInstance().startService();
        OutboundMessage msg = new OutboundMessage(tp, msgBody);
        Service.getInstance().sendMessage(msg);
        System.out.println(msg);
    }
    public void Reader() throws Exception {
        // TO DO
        this.readIt();
    }
    public void readIt() throws Exception {
        Service.getInstance().getServiceStatus();
        System.in.read();
        Service.getInstance().stopService();
    }

    public class OutboundNotification implements IOutboundMessageNotification {
        public OutboundNotification() {
        }
        public void process(AGateway gateway, OutboundMessage msg) {
            System.out.println("Outbound handler called from Gateway: " + gateway.getGatewayId());
            System.out.println(msg);
        }
    }
    public class InboundNotification implements IInboundMessageNotification{
        @Override
        public void process(AGateway aGateway, Message.MessageTypes messageTypes, InboundMessage msg) {

            switch (messageTypes) {
                case INBOUND:
                    rcvdSms = msg.getOriginator() + " " + msg.getText();
                    try {
                        aGateway.deleteMessage(msg);
                    } catch (TimeoutException e) {
                        e.printStackTrace();
                    } catch (GatewayException e) {
                        e.printStackTrace();
                    } catch (IOException e) {
                        e.printStackTrace();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                    filterSmsDetails(rcvdSms);
                    break;

                case STATUSREPORT:
                    break;
            }
        }
    }
}
































//switch (messageTypes)
//        {
//        case INBOUND:
//
//        rcvdSms = msg.getOriginator() + " " + msg.getText();
//        System.out.println(">>> New Inbound message detected from " + "+"
//        + msg.getOriginator()
//        + " "
//        + messageTypes
//        + " "
////                            + aGateway.getInboundMessageCount()
//        + " "
//        + msg.getText() );
//
//        try {
//        aGateway.deleteMessage(msg);
//        } catch (TimeoutException e) {
//        e.printStackTrace();
//        } catch (GatewayException e) {
//        e.printStackTrace();
//        } catch (IOException e) {
//        e.printStackTrace();
//        } catch (InterruptedException e) {
//        e.printStackTrace();
//        }
//        break;
//        case STATUSREPORT:
//
//        break;
//        }
//
//


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