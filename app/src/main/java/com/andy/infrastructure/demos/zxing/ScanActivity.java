package com.andy.infrastructure.demos.zxing;

import com.andy.infrastructure.R;
import com.journeyapps.barcodescanner.CaptureActivity;
import com.journeyapps.barcodescanner.DecoratedBarcodeView;

/**
 * Created by Andy on 2017/9/14.
 */

public class ScanActivity extends CaptureActivity {

    @Override
    protected DecoratedBarcodeView initializeContent() {
        setContentView(R.layout.act_scan);
        DecoratedBarcodeView decoratedBarcodeView = (DecoratedBarcodeView) findViewById(R.id.zxing_barcode_scanner);
        return decoratedBarcodeView;
    }


}
