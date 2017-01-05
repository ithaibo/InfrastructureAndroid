package com.andy.infrastructure.demos.databinding;

import android.databinding.BaseObservable;
import android.databinding.Bindable;

import com.andy.infrastructure.BR;

/**
 * Created by Andy on 2017/1/5.
 */

public class Customer extends BaseObservable {
    private String mobile;
    private String name;
    private String email;

    @Bindable
    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
        notifyPropertyChanged(BR.mobile);
    }

    @Bindable
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
        notifyPropertyChanged(BR.name);
    }

    @Bindable
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
        notifyPropertyChanged(BR.email);
    }
}
