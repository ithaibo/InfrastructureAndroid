package com.andy.infrastructure;

import android.app.Application;
import android.os.Messenger;
import android.support.multidex.MultiDex;

import com.andy.infrastructure.demos.db.note.DaoMaster;

/**
 * Created by Andy on 2017/1/4.
 */

public class MyApp extends Application {
    public static final boolean DB_ENCRYPED = true;

    @Override
    public void onCreate() {
        super.onCreate();
        MultiDex.install(this);

        initSQLite();
    }

    private void initSQLite() {
    }
}