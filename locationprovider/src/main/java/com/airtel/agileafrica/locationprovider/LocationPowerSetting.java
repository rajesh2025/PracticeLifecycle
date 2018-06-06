package com.airtel.agileafrica.locationprovider;

import com.google.android.gms.location.LocationRequest;

/**
 * Developer: Rishabh Dutt Sharma
 * <p/>
 * Organization: Airtel [A1UGPQS2]
 * Dated: 4/6/2018.
 */
public enum LocationPowerSetting {

    LOCATION_HIGH_ACCURACY_SETTINGS(LocationRequest.PRIORITY_HIGH_ACCURACY, LocationConstants.REQ_LOCATION_HIGH_ACCURACY_SETTINGS),
    LOCATION_BALANCED_POWER_SETTINGS(LocationRequest.PRIORITY_BALANCED_POWER_ACCURACY, LocationConstants.REQ_LOCATION_BALANCED_POWER_SETTINGS),
    LOCATION_LOW_POWER_SETTINGS(LocationRequest.PRIORITY_LOW_POWER, LocationConstants.REQ_LOCATION_LOW_POWER_SETTINGS),
    LOCATION_NO_POWER_SETTINGS(LocationRequest.PRIORITY_NO_POWER, LocationConstants.REQ_LOCATION_NO_POWER_SETTINGS);


    public final int priority;
    public final int settingRequestCode;

    LocationPowerSetting(int priority, int settingRequestCode) {
        this.priority = priority;
        this.settingRequestCode = settingRequestCode;
    }

    public static LocationPowerSetting getNextPowerSetting(int currentRequestCode) {

        switch (currentRequestCode) {

            case LocationConstants.REQ_LOCATION_HIGH_ACCURACY_SETTINGS:
                return LOCATION_BALANCED_POWER_SETTINGS;

            case LocationConstants.REQ_LOCATION_BALANCED_POWER_SETTINGS:
                return LOCATION_LOW_POWER_SETTINGS;

            case LocationConstants.REQ_LOCATION_LOW_POWER_SETTINGS:
                return LOCATION_NO_POWER_SETTINGS;
        }

        return null;
    }

    public static boolean containsRequestCode(int settingRequestCode) {
        for (LocationPowerSetting locationPowerSetting : values())
            if (locationPowerSetting.settingRequestCode == settingRequestCode)
                return true;
        return false;
    }

    public LocationRequest buildLocationRequest(long updateInterval, long fastestUpdateInterval) {
        return LocationRequest.create()
                .setInterval(updateInterval)
                .setFastestInterval(fastestUpdateInterval)
                .setPriority(priority);
    }

    public LocationRequest buildLocationRequest() {
        return buildLocationRequest(BuildConfig.locationUpdateInterval, BuildConfig.locationFastestUpdateInterval);
    }
}
