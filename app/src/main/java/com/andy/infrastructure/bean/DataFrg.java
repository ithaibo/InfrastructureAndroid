package com.andy.infrastructure.bean;

import android.databinding.BaseObservable;
import android.databinding.Bindable;



/**
 * Created by Andy on 2017/2/6.
 */

public class DataFrg extends BaseObservable {
    private String cbText;

    @Bindable
    public String getCbText() {
        return cbText;
    }

    public void setCbText(String cbText) {
        this.cbText = cbText;
        notifyChange();
    }
}
