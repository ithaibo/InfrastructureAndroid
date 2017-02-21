package com.andy.infrastructure.demos.other;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.Window;
import android.view.WindowManager;

import com.andy.baselibrary.utils.LogUtil;

/**
 * Created by Andy on 2017/2/20.
 */

public class KeepAliveActivity extends Activity {
    private static final String TAG = "KeepAlive";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        LogUtil.i(TAG, "KeepAliveActivity -> onCreate");

        Window window = getWindow();
        window.setGravity(Gravity.LEFT | Gravity.TOP);
        WindowManager.LayoutParams params = window.getAttributes();
        params.x = 0;
        params.y = 0;
        params.height = 1;
        params.width = 1;
        window.setAttributes(params);

        KeepManager.getInstance().setLiveActivity(this);

    }

    @Override
    protected void onResume() {
        super.onResume();
        LogUtil.i("Keep Activity已启动");
    }

    public void killSelf() {
        Log.i(TAG, "KeepAliveActivity -> kill self");
        finish();
    }

    @Override
    protected void onDestroy() {
        LogUtil.i("Keep Activity已停止");
        super.onDestroy();
    }
}
