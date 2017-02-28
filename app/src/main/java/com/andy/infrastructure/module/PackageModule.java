package com.andy.infrastructure.module;

import android.databinding.BaseObservable;
import android.databinding.Bindable;
import android.graphics.Bitmap;

import java.lang.ref.WeakReference;

/**
 * Created by Andy on 2017/2/28.
 */

public class PackageModule extends BaseObservable {
    private String appName;
    private String apkName;
    private WeakReference<Bitmap> appIcon;

    @Bindable
    public String getAppName() {
        return appName;
    }

    public void setAppName(String appName) {
        this.appName = appName;
    }

    @Bindable
    public String getApkName() {
        return apkName;
    }

    public void setApkName(String apkName) {
        this.apkName = apkName;
    }

    @Bindable
    public WeakReference<Bitmap> getAppIcon() {
        return appIcon;
    }

    public void setAppIcon(WeakReference<Bitmap> appIcon) {
        this.appIcon = appIcon;
    }
}
