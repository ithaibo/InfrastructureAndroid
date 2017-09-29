package com.andy.infrastructure;

import android.support.multidex.MultiDexApplication;

import com.andy.infrastructure.demos.db.note.DaoMaster;
import com.andy.infrastructure.demos.db.note.DaoSession;

import org.greenrobot.greendao.database.Database;

/**
 * Created by Andy on 2017/1/4.
 */

public class MyApp extends MultiDexApplication {
    public static final boolean DB_ENCRYPED = true;
    private DaoSession daoSession;

    @Override
    public void onCreate() {
        super.onCreate();

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