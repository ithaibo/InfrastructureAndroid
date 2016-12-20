package com.andy.infrastructure.bean;

/**
 * Created by Andy on 2016/12/20.
 */

public class JsonBean<T> extends Bean {
    private T data;

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
