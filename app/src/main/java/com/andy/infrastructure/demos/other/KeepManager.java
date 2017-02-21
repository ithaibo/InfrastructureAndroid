package com.andy.infrastructure.demos.other;

import android.content.Context;
import android.content.Intent;

/**
 * Created by Andy on 2017/2/20.
 */

public class KeepManager {
    private KeepAliveActivity liveActivity;
    private KeepLiveService liveService;

    public static class Instance {
        private static final KeepManager ISNTANCE = new KeepManager();
    }

    private KeepManager(){}

    public static final KeepManager getInstance() {
        return Instance.ISNTANCE;
    }

    public void setLiveActivity(KeepAliveActivity liveActivity) {
        this.liveActivity = liveActivity;
    }

    public void finishLiveActivity() {
        if (liveActivity !=null) {
            this.liveActivity.killSelf();
        }
    }

    public void startLiveActivity(Context context) {
        Intent intent = new Intent(context, KeepAliveActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

        context.getApplicationContext().startActivity(intent);
    }

    public void startLiveService(Context context) {
        Intent intent = new Intent(context, KeepLiveService.class);
        context.getApplicationContext().startService(intent);
    }

    public void setLiveService(KeepLiveService liveService) {
        this.liveService = liveService;
    }

    public void stopService() {
        if (liveService !=null) {
            liveService.stopSelf();
            liveService = null;
        }
    }
}
