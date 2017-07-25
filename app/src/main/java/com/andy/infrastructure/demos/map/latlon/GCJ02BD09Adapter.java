package com.andy.infrastructure.demos.map.latlon;


/**
 * Created by Andy on 2017/7/25.
 *
 * 高德地图---百度地图坐标转换工具
 */

public class GCJ02BD09Adapter {
    public static final double x_pi = 3.14159265358979324 * 3000.0 / 180.0;

    public static final double[] GCJ02ToBD09(double gc02Lon, double gc02Lat) {
        double[] bd09 = new double[2];
        double PI = 3.14159265358979324 * 3000.0 / 180.0;
        double x = gc02Lon, y = gc02Lat;
        double z = Math.sqrt(x * x + y * y) + 0.00002 * Math.sin(y * PI);
        double theta = Math.atan2(y, x) + 0.000003 * Math.cos(x * PI);
        bd09[0] = z * Math.cos(theta) + 0.0065;
        bd09[1] = z * Math.sin(theta) + 0.006;
        return bd09;
    }

    public static final double[] BD09ToGCJ02(double bd_lat, double bd_lon) {
        double[] gc02 = new double[2];
        double PI = 3.14159265358979324 * 3000.0 / 180.0;
        double x = bd_lon - 0.0065, y = bd_lat - 0.006;
        double z = Math.sqrt(x * x + y * y) - 0.00002 * Math.sin(y * PI);
        double theta = Math.atan2(y, x) - 0.000003 * Math.cos(x * PI);
        gc02[0] = z * Math.cos(theta);
        gc02[1] = z * Math.sin(theta);
        return gc02;
    }
}
