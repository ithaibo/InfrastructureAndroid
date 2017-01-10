package com.andy.infrastructure;

import android.app.Application;
import android.os.Messenger;
import android.support.multidex.MultiDex;

/**
 * Created by Andy on 2017/1/4.
 */

public class MyApp extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        MultiDex.install(this);

    }
}