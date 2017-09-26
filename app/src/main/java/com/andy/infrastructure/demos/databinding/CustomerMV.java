package com.andy.infrastructure.demos.databinding;

import android.databinding.BaseObservable;
import android.databinding.Bindable;
import android.databinding.ObservableField;


/**
 * Created by Andy on 2017/1/5.
 */

public class CustomerMV {

    public final ObservableField<String> mobile = new ObservableField<>("");
    public final ObservableField<String> name = new ObservableField<>("");
    public final ObservableField<String> email = new ObservableField<>("");
}
