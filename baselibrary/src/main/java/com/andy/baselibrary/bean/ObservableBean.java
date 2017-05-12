package com.andy.baselibrary.bean;

import android.databinding.BaseObservable;

import java.io.Serializable;

/**
 * Created by Andy on 2017/4/28.
 */

public class ObservableBean extends BaseObservable implements Serializable {
    private int code;
    private String msg;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
