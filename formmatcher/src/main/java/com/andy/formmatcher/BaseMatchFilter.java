package com.andy.formmatcher;

/**
 * Created by Andy on 2017/7/21.
 */

public abstract class BaseMatchFilter<T> {
    T tempData;
    private String message;

    public BaseMatchFilter(T tempData, String message) {
        this.tempData = tempData;
        this.message = message;
    }

    public BaseMatchFilter() {}

    abstract void onMatched(boolean value);

    public T getTempData() {
        return tempData;
    }

    public void setTempData(T tempData) {
        this.tempData = tempData;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
