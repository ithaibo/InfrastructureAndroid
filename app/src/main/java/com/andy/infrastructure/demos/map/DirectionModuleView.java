package com.andy.infrastructure.demos.map;

import android.databinding.ObservableDouble;
import android.databinding.ObservableField;

/**
 * Created by Andy on 2017/7/24.
 */

public class DirectionModuleView {
    private final ObservableField<String> originAddress = new ObservableField<>("");
    private final ObservableField<String> targetAddress = new ObservableField<>("");

    public final ObservableDouble originLongitude = new ObservableDouble(0);
    public final ObservableDouble originLatitude = new ObservableDouble(0);
    public final ObservableDouble targetLongitude = new ObservableDouble(0);
    public final ObservableDouble targetLatitude = new ObservableDouble(0);

    public String getOriginAddress() {
        return originAddress.get();
    }

    public String  getTargetAddress() {
        return targetAddress.get();
    }

    public void setOriginAddress(String address) {
        this.originAddress.set(address);
    }

    public void setTargetAddress(String address) {
        this.targetAddress.set(address);
    }
}
