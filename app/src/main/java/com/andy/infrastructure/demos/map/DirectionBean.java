package com.andy.infrastructure.demos.map;

/**
 * Created by Andy on 2017/7/24.
 */

public class DirectionBean {
    private String packageName;
    private String label;
    private int priority;
    private boolean isInstalled;

    private String oAddress;
    private double oLongitude;
    private double oLatitude;

    private String dAddress;
    private double dLongitude;
    private double dLatitude;

    public DirectionBean(String packageName, int priority) {
        this.packageName = packageName;
        this.priority = priority;
    }

    public String getPackageName() {
        return packageName;
    }

    public void setPackageName(String packageName) {
        this.packageName = packageName;
    }

    public int getPriority() {
        return priority;
    }

    public void setPriority(int priority) {
        this.priority = priority;
    }

    public boolean isInstalled() {
        return isInstalled;
    }

    public void setInstalled(boolean installed) {
        isInstalled = installed;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public String getoAddress() {
        return oAddress;
    }

    public void setoAddress(String oAddress) {
        this.oAddress = oAddress;
    }

    public double getoLongitude() {
        return oLongitude;
    }

    public void setoLongitude(double oLongitude) {
        this.oLongitude = oLongitude;
    }

    public double getoLatitude() {
        return oLatitude;
    }

    public void setoLatitude(double oLatitude) {
        this.oLatitude = oLatitude;
    }

    public String getdAddress() {
        return dAddress;
    }

    public void setdAddress(String dAddress) {
        this.dAddress = dAddress;
    }

    public double getdLongitude() {
        return dLongitude;
    }

    public void setdLongitude(double dLongitude) {
        this.dLongitude = dLongitude;
    }

    public double getdLatitude() {
        return dLatitude;
    }

    public void setdLatitude(double dLatitude) {
        this.dLatitude = dLatitude;
    }
}
