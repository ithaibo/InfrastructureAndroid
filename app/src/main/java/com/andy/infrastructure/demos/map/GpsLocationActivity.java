package com.andy.infrastructure.demos.map;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.andy.baselibrary.activity.DataBindActivity;
import com.andy.baselibrary.utils.WGS84GCJ02BD09Util;
import com.andy.infrastructure.R;
import com.andy.infrastructure.databinding.ActGpsBinding;

/**
 * Created by Andy on 2017/9/11.
 */

public class GpsLocationActivity extends DataBindActivity<ActGpsBinding> {

    private LocationManager lm;

    @Override
    protected int getLayoutId() {
        return R.layout.act_gps;
    }

    @Override
    protected void initData() {
        mDataBind.setClicker(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                requestLocation();
            }
        });
    }

    private void requestLocation() {
        requestSomePersimission(101, Manifest.permission.ACCESS_FINE_LOCATION);

    }

    private void requestSomePersimission(final int requestCode, final String permissonTag) {
        // here, check the permission is granted or not

        if (ContextCompat.checkSelfPermission(mContext,
                permissonTag)!=
                PackageManager.PERMISSION_GRANTED
                ) {
            // request permission
            ActivityCompat.requestPermissions(this,
                    new String[]{permissonTag},
                    requestCode);
        } else {
            // do something
            if (requestCode == 101) {
                gpsLocate();
            }
        }

    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if (requestCode == 101) {
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                gpsLocate();
            } else {
                Toast.makeText(mContext, "You are not granted to call anyone.", Toast.LENGTH_SHORT).show();
            }
            return;
        }

        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }

    private void gpsLocate() {
        if (lm == null) {
            lm = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        }

        try {
            if (lm.isProviderEnabled(LocationManager.GPS_PROVIDER)) {
                lm.requestLocationUpdates(LocationManager.GPS_PROVIDER, 10 * 1000, 10, gpsListener);
                Location location = lm.getLastKnownLocation(LocationManager.GPS_PROVIDER);
                if (location != null) {
                    showToast("gps location: " + location.toString());
                }
            }

            if (lm.isProviderEnabled(LocationManager.NETWORK_PROVIDER)) {
                lm.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 10 * 1000, 10, networkListener);
                Location location = lm.getLastKnownLocation(LocationManager.NETWORK_PROVIDER);
                if (location != null) {
                    showToast("network location: " + location.toString());
                }
            }
        } catch (SecurityException e) {}

    }

    private LocationListener gpsListener = new LocationListener() {
        @Override
        public void onLocationChanged(Location location) {
            Log.i("GpsLocationActivity", "gps location: " + location.toString());
            showToast("location: " + location.toString());
            double[] lonLat = WGS84GCJ02BD09Util.wgs84togcj02(location.getLongitude(), location.getLatitude());
            location.setLongitude(lonLat[0]);
            location.setLatitude(lonLat[1]);
            mDataBind.setLatlon("gps, " + location);
        }

        @Override
        public void onStatusChanged(String provider, int status, Bundle extras) {

        }

        @Override
        public void onProviderEnabled(String provider) {

        }

        @Override
        public void onProviderDisabled(String provider) {

        }
    };

    private LocationListener networkListener = new LocationListener() {
        @Override
        public void onLocationChanged(Location location) {
            Log.i("GpsLocationActivity", "network location: " + location.toString());
            showToast("network location: " + location.toString());
            double[] lonLat = WGS84GCJ02BD09Util.wgs84togcj02(location.getLongitude(), location.getLatitude());
            location.setLongitude(lonLat[0]);
            location.setLatitude(lonLat[1]);
            mDataBind.setLatlon("network, " + location);
        }

        @Override
        public void onStatusChanged(String provider, int status, Bundle extras) {

        }

        @Override
        public void onProviderEnabled(String provider) {

        }

        @Override
        public void onProviderDisabled(String provider) {

        }
    };

    @Override
    protected void initViews() {

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        lm.removeUpdates(this.gpsListener);
    }
}
