package com.airtel.agileafrica.locationprovider;

import android.Manifest;

/**
 * Developer: Rishabh Dutt Sharma
 * <p/>
 * Organization: Airtel [A1UGPQS2]
 * Dated: 4/5/2018.
 */
public interface LocationConstants {

    int REQ_PERMISSIONS_LOCATION_UPDATE = 1801;
    int REQ_PERMISSIONS_LOCATION_LAST_KNOWN = 1802;

    int REQ_LOCATION_HIGH_ACCURACY_SETTINGS = 1803;
    int REQ_LOCATION_BALANCED_POWER_SETTINGS = 1804;
    int REQ_LOCATION_LOW_POWER_SETTINGS = 1805;
    int REQ_LOCATION_NO_POWER_SETTINGS = 1806;

    String PERM_ACCESS_FINE_LOCATION = Manifest.permission.ACCESS_FINE_LOCATION;
    String PERM_ACCESS_COARSE_LOCATION = Manifest.permission.ACCESS_COARSE_LOCATION;

    String ACTION_LOCATION_CHANGED = "android.location.PROVIDERS_CHANGED";
}
