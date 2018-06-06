package com.airtel.agileafrica.locationprovider;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.IntentSender;
import android.location.Location;
import android.support.annotation.NonNull;
import android.text.TextUtils;
import android.util.Log;
import android.widget.Toast;

import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.common.api.ResolvableApiException;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationCallback;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationResult;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.location.LocationSettingsRequest;
import com.google.android.gms.location.LocationSettingsResponse;
import com.google.android.gms.location.LocationSettingsStatusCodes;
import com.google.android.gms.location.SettingsClient;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;

import static com.airtel.agileafrica.locationprovider.LocationConstants.REQ_PERMISSIONS_LOCATION_LAST_KNOWN;
import static com.airtel.agileafrica.locationprovider.LocationConstants.REQ_PERMISSIONS_LOCATION_UPDATE;

/**
 * Developer: Rishabh Dutt Sharma
 * <p/>
 * Organization: Airtel [A1UGPQS2]
 * Dated: 4/5/2018.
 */
public class LocationManager extends LocationCallback implements OnSuccessListener<Location>, OnFailureListener {

    public static double LATITUDE;
    public static double LONGITUDE;

    private static LocationPowerSetting LOCATION_ACCURACY = LocationPowerSetting.LOCATION_HIGH_ACCURACY_SETTINGS;

    private final Activity mActivity;
    private final FusedLocationProviderClient mFusedLocationProviderClient;

    private boolean mRequestCancelledByUser;
    private boolean mRequestingLocationSettings;

    public LocationManager(Activity activity) {
        this.mActivity = activity;
        this.mFusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(mActivity);
    }

    @SuppressLint("MissingPermission")
    public Task<Location> getLastKnownGoodLocation() {
        if (PermissionManager.with(mActivity).checkLocationPermissions(REQ_PERMISSIONS_LOCATION_LAST_KNOWN))
            return mFusedLocationProviderClient.getLastLocation()
                    .addOnSuccessListener(this).addOnFailureListener(this);
        return null;
    }

    public Task<LocationSettingsResponse> prepareLocationUpdates() {
        return prepareLocationUpdates(LOCATION_ACCURACY);
    }

    public Task<LocationSettingsResponse> prepareLocationUpdates(LocationPowerSetting powerSetting) {
        LocationSettingsRequest settingsRequest = new LocationSettingsRequest.Builder()
                .addLocationRequest(prepareLocationRequest(powerSetting)).build();
        SettingsClient settingsClient = LocationServices.getSettingsClient(mActivity);
        return settingsClient.checkLocationSettings(settingsRequest).addOnFailureListener(this)
                .addOnSuccessListener(new OnSuccessListener<LocationSettingsResponse>() {
                    @Override
                    public void onSuccess(LocationSettingsResponse locationSettingsResponse) {
                        startTrackingLocation();
                    }
                });
    }

    private LocationRequest prepareLocationRequest(LocationPowerSetting locationPowerSetting) {

        // Initialize request status
        mRequestCancelledByUser = false;

        // Create Location Request
        return LocationRequest.create()
                .setInterval(BuildConfig.locationUpdateInterval)
                .setFastestInterval(BuildConfig.locationFastestUpdateInterval)
                .setPriority((LOCATION_ACCURACY = locationPowerSetting).priority);
    }

    @Override
    public void onSuccess(Location location) {
        if (mActivity instanceof Listener)
            ((Listener) mActivity).onFetchedLocation(location);
    }

    @Override
    public void onFailure(@NonNull Exception ex) {

        String displayMessage = null;

        if (ex instanceof ApiException) {
            int statusCode = ((ApiException) ex).getStatusCode();
            if (!mRequestingLocationSettings && statusCode == LocationSettingsStatusCodes.RESOLUTION_REQUIRED) {
                if (ex instanceof ResolvableApiException) try {

                    ((ResolvableApiException) ex).startResolutionForResult(mActivity, LOCATION_ACCURACY.settingRequestCode);

                    // Set Status: Asking Location Update Settings
                    mRequestingLocationSettings = true;

                } catch (IntentSender.SendIntentException intentEx) {
                    intentEx.printStackTrace();
                    displayMessage = intentEx.getLocalizedMessage();
                }
                else displayMessage = ex.getLocalizedMessage();
            }
        }

        if (!TextUtils.isEmpty(displayMessage))
            Toast.makeText(mActivity, displayMessage, Toast.LENGTH_SHORT).show();
    }

    public boolean onLocationSettingsResult(int requestCode, int resultCode) {

        // Set Status: Done asking Location Update Settings
        mRequestingLocationSettings = false;

        boolean consumable = LocationPowerSetting.containsRequestCode(requestCode);

        if (consumable) if (resultCode == Activity.RESULT_CANCELED) {

            mRequestCancelledByUser = true;

            // Stop any ongoing tracking
            stopTrackingLocation();

            // Show user cancellation message
            Toast.makeText(mActivity, R.string.msg_location_detection_cancelled, Toast.LENGTH_LONG).show();

//      Successive PowerSettings.
//            LocationPowerSetting nextPowerSetting = LocationPowerSetting.getNextPowerSetting(requestCode);
//            if (nextPowerSetting == null)
//                Toast.makeText(mActivity, R.string.msg_location_detection_cancelled_by_user, Toast.LENGTH_SHORT).show();
//            else prepareLocationUpdates(nextPowerSetting);

        } else if (resultCode == Activity.RESULT_OK) startTrackingLocation();

        return consumable;
    }

    public void stopTrackingLocation() {
        if (mFusedLocationProviderClient != null)
            mFusedLocationProviderClient.removeLocationUpdates(this);
    }

    @SuppressLint("MissingPermission")
    private void startTrackingLocation() {
        boolean isLocationPermissionGranted = PermissionManager.with(mActivity)
                .checkLocationPermissions(REQ_PERMISSIONS_LOCATION_UPDATE);

        if (mFusedLocationProviderClient != null && isLocationPermissionGranted)
            mFusedLocationProviderClient.requestLocationUpdates(prepareLocationRequest(LOCATION_ACCURACY), this, null);
    }

    @Override
    public void onLocationResult(LocationResult locationResult) {
        Location lastLocation = locationResult.getLastLocation();

        if (mActivity instanceof Listener)
            ((Listener) this.mActivity).onFetchedLocation(lastLocation);
        else
            Log.e("LocationManager", mActivity.getString(R.string.msg_activity_no_location_subscription));

        if (lastLocation != null) {
            LATITUDE = lastLocation.getLatitude();
            LONGITUDE = lastLocation.getLongitude();
        }
    }

    public void onRequestPermissionsResult(int requestCode) {
        if (requestCode == LocationConstants.REQ_PERMISSIONS_LOCATION_LAST_KNOWN)
            getLastKnownGoodLocation();
        else if (requestCode == LocationConstants.REQ_PERMISSIONS_LOCATION_UPDATE)
            startTrackingLocation();
    }

    public boolean isRequestingLocationSettings() {
        return mRequestingLocationSettings;
    }

    public boolean isRequestCancelledByUser() {
        return mRequestCancelledByUser;
    }

    public interface Listener {

        void onFetchedLocation(Location recentLocation);
    }
}
