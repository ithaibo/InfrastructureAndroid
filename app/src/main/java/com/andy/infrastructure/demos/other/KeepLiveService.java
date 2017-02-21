package com.andy.infrastructure.demos.other;


import android.app.Service;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.IBinder;
import android.support.annotation.Nullable;

import com.andy.baselibrary.utils.LogUtil;

/**
 * Created by Andy on 2017/2/21.
 */

public class KeepLiveService extends Service {
    private KeepLiveReceiver receiver;

    @Override
    public void onCreate() {
        super.onCreate();
        LogUtil.i("Keep Service已创建");
        receiver = new KeepLiveReceiver();
        IntentFilter intentFilter = new IntentFilter("android.intent.action.USER_PRESENT");
        intentFilter.addAction(Intent.ACTION_SCREEN_OFF);
        intentFilter.addAction(Intent.ACTION_SCREEN_ON);
        registerReceiver(receiver, intentFilter);
        KeepManager.getInstance().setLiveService(this);
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        LogUtil.i("Keep Service已启动");
        return START_STICKY;
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onDestroy() {
        LogUtil.i("Keep Service已停止");
        if (receiver != null) {
            unregisterReceiver(receiver);
        }
        super.onDestroy();
    }
}
