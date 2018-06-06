package com.airtel.kyccongob.utility;

import android.Manifest;

public class Constant {
    public static final int MULTIPLE_PERMISSIONS = 101;


    public static final String[] permissions = new String[]{
            Manifest.permission.READ_PHONE_STATE,
            Manifest.permission.CAMERA,
            Manifest.permission.WRITE_EXTERNAL_STORAGE,
            Manifest.permission.ACCESS_COARSE_LOCATION,
            Manifest.permission.ACCESS_FINE_LOCATION};

}
