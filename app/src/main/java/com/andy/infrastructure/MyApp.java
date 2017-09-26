package com.andy.infrastructure;

import android.app.Application;
import android.os.Messenger;
import android.support.multidex.MultiDex;

import com.andy.infrastructure.demos.db.note.DaoMaster;
import com.andy.infrastructure.demos.db.note.DaoSession;

import org.greenrobot.greendao.database.Database;

/**
 * Created by Andy on 2017/1/4.
 */

public class MyApp extends Application {
    public static final boolean DB_ENCRYPED = true;
    private DaoSession daoSession;

    @Override
    public void onCreate() {
        super.onCreate();
        MultiDex.install(this);

        initSQLite();
    }

    private void initSQLite() {
        DaoMaster.DevOpenHelper dbHelper = new DaoMaster.DevOpenHelper(this, "user");
        Database db = dbHelper.getReadableDb();
        daoSession = new DaoMaster(db).newSession();
    }

    public DaoSession getDaoSession() {
        return daoSession;
    }
}