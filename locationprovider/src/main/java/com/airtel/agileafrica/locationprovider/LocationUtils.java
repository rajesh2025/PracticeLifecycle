package com.airtel.agileafrica.locationprovider;

import android.content.Context;
import android.location.LocationManager;

/**
 * Developer: Rishabh Dutt Sharma
 * <p/>
 * Organization: Airtel [A1UGPQS2]
 * Dated: 4/6/2018.
 */
public class LocationUtils {

    /**
     * Method to check whether location services
     * through network are enabled or not
     *
     * @return
     */
    private static boolean isProviderEnabled(Context context, String provider) {
        try {
            LocationManager manager = (LocationManager) context.getSystemService(Context.LOCATION_SERVICE);
            if (manager != null) return manager.isProviderEnabled(provider);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return false;
    }

    /**
     * Method to check whether the GPS is enabled or not
     *
     * @param context
     * @return
     */
    public static boolean isGPSEnabled(Context context) {
        return isProviderEnabled(context, LocationManager.GPS_PROVIDER);
    }

    /**
     * Method to check whether location services via Network are enabled or not
     *
     * @return
     */
    public static boolean isNetworkEnabled(Context context) {
        return isProviderEnabled(context, LocationManager.NETWORK_PROVIDER);
    }
}
