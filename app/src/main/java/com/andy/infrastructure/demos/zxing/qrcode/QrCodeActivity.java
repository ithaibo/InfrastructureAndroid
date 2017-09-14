package com.andy.infrastructure.demos.zxing.qrcode;

import android.content.Intent;
import android.view.View;
import android.widget.Toast;

import com.andy.baselibrary.activity.DataBindActivity;
import com.andy.infrastructure.R;
import com.andy.infrastructure.databinding.ZxingActQrcodeBinding;
import com.andy.infrastructure.demos.zxing.ScanActivity;
import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;

/**
 * Created by Andy on 2017/9/14.
 */

public class QrCodeActivity extends DataBindActivity<ZxingActQrcodeBinding> {
    @Override
    protected int getLayoutId() {
        return R.layout.zxing_act_qrcode;
    }

    @Override
    protected void initData() {
        mDataBind.setClicker(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                doScan();
            }
        });
    }

    private void doScan() {
        IntentIntegrator scanIntegrator = new IntentIntegrator(this);
        scanIntegrator.setCameraId(0);//0-back, 1-front
        scanIntegrator.setOrientationLocked(false);
        scanIntegrator.setCaptureActivity(ScanActivity.class);
        scanIntegrator.initiateScan();
    }

    @Override
    protected void initViews() {

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        IntentResult scanResult = IntentIntegrator.parseActivityResult(requestCode, resultCode, data);
        if (scanResult != null) {
            //we have a result
            String scanContent = scanResult.getContents();
            mDataBind.setResult(scanContent);
        } else {
            Toast.makeText(getApplicationContext(), "No scan data received!", Toast.LENGTH_SHORT).show();
        }
    }
}
