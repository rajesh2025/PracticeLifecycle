package com.airtel.agileafrica.locationprovider;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.pm.PackageManager;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AlertDialog;

import static com.airtel.agileafrica.locationprovider.LocationConstants.PERM_ACCESS_COARSE_LOCATION;
import static com.airtel.agileafrica.locationprovider.LocationConstants.PERM_ACCESS_FINE_LOCATION;

/**
 * Developer: Rishabh Dutt Sharma
 * <p/>
 * Organization: Airtel [A1UGPQS2]
 * Dated: 4/5/2018.
 */
public class PermissionManager {

    private final Activity activity;

    private PermissionManager(Activity mActivity) {
        this.activity = mActivity;
    }

    public static PermissionManager with(Activity activity) {
        return new PermissionManager(activity);
    }

    public boolean checkLocationPermissions(int permissionRequestCode) {

        if (isPermissionGranted(PERM_ACCESS_FINE_LOCATION)
                || isPermissionGranted(PERM_ACCESS_COARSE_LOCATION)) return true;

        if (shouldShowRequestPermissionRationale(PERM_ACCESS_FINE_LOCATION) ||
                shouldShowRequestPermissionRationale(PERM_ACCESS_COARSE_LOCATION))
            showRequestPermissionRationale(permissionRequestCode);

        else requestLocationPermissions(permissionRequestCode);

        return false;
    }

    private boolean isPermissionGranted(String permission) {
        return ActivityCompat.checkSelfPermission(activity, permission) == PackageManager.PERMISSION_GRANTED;
    }

    private boolean shouldShowRequestPermissionRationale(String permission) {
        return ActivityCompat.shouldShowRequestPermissionRationale(activity, permission);
    }

    private void showRequestPermissionRationale(final int permissionRequestCode) {
        new AlertDialog.Builder(activity)
                .setTitle(R.string.title_permission_required)
                .setMessage(R.string.msg_location_permission_required)
                .setPositiveButton(R.string.action_ok, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        requestLocationPermissions(permissionRequestCode);
                    }
                }).create().show();
    }

    private void requestLocationPermissions(int permissionRequestCode) {
        ActivityCompat.requestPermissions(activity, new String[]{
                PERM_ACCESS_FINE_LOCATION, PERM_ACCESS_COARSE_LOCATION
        }, permissionRequestCode);
    }
}
