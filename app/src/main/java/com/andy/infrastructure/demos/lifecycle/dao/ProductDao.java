package com.andy.infrastructure.demos.lifecycle.dao;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;

import com.andy.infrastructure.demos.lifecycle.entity.ProductEntity;

import java.util.List;

/**
 * Created by Andy on 2017/9/29.
 */

@Dao
public interface ProductDao {
    @Query("SELECT * FROM products")
    LiveData<List<ProductEntity>> loadAllProducts();

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertAll(List<ProductEntity> products);

    @Query("select * from products where id = :productId")
    LiveData<ProductEntity> loadProduct(int productId);

    @Query("SELECT * FROM products where id = :productId")
    ProductEntity loadProductSync(int productId);
}
