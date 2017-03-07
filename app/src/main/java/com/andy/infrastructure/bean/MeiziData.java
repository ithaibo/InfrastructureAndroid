package com.andy.infrastructure.bean;

import android.databinding.BaseObservable;
import android.databinding.Bindable;

import com.andy.infrastructure.BR;

import java.io.Serializable;

/**
 * Created by Andy on 2017/3/7.
 */

public class MeiziData extends BaseObservable implements Serializable {
    public String url;
    public String type;

    @Bindable
    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
        notifyPropertyChanged(BR.meizi);
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
        notifyPropertyChanged(BR.meizi);
    }

    @Override
    public String toString() {
        return "MeiziData{" +
                "url='" + url + '\'' +
                ", type='" + type + '\'' +
                '}';
    }
}
