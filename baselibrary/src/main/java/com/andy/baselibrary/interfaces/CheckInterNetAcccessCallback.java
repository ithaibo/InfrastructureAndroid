package com.andy.baselibrary.interfaces;

import java.io.Serializable;

/**
 * Created by Andy on 2017/3/21.
 */

public interface CheckInterNetAcccessCallback<T> extends Serializable {
    void onCallback(T data);
}
