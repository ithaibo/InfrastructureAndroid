package com.andy.infrastructure.demos.other;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import com.andy.baselibrary.utils.LogUtil;

/**
 * Created by Andy on 2017/2/20.
 */

public class KeepLiveReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        String action = intent.getAction();
        if (action.equals(Intent.ACTION_SCREEN_OFF)) {
            LogUtil.i("用户已锁屏");
            KeepManager.getInstance().startLiveActivity(context);
//            KeepManager.getInstance().stopService();
        } else if (action.equals(Intent.ACTION_SCREEN_ON)) {
            LogUtil.i("用户已解锁");
            KeepManager.getInstance().finishLiveActivity();
            KeepManager.getInstance().startLiveService(context);
        }
    }
}
