package com.andy.rendercontroller;

/**
 * Created by Andy on 2017/7/26.
 */

public interface DataLoaderListener<T> {
    void onSuccess(T data);
    void onFail();
}
