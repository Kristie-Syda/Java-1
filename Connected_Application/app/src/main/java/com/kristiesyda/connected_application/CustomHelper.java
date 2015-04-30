package com.kristiesyda.connected_application;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

/**
 * Created by Kristie Syda on 4/28/15.
 */
public class CustomHelper {

    public static Boolean isConnected(Context _context){

        //Create Boolean Value
        Boolean isConnected=false;

        ConnectivityManager mgr = (ConnectivityManager) _context.getSystemService(Context.CONNECTIVITY_SERVICE);

        if(mgr != null){
            NetworkInfo networkInfo = mgr.getActiveNetworkInfo();

            if(networkInfo != null && networkInfo.isConnected()){
                isConnected = true;
            } else {
                isConnected = false;
            }
        }
        return isConnected;
    }
}
