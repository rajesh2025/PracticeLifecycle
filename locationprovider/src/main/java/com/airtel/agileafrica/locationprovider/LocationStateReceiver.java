package com.airtel.agileafrica.locationprovider;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.text.TextUtils;

import static com.airtel.agileafrica.locationprovider.LocationConstants.ACTION_LOCATION_CHANGED;

/**
 * Developer: Rishabh Dutt Sharma
 * <p/>
 * Organization: Airtel [A1UGPQS2]
 * Dated: 4/6/2018.
 */
public class LocationStateReceiver extends BroadcastReceiver {

    private final OnLocationStateChangedListener mListener;

    private LocationStateReceiver(OnLocationStateChangedListener listener) {
        this.mListener = listener;
    }

    public static LocationStateReceiver register(Context context, OnLocationStateChangedListener listener) {
        if (context == null) return null;

        LocationStateReceiver locationStateReceiver = new LocationStateReceiver(listener);
        context.registerReceiver(locationStateReceiver, new IntentFilter(ACTION_LOCATION_CHANGED));
        return locationStateReceiver;
    }

    public static void unregister(Context context, LocationStateReceiver receiver) {
        if (context != null) context.unregisterReceiver(receiver);
    }

    @Override
    public void onReceive(Context context, Intent intent) {

        if (intent != null && TextUtils.equals(intent.getAction(), ACTION_LOCATION_CHANGED))
            if (mListener != null)
                mListener.onLocationStateChanged(LocationUtils.isGPSEnabled(context));
    }

    public interface OnLocationStateChangedListener {

        void onLocationStateChanged(boolean locationEnabled);
    }
}