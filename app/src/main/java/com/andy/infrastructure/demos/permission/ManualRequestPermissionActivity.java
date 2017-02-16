package com.andy.infrastructure.demos.permission;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.view.View;
import android.widget.Toast;

import com.andy.baselibrary.activity.BaseActivity;
import com.andy.infrastructure.R;

import butterknife.OnClick;

/**
 * Created by Andy on 2017/2/16.
 */

public class ManualRequestPermissionActivity extends BaseActivity {
    private static final int MY_PERMISSIONS_REQUEST_CALL_PHONE = 1;

    @Override
    protected int getLayoutId() {
        return R.layout.permission_act_manule_request;
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void initViews() {

    }

    @OnClick({R.id.btn_request_dial_act_manule})
    void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_request_dial_act_manule:
                requestDialPersimission();
                break;
        }
    }

    private void requestDialPersimission() {
        // here, check the permission is granted or not
        if (ContextCompat.checkSelfPermission(mContext,
                Manifest.permission.CALL_PHONE)!=
                PackageManager.PERMISSION_GRANTED
        ) {
            // request permission
            ActivityCompat.requestPermissions(this,
                    new String[]{Manifest.permission.CALL_PHONE},
                    MY_PERMISSIONS_REQUEST_CALL_PHONE);
        } else {
            // do something
            callPhone("10086");
        }

    }

    private void callPhone(String phoneNumber) {
        Intent intent = new Intent(Intent.ACTION_CALL);
        Uri data = Uri.parse("tel:" + phoneNumber);
        intent.setData(data);
        startActivity(intent);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if (requestCode == MY_PERMISSIONS_REQUEST_CALL_PHONE) {
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                callPhone("10086");
            } else {
                Toast.makeText(mContext, "You are not granted to call anyone.", Toast.LENGTH_SHORT).show();
            }
            return;
        }

        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }
}
