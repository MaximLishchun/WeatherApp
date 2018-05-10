package com.bignerdranch.android.weather;


import android.content.Context;
import android.net.ConnectivityManager;
import android.telephony.TelephonyManager;

public class CheckNetworkConnection {

    public static boolean isNetworkConnected(Context context) {
        ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        return cm != null && cm.getActiveNetworkInfo() != null && cm.getActiveNetworkInfo().isConnected();
    }
}
