package com.andy.baselibrary.bean;

import java.io.Serializable;
import java.net.InetAddress;

/**
 * Created by Andy on 2017/3/21.
 */

public class InternetCheckDataBean implements Serializable {
    private long startTime;
    private long stopTime;
    private boolean isinternetAccess;

    public long getStartTime() {
        return startTime;
    }

    public void setStartTime(long startTime) {
        this.startTime = startTime;
    }

    public long getStopTime() {
        return stopTime;
    }

    public void setStopTime(long stopTime) {
        this.stopTime = stopTime;
    }

    public boolean isinternetAccess() {
        return isinternetAccess;
    }

    public void setIsinternetAccess(boolean isinternetAccess) {
        this.isinternetAccess = isinternetAccess;
    }

    @Override
    public String toString() {
        return "IntAddrDataBean{" +
                "startTime=" + startTime +
                ", stopTime=" + stopTime +
                ", isinternetAccess=" + isinternetAccess +
                '}';
    }
}
