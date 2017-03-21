package com.andy.infrastructure.presenter;

import android.databinding.BaseObservable;
import android.databinding.Bindable;
import android.databinding.ObservableField;
import android.view.View;

import com.andy.infrastructure.BR;

import java.io.Serializable;

/**
 * Created by Andy on 2017/3/21.
 */

public class NetCheckData extends BaseObservable implements Serializable {
    @Bindable
    private int showPingPb;
    @Bindable
    private int showIntAPb;
    @Bindable
    private String pingTimeCost;
    @Bindable
    private String intATimeCost;

    public int getShowPingPb() {
        return showPingPb;
    }

    public void setShowPingPb(int showPingPb) {
        this.showPingPb = showPingPb;
        notifyPropertyChanged(BR.netCheckData);
    }

    public int getShowIntAPb() {
        return showIntAPb;
    }

    public void setShowIntAPb(int showIntAPb) {
        this.showIntAPb = showIntAPb;
        notifyPropertyChanged(BR.netCheckData);
    }

    public String getPingTimeCost() {
        return pingTimeCost;
    }

    public void setPingTimeCost(String pingTimeCost) {
        this.pingTimeCost = pingTimeCost;
        notifyPropertyChanged(BR.netCheckData);
    }

    public String getIntATimeCost() {
        return intATimeCost;
    }

    public void setIntATimeCost(String intATimeCost) {
        this.intATimeCost = intATimeCost;
        notifyPropertyChanged(BR.netCheckData);
    }
}
