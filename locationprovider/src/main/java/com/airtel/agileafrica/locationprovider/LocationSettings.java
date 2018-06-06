package com.airtel.agileafrica.locationprovider;

import android.app.Activity;
import android.content.IntentSender;
import android.support.annotation.NonNull;
import android.text.TextUtils;
import android.widget.Toast;

import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.common.api.ResolvableApiException;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.location.LocationSettingsRequest;
import com.google.android.gms.location.LocationSettingsResponse;
import com.google.android.gms.location.LocationSettingsStatusCodes;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;

/**
 * Developer: Rishabh Dutt Sharma
 * <p/>
 * Organization: Airtel [A1UGPQS2]
 * Dated: 4/10/2018.
 */
public class LocationSettings implements OnFailureListener, OnSuccessListener<LocationSettingsResponse> {

    private final String TAG = LocationSettings.class.getSimpleName();

    private final Activity mActivity;
    private final Listener mListener;

    private LocationPowerSetting mPowerSetting;

    private boolean mRequestingLocationSettings;

    public LocationSettings(Activity activity) {
        this(activity, (Listener) (activity instanceof Listener ? activity : null));
    }

    public LocationSettings(Activity activity, Listener listener) {
        this.mActivity = activity;
        this.mListener = listener;
    }

    public Task<LocationSettingsResponse> prepareLocationUpdates(LocationPowerSetting setting) {

        this.mPowerSetting = setting;

        LocationSettingsRequest settingsRequest = new LocationSettingsRequest.Builder()
                .addLocationRequest(setting.buildLocationRequest()).build();

        return LocationServices.getSettingsClient(mActivity)
                .checkLocationSettings(settingsRequest)
                .addOnFailureListener(this)
                .addOnSuccessListener(this);
    }

    public boolean onLocationSettingsResult(int requestCode, int resultCode) {

        // Check if the request was made from LocationSettings
        if (!LocationPowerSetting.containsRequestCode(requestCode)) return false;

        // Set Status: Done asking Location Update Settings
        mRequestingLocationSettings = false;

        // Check for considerable RESULT_CODE and notify Listener
        if (mListener != null) if (resultCode == Activity.RESULT_CANCELED)
            mListener.onLocationSettingsChanged(false);
        else if (resultCode == Activity.RESULT_OK)
            mListener.onLocationSettingsChanged(true);
        else mListener.onLocationSettingsError("Unknown result");

        return true;
    }

    @Override
    public void onFailure(@NonNull Exception ex) {
        String displayMessage = null;

        if (ex instanceof ApiException) {
            int statusCode = ((ApiException) ex).getStatusCode();
            if (!mRequestingLocationSettings && statusCode == LocationSettingsStatusCodes.RESOLUTION_REQUIRED) {
                if (ex instanceof ResolvableApiException) try {

                    ((ResolvableApiException) ex).startResolutionForResult(mActivity, mPowerSetting.settingRequestCode);

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

    @Override
    public void onSuccess(LocationSettingsResponse locationSettingsResponse) {

    }

    public interface Listener {

        void onLocationSettingsChanged(boolean enabled);

        void onLocationSettingsError(String message);
    }
}
