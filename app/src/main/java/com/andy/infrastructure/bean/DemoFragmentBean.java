package com.andy.infrastructure.bean;

import android.support.v4.app.Fragment;

/**
 * Created by Andy on 2016/12/20.
 */

public class DemoFragmentBean extends Bean {
    private String name;
    private String desc;
    private Class<? extends Fragment> className;

    public String getName() {
        return name;
    }

    public DemoFragmentBean setName(String name) {
        this.name = name;
        return this;
    }

    public String getDesc() {
        return desc;
    }

    public DemoFragmentBean setDesc(String desc) {
        this.desc = desc;
        return this;
    }

    public Class<? extends Fragment> getClassName() {
        return className;
    }

    public DemoFragmentBean setClassName(Class<? extends Fragment> className) {
        this.className = className;
        return this;
    }
}
