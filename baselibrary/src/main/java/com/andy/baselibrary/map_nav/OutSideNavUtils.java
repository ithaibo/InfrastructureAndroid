package com.andy.baselibrary.map_nav;

import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.text.TextUtils;
import android.widget.Toast;


import com.andy.baselibrary.BuildConfig;
import com.andy.baselibrary.utils.AppUtils;
import com.andy.baselibrary.utils.CommonUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Andy on 2017/7/25.
 */

public class OutSideNavUtils {
    private Context mContext;
    private DirectionBean channelPicked;

    public OutSideNavUtils(Context context) {
        this.mContext = context;

        List<DirectionBean> navDataList = new ArrayList<>();
        DirectionBean amapData = new DirectionBean("com.autonavi.minimap", 0);
        DirectionBean bdmapData = new DirectionBean("com.baidu.BaiduMap", 1);
        navDataList.add(amapData);
        navDataList.add(bdmapData);

        checkChannelAppInstalled(navDataList);
        pickOneNavChannel(navDataList);
    }

    private void checkChannelAppInstalled(List<DirectionBean> navDataList) {
        if (CommonUtils.isListEmpty(navDataList)){
            return;
        }

        for (DirectionBean direction : navDataList) {
            try {
                PackageInfo packageInfo = mContext.getPackageManager().getPackageInfo(direction.getPackageData().getPackageName(),
                        PackageManager.GET_ACTIVITIES);
                if (packageInfo!=null) {
                    direction.getPackageData().setInstalled(AppUtils.isPackageInstalled(direction.getPackageData().getPackageName(), mContext));
                    direction.getPackageData().setLabel(mContext.getPackageManager()
                            .getApplicationLabel(mContext.getPackageManager()
                                    .getApplicationInfo(direction.getPackageData().getPackageName(), 0))
                            .toString());
                }
            } catch (PackageManager.NameNotFoundException e) {
                e.printStackTrace();
            }
        }
    }

    private void pickOneNavChannel(List<DirectionBean> channelList) {
        if (CommonUtils.isListEmpty(channelList)) {
            return;
        }

        for (int i=0; i<channelList.size(); i++) {
            try {
                if (channelList.get(i).getPackageData().isInstalled()) {
                    channelPicked = channelList.get(i);
                    break;
                }
            } catch (RuntimeException e) {
            }
        }
    }

    private void toPickedChannelNav(DirectionBean navDataList) {
        Intent intent = null;
        switch (navDataList.getPriority()) {
            case 0:
                intent = new Intent();
                intent.addCategory("android.intent.category.DEFAULT");
                intent.setAction("android.intent.action.VIEW");
                intent.setData(android.net.Uri.parse(
                        new MapDataBuilder("amapuri://route/plan/")
                                .addParamter("sourceApplication", BuildConfig.APPLICATION_ID)
                                .addParamter("dname", navDataList.getdAddress())
                                .addParamter("dlon", navDataList.getdPoint().getLongitude()+"")
                                .addParamter("dlat", navDataList.getdPoint().getLatitude()+"")
                                .addParamter("t", 3+"")     //默认 骑行模式
                                .build()));
                intent.setPackage(navDataList.getPackageData().getPackageName());
                break;
            case 1:
                intent = new Intent();
                intent.setAction("android.intent.action.VIEW");

                intent.setData(android.net.Uri.parse(
                        "baidumap://map/direction?"+
                                "destination=name:"+
                                navDataList.getdAddress()+
                                "|latlng:"+navDataList.getdPoint().getLatitude()+
                                ","+navDataList.getdPoint().getLongitude()+
                                "&mode=riding"      //默认 骑行模式
                ));
                intent.setPackage(navDataList.getPackageData().getPackageName());
                break;
        }

        if (navDataList.getPackageData().isInstalled()) {
            if (intent !=null) {
                try {
                    mContext.startActivity(intent);
                } catch (ActivityNotFoundException e) {
                    e.printStackTrace();
                }
            }
        } else {
            Toast.makeText(mContext, "请先安装" + navDataList.getPackageData().getLabel() + "APP", Toast.LENGTH_LONG).show();
        }
    }

    public void onNavOutSide(Double destinationLongitude, Double destinationLatitude, String destinationAddress) {
        if (channelPicked==null) {
            Toast.makeText(mContext, "请先安装高德地图或百度地图APP", Toast.LENGTH_LONG).show();
            return;
        }
        
        if (channelPicked.getdPoint()==null) {
            channelPicked.setdPoint(new LatLon());
        }

        if (TextUtils.equals(channelPicked.getPackageData().getPackageName(), "com.baidu.BaiduMap")) {
            double[] converted = GCJ02BD09Adapter.GCJ02ToBD09(destinationLongitude, destinationLatitude);
            channelPicked.getdPoint().setLongitude(converted[0]);
            channelPicked.getdPoint().setLatitude(converted[1]);
        } else {
            channelPicked.getdPoint().setLongitude(destinationLongitude);
            channelPicked.getdPoint().setLatitude(destinationLatitude);
        }
        channelPicked.setdAddress(destinationAddress);
        toPickedChannelNav(channelPicked);
    }

    public void onNavOutSide(Double destinationLongitude, Double destinationLatitude, String destinationAddress,
                             Double originLongitude, Double originLatitude, String originAddress) {
        if (channelPicked==null) {
            Toast.makeText(mContext, "请先安装高德地图或百度地图APP", Toast.LENGTH_LONG).show();
            return;
        }
        if (TextUtils.equals(channelPicked.getPackageData().getPackageName(), "com.baidu.BaiduMap")) {
            double[] converted = GCJ02BD09Adapter.GCJ02ToBD09(destinationLongitude, destinationLatitude);
            channelPicked.getdPoint().setLongitude(converted[0]);
            channelPicked.getdPoint().setLatitude(converted[1]);
        } else {
            channelPicked.getdPoint().setLongitude(destinationLongitude);
            channelPicked.getdPoint().setLatitude(destinationLatitude);
        }
        channelPicked.setdAddress(destinationAddress);
        toPickedChannelNav(channelPicked);
    }
}
