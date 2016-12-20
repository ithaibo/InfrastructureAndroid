package com.andy.infrastructure.bean;

import android.app.Activity;

/**
 * Created by Andy on 2016/12/20.
 */

public class DemoActivityBean extends Bean {
    private String name;
    private String desc;
    private Class<? extends Activity> className;

    public String getName() {
        return name;
    }

    public DemoActivityBean setName(String name) {
        this.name = name;
        return this;
    }

    public String getDesc() {
        return desc;
    }

    public DemoActivityBean setDesc(String desc) {
        this.desc = desc;
        return this;
    }

    public Class<? extends Activity> getClassName() {
        return className;
    }

    public DemoActivityBean setClassName(Class<? extends Activity> className) {
        this.className = className;
        return this;
    }
}
