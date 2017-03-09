package com.andy.infrastructure.bean;


import android.databinding.ObservableField;

/**
 * Created by Andy on 2017/3/9.
 */

public class UserBean extends ObservableBean {
    public ObservableField<String> userName = new ObservableField<>();
    public ObservableField<String> password = new ObservableField<>();

}
