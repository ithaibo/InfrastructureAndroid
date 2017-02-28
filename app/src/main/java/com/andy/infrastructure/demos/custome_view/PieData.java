package com.andy.infrastructure.demos.custome_view;

import android.databinding.BaseObservable;
import android.databinding.Bindable;

import com.android.annotations.NonNull;
import com.andy.infrastructure.BR;

/**
 * Created by Andy on 2017/2/23.
 */

public class PieData extends BaseObservable {
    // 用户关心数据
    private String name;        // 名字
    private float value;        // 数值
    private float percentage;   // 百分比

    // 非用户关心数据
    private int color = 0;      // 颜色
    private float angle = 0;    // 角度

    public PieData(@NonNull String name, @NonNull float value) {
        this.name = name;
        this.value = value;
    }

    public int getColor() {
        return color;
    }

    public void setColor(int color) {
        this.color = color;
    }

    @Bindable
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
        notifyPropertyChanged(BR.pieData);
    }

    public float getAngle() {
        return angle;
    }

    public void setAngle(float angle) {
        this.angle = angle;
    }

    @Bindable
    public float getValue() {
        return value;
    }

    public void setValue(float value) {
        this.value = value;
        notifyPropertyChanged(BR.pieData);
    }

    public float getPercentage() {
        return percentage;
    }

    public void setPercentage(float percentage) {
        this.percentage = percentage;
    }
}
