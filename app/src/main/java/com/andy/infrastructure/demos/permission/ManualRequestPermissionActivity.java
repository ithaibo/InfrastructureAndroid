package com.andy.infrastructure.demos.permission;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.telephony.SmsManager;
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
    private static final int MY_PERMISSIONS_REQUEST_SEND_SMS = 11;

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

    @OnClick({R.id.btn_request_dial_act_manule,
    R.id.btn_send_sms_act_permission})
    void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_request_dial_act_manule:
                requestSomePersimission(MY_PERMISSIONS_REQUEST_CALL_PHONE, Manifest.permission.CALL_PHONE);
                break;
            case R.id.btn_send_sms_act_permission:
                requestSomePersimission(MY_PERMISSIONS_REQUEST_SEND_SMS, Manifest.permission.SEND_SMS);
                break;
        }
    }

    private void sendSms(String targetNumber, String smsBody) {
        SmsManager.getDefault().sendTextMessage(targetNumber, null, smsBody, null, null);
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
            if (requestCode == MY_PERMISSIONS_REQUEST_CALL_PHONE) {
                callPhone("10086");
            }else if (requestCode == MY_PERMISSIONS_REQUEST_SEND_SMS) {
                sendSms("10086", "Hi, this is a test.");
            }
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
        } else if (requestCode == MY_PERMISSIONS_REQUEST_SEND_SMS) {
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                sendSms("10086", "Hi, this is a test.");
            } else {
                Toast.makeText(mContext, "You are not granted to call anyone.", Toast.LENGTH_SHORT).show();
            }
        }



        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }
}
