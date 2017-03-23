package com.andy.infrastructure.presenter;

import android.databinding.BaseObservable;
import android.databinding.Bindable;

import com.andy.infrastructure.BR;
import com.andy.infrastructure.bean.PingViewStatusBean;

import java.io.Serializable;

/**
 * Created by Andy on 2017/3/21.
 */

public class NetCheckData extends BaseObservable implements Serializable {
    @Bindable
    PingViewStatusBean pingStatueData;
    @Bindable
    PingViewStatusBean intAddrStatueData;

    public PingViewStatusBean getPingStatueData() {
        return pingStatueData;
    }

    public void setPingStatueData(PingViewStatusBean pingStatueData) {
        this.pingStatueData = pingStatueData;
        notifyPropertyChanged(BR.pingStatueData);
    }

    public PingViewStatusBean getIntAddrStatueData() {
        return intAddrStatueData;
    }

    public void setIntAddrStatueData(PingViewStatusBean intAddrStatueData) {
        this.intAddrStatueData = intAddrStatueData;
        notifyPropertyChanged(BR.intAddrStatueData);
    }
}
