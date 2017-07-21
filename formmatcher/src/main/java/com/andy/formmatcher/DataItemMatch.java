package com.andy.formmatcher;

/**
 * Created by Andy on 2017/7/21.
 */

public class DataItemMatch<T> {
    private T value;
    private int regularValue;
    private String toastString;
    private String postKey;
    private boolean dataChanged;

    public DataItemMatch(T value, int regularValue, String toastString, String postKey) {
        this.value = value;
        this.regularValue = regularValue;
        this.toastString = toastString;
        this.postKey = postKey;
    }
}
