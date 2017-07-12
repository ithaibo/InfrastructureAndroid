package com.andy.infrastructure.demos.view.official;

import android.databinding.BaseObservable;
import android.databinding.ObservableBoolean;
import android.databinding.ObservableField;

/**
 * Created by Andy on 2017/6/27.
 */

public class CustomTabModule {
    public final ObservableField<String> tabText = new ObservableField<>();
    public final ObservableBoolean selected = new ObservableBoolean(false);

    public ObservableField<String> getTabText() {
        return tabText;
    }

    public ObservableBoolean getSelected() {
        return selected;
    }
}
