package com.andy.baselibrary.map_nav;


/**
 * Created by Andy on 2017/7/24.
 */

public class DirectionBean {
    private PackageData packageData;

    private int priority;

    private String oAddress;
    private LatLon oPoint;

    private String dAddress;
    private LatLon dPoint;

    public DirectionBean(String packageName, int priority) {
        this.packageData = new PackageData(packageName);
        this.priority = priority;
    }

    public DirectionBean() {
    }

    public int getPriority() {
        return priority;
    }

    public void setPriority(int priority) {
        this.priority = priority;
    }

    public PackageData getPackageData() {
        return packageData;
    }

    public void setPackageData(PackageData packageData) {
        this.packageData = packageData;
    }

    public String getoAddress() {
        return oAddress;
    }

    public void setoAddress(String oAddress) {
        this.oAddress = oAddress;
    }

    public String getdAddress() {
        return dAddress;
    }

    public void setdAddress(String dAddress) {
        this.dAddress = dAddress;
    }

    public LatLon getoPoint() {
        return oPoint;
    }

    public void setoPoint(LatLon oPoint) {
        this.oPoint = oPoint;
    }

    public LatLon getdPoint() {
        return dPoint;
    }

    public void setdPoint(LatLon dPoint) {
        this.dPoint = dPoint;
    }
}
