package com.andy.infrastructure.demos.float_window;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import com.andy.baselibrary.utils.LogUtil;

/**
 * Created by Andy on 2017/3/3.
 */

public class ScreenShotReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        if ("com.andy.SCREENSHOT".equals(intent.getAction())) {
            LogUtil.i("收到截屏请求...");
        }
    }
}
