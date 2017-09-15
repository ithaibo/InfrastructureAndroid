package com.andy.infrastructure.demos.zxing.qrcode;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.View;
import android.widget.ListView;
import android.widget.Toast;

import com.andy.baselibrary.adapter.BindListViewAdapter;
import com.andy.infrastructure.R;
import com.andy.infrastructure.databinding.ScanResultItemBinding;
import com.google.zxing.ResultPoint;
import com.google.zxing.client.android.BeepManager;
import com.journeyapps.barcodescanner.BarcodeCallback;
import com.journeyapps.barcodescanner.BarcodeResult;
import com.journeyapps.barcodescanner.DecoratedBarcodeView;

import java.util.LinkedList;
import java.util.List;

/**
 * This sample performs continuous scanning, displaying the barcode and source image whenever
 * a barcode is scanned.
 */
public class ContinuousCaptureActivity extends Activity {
    private static final String TAG = ContinuousCaptureActivity.class.getSimpleName();
    private DecoratedBarcodeView barcodeView;
    private BeepManager beepManager;
    private List<String> resultList = new LinkedList<>();

    private BarcodeCallback callback = new BarcodeCallback() {
        @Override
        public void barcodeResult(BarcodeResult result) {
            barcodeView.pause();
            if (result == null || TextUtils.isEmpty(result.getText())) {
                Toast.makeText(ContinuousCaptureActivity.this, "扫码失败，请重新扫码", Toast.LENGTH_SHORT).show();
            } else {
//                if (resultList.contains(result.getText())) {
//                    Toast.makeText(ContinuousCaptureActivity.this, "请勿重复扫码", Toast.LENGTH_SHORT).show();
//                } else {
                    Toast.makeText(ContinuousCaptureActivity.this, "扫码成功", Toast.LENGTH_SHORT).show();
                    beepManager.playBeepSoundAndVibrate();
                    //add result into list
                    resultList.add(result.getText());
//                    barcodeView.setStatusText(result.getText());
                    adapter.notifyDataSetChanged();
//                }

            }
            //防止扫码过快，频繁出现重复扫码
            scanHandler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    barcodeView.resume();
                }
            }, 1000);
        }

        @Override
        public void possibleResultPoints(List<ResultPoint> resultPoints) {}
    };
    private ScanResultAdapter adapter;
    private Handler scanHandler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.continuous_scan);

        scanHandler = new Handler();

        adapter = new ScanResultAdapter(this);
        adapter.refreshItems(resultList);
        ((ListView)findViewById(R.id.listResult)).setAdapter(adapter);

        barcodeView = (DecoratedBarcodeView) findViewById(R.id.barcode_scanner);
        barcodeView.decodeContinuous(callback);
        beepManager = new BeepManager(this);

    }

    @Override
    protected void onResume() {
        super.onResume();

        barcodeView.resume();
    }

    @Override
    protected void onPause() {
        super.onPause();

        barcodeView.pause();
    }

    public void pause(View view) {
        barcodeView.pause();
    }

    public void resume(View view) {
        barcodeView.resume();
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        return barcodeView.onKeyDown(keyCode, event) || super.onKeyDown(keyCode, event);
    }

    private class ScanResultAdapter extends BindListViewAdapter<String, ScanResultItemBinding>{
        public ScanResultAdapter(Context mContext) {
            super(mContext);
        }

        @Override
        protected int getItemlayout() {
            return R.layout.scan_result_item;
        }

        @Override
        protected void onBindView(ScanResultItemBinding itemBind, String data, int position) {
            itemBind.setItemResult(data);
        }
    }
}
