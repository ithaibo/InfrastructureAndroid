package com.andy.infrastructure.bean;

import android.databinding.Bindable;

/**
 * Created by Andy on 2017/3/22.
 */

public class PingViewStatusBean extends ObservableBean {
    @Bindable
    private int showPingPb;
    @Bindable
    private String pingTimeCost;

    public int getShowPingPb() {
        return showPingPb;
    }

    public void setShowPingPb(int showPingPb) {
        this.showPingPb = showPingPb;
        notifyChange();
    }

    public String getPingTimeCost() {
        return pingTimeCost;
    }

    public void setPingTimeCost(String pingTimeCost) {
        this.pingTimeCost = pingTimeCost;
        notifyChange();
    }
}
