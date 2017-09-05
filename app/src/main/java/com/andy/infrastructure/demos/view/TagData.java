package com.andy.infrastructure.demos.view;

import android.databinding.ObservableBoolean;

/**
 * Created by Andy on 2017/9/5.
 */

public class TagData<T> {
    private T data;
    final public ObservableBoolean selected = new ObservableBoolean(false);

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "TagData{" +
                "data=" + data +
                ", selected=" + selected.get() +
                '}';
    }
}
