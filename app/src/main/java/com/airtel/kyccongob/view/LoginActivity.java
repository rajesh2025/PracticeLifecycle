package com.airtel.kyccongob.view;

import android.Manifest;
import android.app.ProgressDialog;
import android.arch.lifecycle.Lifecycle;
import android.arch.lifecycle.LifecycleOwner;
import android.arch.lifecycle.LifecycleRegistry;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.airtel.agileafrica.locationprovider.LocationManager;
import com.airtel.kyccongob.R;
import com.airtel.kyccongob.ui.DeviceLocationObserver;
import com.airtel.kyccongob.utility.Constant;
import com.airtel.kyccongob.utility.KycEnums;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class LoginActivity extends AppCompatActivity implements LifecycleOwner, LocationManager.Listener{
    private DeviceLocationObserver locationObserver;
    private KycEnums.KYC_LOGIN_MODE kyc_login_mode;


    @BindView(R.id.edt_password)
    EditText edt_password;

    @BindView(R.id.edt_username_auuid)
    EditText edt_username_auuid;

    @BindView(R.id.edt_otp)
    EditText edt_otp;

    @BindView(R.id.btn_generate_otp)
    Button btn_generate_otp;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);
        locationObserver = new DeviceLocationObserver(this, getLifecycle());
        getLifecycle().addObserver(locationObserver);

    }

    @Override
    protected void onResume() {
        super.onResume();
        checkPermissions();


    }

    private void checkPermissions() {
        int result;
        List<String> listPermissionsNeeded = new ArrayList<>();
        for (String p : Constant.permissions) {
            result = ContextCompat.checkSelfPermission(this, p);
            if (result != PackageManager.PERMISSION_GRANTED) {
                listPermissionsNeeded.add(p);
            }
        }
        if (!listPermissionsNeeded.isEmpty()) {
            ActivityCompat.requestPermissions(this, listPermissionsNeeded.toArray(new String[listPermissionsNeeded.size()]), Constant.MULTIPLE_PERMISSIONS);
        }
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (locationObserver != null)
            locationObserver.onLocationSettingsResult(requestCode, resultCode);
    }



        @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
//        super.onRequestPermissionsResult(requestCode,permissions,grantResults);
        switch (requestCode) {
            case Constant.MULTIPLE_PERMISSIONS: {
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    if (locationObserver != null)
                        locationObserver.onRequestPermissionsResult(requestCode);

                }
            }
        }
    }

    @Override
    public void onFetchedLocation(Location recentLocation) {
//        Toast.makeText(this, recentLocation.getLatitude()+""+ recentLocation.getLatitude(), Toast.LENGTH_SHORT).show();
    }
}
