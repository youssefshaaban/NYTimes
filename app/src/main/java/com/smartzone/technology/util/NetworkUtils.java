package com.smartzone.technology.util;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

/**
 * Created by joe on 07/09/2018.
 */

public class NetworkUtils {
    public static boolean isConnected(Context context) {
        // get object from connectivity manager to get instance from network info
        ConnectivityManager manager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo info = manager.getActiveNetworkInfo();
        if (info != null && info.isConnectedOrConnecting()) {
            NetworkInfo wifi = manager.getNetworkInfo(ConnectivityManager.TYPE_WIFI);
            NetworkInfo mobileData = manager.getNetworkInfo(ConnectivityManager.TYPE_MOBILE);
            if ((wifi != null && wifi.isConnectedOrConnecting()) || (mobileData != null && mobileData.isConnectedOrConnecting())) {
                return true;
            } else
                return false;
        } else
            return false;
    }
}
