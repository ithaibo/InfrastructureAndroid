package com.andy.infrastructure.demos.lifecycle;

import android.app.Application;
import android.arch.core.util.Function;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.Transformations;

import com.andy.infrastructure.demos.lifecycle.db.DataBaseCreator;
import com.andy.infrastructure.demos.lifecycle.entity.ProductEntity;

import java.util.List;

/**
 * Created by Andy on 2017/9/29.
 */

public class ProductListViewModule extends AndroidViewModel {
    private LiveData<List<ProductEntity>> mObservableProducts;
    private static final MutableLiveData ABSENT = new MutableLiveData();
    {
        //noinspection unchecked
        ABSENT.setValue(null);
    }


    public ProductListViewModule(Application application) {
        super(application);
        final DataBaseCreator dataBaseCreator = DataBaseCreator.getInstance(this.getApplication());
        dataBaseCreator.createDb(this.getApplication());
        final LiveData<Boolean> databaseCreated = dataBaseCreator.getmIsDataBaseCreated();
        mObservableProducts = Transformations.switchMap(databaseCreated, new Function<Boolean, LiveData<List<ProductEntity>>>() {
            @Override
            public LiveData<List<ProductEntity>> apply(Boolean input) {
                if (!Boolean.TRUE.equals(input)) {
                    return ABSENT;
                } else {
                    return dataBaseCreator.getAppDatabase().productDao().loadAllProducts();
                }
            }
        });
    }

    public LiveData<List<ProductEntity>> getProducts() {
        return mObservableProducts;
    }


}
