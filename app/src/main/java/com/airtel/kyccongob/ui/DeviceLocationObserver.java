package com.airtel.kyccongob.ui;

import android.app.Activity;
import android.arch.lifecycle.Lifecycle;
import android.arch.lifecycle.LifecycleObserver;
import android.arch.lifecycle.OnLifecycleEvent;
import android.content.Context;
import android.location.Location;
import android.widget.Toast;

import com.airtel.agileafrica.locationprovider.LocationManager;
import com.airtel.kyccongob.R;

public class DeviceLocationObserver extends LocationManager implements LifecycleObserver{
    private Lifecycle mLifecycle;
    private Activity mContext;
    public DeviceLocationObserver(Activity context,Lifecycle lifecycle) {
        super(context);
        mContext = context;
        mLifecycle = lifecycle;
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_START)
    void start() {
        if (mLifecycle.getCurrentState().isAtLeast(Lifecycle.State.STARTED)) {
             prepareLocationUpdates();
        }
    }


    @OnLifecycleEvent(Lifecycle.Event.ON_STOP)
    void stop() {
     stopTrackingLocation();
    }


}
