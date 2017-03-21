package com.andy.infrastructure.bean;

import java.net.InetAddress;

/**
 * Created by Andy on 2017/3/21.
 */

public class IntAddrDataBean extends Bean {
    private long startTime;
    private long stopTime;
    private boolean isinternetAccess;
    private InetAddress inetAddress;

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

    public InetAddress getInetAddress() {
        return inetAddress;
    }

    public void setInetAddress(InetAddress inetAddress) {
        this.inetAddress = inetAddress;
    }

    @Override
    public String toString() {
        return "IntAddrDataBean{" +
                "startTime=" + startTime +
                ", stopTime=" + stopTime +
                ", isinternetAccess=" + isinternetAccess +
                ", inetAddress=" + inetAddress +
                '}';
    }
}
