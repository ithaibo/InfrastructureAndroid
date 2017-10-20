package com.andy.infrastructure.demos.lifecycle.db;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;
import android.arch.persistence.room.TypeConverters;

import com.andy.infrastructure.demos.lifecycle.dao.ProductDao;
import com.andy.infrastructure.demos.lifecycle.entity.ProductEntity;

/**
 * Created by Andy on 2017/9/29.
 */

@Database(entities = {ProductEntity.class}, version = 1)
@TypeConverters(DateConverter.class)
public abstract class AppDatabase extends RoomDatabase {
    static final String DATABASE_NAME = "sample-product-db";

    public abstract ProductDao productDao();

}
