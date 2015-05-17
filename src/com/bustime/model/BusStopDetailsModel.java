package com.bustime.model;

/**
 * Created by Hiran Pathirana on 0003, 03-May-15.
 */
public class BusStopDetailsModel {
    private int BusStopNo;
    private float Latitude;
    private float Longitude;
    private  String HoltName;

    public int getBusStopNo() {return BusStopNo;}

    public void setBusStopNo(int busStopNo) {
        BusStopNo = busStopNo;
    }

    public String getHoltName() {return HoltName;}

    public void setHoltName(String holtName) {
        HoltName = holtName;
    }

    public float getLatitude() {
        return Latitude;
    }

    public void setLatitude(float latitude) {
        Latitude = latitude;
    }

    public float getLongitude() {
        return Longitude;
    }

    public void setLongitude(float longitude) {
        Longitude = longitude;
    }
}
