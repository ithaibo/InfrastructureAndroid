package com.andy.infrastructure.bean;

import android.databinding.BaseObservable;

import java.io.Serializable;

/**
 * Created by Andy on 2017/3/9.
 */

public class ObservableBean extends BaseObservable implements Serializable {
    private String code;
    private String msg;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    @Override
    public String toString() {
        return "ObservableBean{" +
                "code='" + code + '\'' +
                ", msg='" + msg + '\'' +
                '}';
    }
}
