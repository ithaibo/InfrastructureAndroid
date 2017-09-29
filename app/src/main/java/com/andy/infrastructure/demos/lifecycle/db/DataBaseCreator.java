package com.andy.infrastructure.demos.lifecycle.db;

import android.arch.lifecycle.MutableLiveData;
import android.arch.persistence.room.Room;
import android.content.Context;
import android.os.AsyncTask;

import com.andy.infrastructure.demos.lifecycle.Product;
import com.andy.infrastructure.demos.lifecycle.entity.ProductEntity;

import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;

/**
 * Created by Andy on 2017/9/29.
 */

public class DataBaseCreator {
    private static DataBaseCreator mInstance;
    public static synchronized DataBaseCreator getInstance(Context context) {
        if (mInstance == null) {
            synchronized (DataBaseCreator.class){
                if (mInstance == null) {
                    mInstance = new DataBaseCreator();
                }
            }
        }
        return mInstance;
    }

    private final MutableLiveData<Boolean> mIsDataBaseCreated = new MutableLiveData<>();
    private AppDatabase mDb;
    private final AtomicBoolean mInitializing = new AtomicBoolean(true);

    public MutableLiveData<Boolean> getmIsDataBaseCreated() {
        return mIsDataBaseCreated;
    }
    public void createDb(final Context context) {
        if (!mInitializing.compareAndSet(true, false)) {
            return;
        }

        mIsDataBaseCreated.setValue(false);
        new AsyncTask<Context, Void, Void>(){
            @Override
            protected Void doInBackground(Context... params) {
                Context context1 = params[0];
                context.deleteDatabase(AppDatabase.DATABASE_NAME);

                AppDatabase db = Room.databaseBuilder(context.getApplicationContext(),
                        AppDatabase.class,
                        AppDatabase.DATABASE_NAME)
                        .build();

                try{
                    Thread.sleep(3000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                mockTestData(db);

                mDb = db;
                return null;
            }

            @Override
            protected void onPostExecute(Void aVoid) {
                super.onPostExecute(aVoid);
                mIsDataBaseCreated.setValue(true);
            }
        }.execute(context.getApplicationContext());
    }

    public AppDatabase getAppDatabase() {
        return mDb;
    }

    private void mockTestData(AppDatabase db) {
        List<ProductEntity> testDataList = new LinkedList<>();
        for (int i=1; i<20; i++) {
            ProductEntity item = new ProductEntity();
            item.setName("item_"+i);
            item.setPrice(10+ i);
            item.setDescription("product, item " + i);
            item.setId(i);

            testDataList.add(item);
        }
        db.productDao().insertAll(testDataList);
    }
}
