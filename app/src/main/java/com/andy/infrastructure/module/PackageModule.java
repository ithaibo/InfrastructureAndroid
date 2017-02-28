package com.andy.infrastructure.module;

import android.databinding.BaseObservable;
import android.databinding.Bindable;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;


import java.lang.ref.WeakReference;

/**
 * Created by Andy on 2017/2/28.
 */

public class PackageModule extends BaseObservable {
    private String appName;
    private String apkName;
    private Drawable appIcon;

    @Bindable
    public String getAppName() {
        return appName;
    }

    public void setAppName(String appName) {
        this.appName = appName;
//        notifyPropertyChanged(BR.appName);
    }

    @Bindable
    public String getApkName() {
        return apkName;
    }

    public void setApkName(String apkName) {
        this.apkName = apkName;
//        notifyPropertyChanged(BR.apkName);
    }

    public Drawable getAppIcon() {
        return appIcon;
    }

    public void setAppIcon(Drawable appIcon) {
        this.appIcon = appIcon;
    }
}
