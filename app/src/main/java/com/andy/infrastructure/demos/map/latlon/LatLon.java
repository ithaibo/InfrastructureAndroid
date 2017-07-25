package com.andy.infrastructure.demos.map.latlon;

/**
 * Created by Andy on 2017/7/25.
 */

public class LatLon {
    private double longitude;
    private double latitude;
    private int codeType;

    public LatLon(double longitude, double latitude, int codeType) {
        this.longitude = longitude;
        this.latitude = latitude;
        this.codeType = codeType;
    }

    public LatLon() {
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public int getCodeType() {
        return codeType;
    }

    public void setCodeType(int codeType) {
        this.codeType = codeType;
    }

    public interface PointType {
        int CODE_BD09 = 1;
        int CODE_GCJ02 = 2;
    }
}
