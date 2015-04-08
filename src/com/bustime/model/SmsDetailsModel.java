package com.bustime.model;

/**
 * Created by Rushan on 4/8/2015.
 */
public class SmsDetailsModel {

    private int Latitude;
    private int Longitude;
    private int Speed;      //(Km/h)
    private String Date;
    private String Time;        // 08 34.0 413 02 4EDF 6155
    private String Battery;
    private int Charging;
    private char GPS_Signal;
    private String Imei;
    private int GPS_Satellites;
    private int Altitude;
    private int MCC;        //Mobile Country Code
    private int MNC;        //Mobile Network Code
    private int LAC;        //Location Area Code
    private int Cell_Id;

    public int getLatitude() {
        return Latitude;
    }

    public void setLatitude(int latitude) {
        Latitude = latitude;
    }

    public int getLongitude() {
        return Longitude;
    }

    public void setLongitude(int longitude) {
        Longitude = longitude;
    }

    public int getSpeed() {
        return Speed;
    }

    public void setSpeed(int speed) {
        Speed = speed;
    }

    public String getDate() {
        return Date;
    }

    public void setDate(String date) {
        Date = date;
    }

    public String getTime() {
        return Time;
    }

    public void setTime(String time) {
        Time = time;
    }

    public String getBattery() {
        return Battery;
    }

    public void setBattery(String battery) {
        Battery = battery;
    }

    public int getCharging() {
        return Charging;
    }

    public void setCharging(int charging) {
        Charging = charging;
    }

    public char getGPS_Signal() {
        return GPS_Signal;
    }

    public void setGPS_Signal(char GPS_Signal) {
        this.GPS_Signal = GPS_Signal;
    }

    public String getImei() {
        return Imei;
    }

    public void setImei(String imei) {
        Imei = imei;
    }

    public int getGPS_Satellites() {
        return GPS_Satellites;
    }

    public void setGPS_Satellites(int GPS_Satellites) {
        this.GPS_Satellites = GPS_Satellites;
    }

    public int getAltitude() {
        return Altitude;
    }

    public void setAltitude(int altitude) {
        Altitude = altitude;
    }

    public int getMCC() {
        return MCC;
    }

    public void setMCC(int MCC) {
        this.MCC = MCC;
    }

    public int getMNC() {
        return MNC;
    }

    public void setMNC(int MNC) {
        this.MNC = MNC;
    }

    public int getLAC() {
        return LAC;
    }

    public void setLAC(int LAC) {
        this.LAC = LAC;
    }

    public int getCell_Id() {
        return Cell_Id;
    }

    public void setCell_Id(int cell_Id) {
        Cell_Id = cell_Id;
    }

}
