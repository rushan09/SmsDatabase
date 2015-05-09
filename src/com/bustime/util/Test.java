package com.bustime.util;

import java.sql.ResultSet;

/**
 * Created by Rushan on 5/9/2015.
 */
public class Test {
    public static void main(String[] args) throws Exception {

        Dbmethods db = new Dbmethods();


        String mobileno = "+94717527175";
        ResultSet result= db.getBusRegNo(mobileno);

        while (result.next()) {
            System.out.println(result.getString("BusRegNo"));
            System.out.println(result.getString("BusRoute"));
        }

    }
}
