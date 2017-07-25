package com.andy.infrastructure.demos.map;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.text.TextUtils;
import android.widget.Toast;

import com.andy.baselibrary.activity.DataBindActivity;
import com.andy.baselibrary.utils.AppUtils;
import com.andy.baselibrary.utils.CommonUtils;
import com.andy.infrastructure.BuildConfig;
import com.andy.infrastructure.R;
import com.andy.infrastructure.databinding.MapDirectionBinding;
import com.andy.infrastructure.demos.map.latlon.GCJ02BD09Adapter;
import com.andy.infrastructure.demos.map.latlon.LatLon;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Created by Andy on 2017/7/24.
 */

public class ThirdMapDirection extends DataBindActivity<MapDirectionBinding> {

    private List<DirectionBean> directionList;

    @Override
    protected int getLayoutId() {
        return R.layout.map_direction;
    }

    @Override
    protected void initData() {

        directionList = new ArrayList<>();

        DirectionBean amapDirection = new DirectionBean("com.autonavi.minimap", 0);
        amapDirection.setdAddress("肇家浜路地铁站");
        amapDirection.setdPoint(new LatLon(31.199436, 121.450212, LatLon.PointType.CODE_GCJ02));

        directionList.add(amapDirection);

        DirectionBean dbMapDirection = new DirectionBean("com.baidu.BaiduMap", 1);
        dbMapDirection.setdAddress("肇家浜路地铁站");
        double[] lonLat = GCJ02BD09Adapter.GCJ02ToBD09(121.450212, 31.199436);
        dbMapDirection.setdPoint(new LatLon(lonLat[0], lonLat[1], LatLon.PointType.CODE_BD09));

        directionList.add(dbMapDirection);

        checkMapPackageInstalled(directionList);
    }

    private void checkMapPackageInstalled(List<DirectionBean> directionList) {
        if (CommonUtils.isListEmpty(directionList)){
            return;
        }

        for (DirectionBean direction : directionList) {
            try {
                PackageInfo packageInfo = getPackageManager().getPackageInfo(direction.getPackageData().getPackageName(),
                        PackageManager.GET_ACTIVITIES);
                if (packageInfo!=null) {
                    direction.getPackageData().setInstalled(AppUtils.isPackageInstalled(direction.getPackageData().getPackageName(), ThirdMapDirection.this));
                    direction.getPackageData().setLabel(getPackageManager()
                            .getApplicationLabel(getPackageManager()
                                    .getApplicationInfo(direction.getPackageData().getPackageName(), 0))
                            .toString());
                }
            } catch (PackageManager.NameNotFoundException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    protected void initViews() {
        mDataBind.setClickAction(this.simpleAction);
        mDataBind.setDirectionData(directionList.get(1));
    }

    private SimpleAction simpleAction = new SimpleAction() {
        @Override
        public void action(final DirectionBean direction) {

            navOutSide(direction);
        }
    };

    private void navOutSide(DirectionBean direction) {
        Intent intent = null;
        switch (direction.getPriority()) {
            case 0:
                intent = new Intent();
                intent.addCategory("android.intent.category.DEFAULT");
                intent.setAction("android.intent.action.VIEW");
                intent.setData(android.net.Uri.parse(
                        new MapDataBuilder("amapuri://route/plan/")
                                .addParamter("sourceApplication", BuildConfig.APPLICATION_ID)
                                .addParamter("dname", direction.getdAddress())
                                .addParamter("dlon", direction.getdPoint().getLongitude()+"")
                                .addParamter("dlat", direction.getdPoint().getLatitude()+"")
                                .addParamter("t", 3+"")
                                .build()));
                intent.setPackage(direction.getPackageData().getPackageName());
                break;
            case 1:
                intent = new Intent();
                intent.setAction("android.intent.action.VIEW");

                intent.setData(android.net.Uri.parse(
                        "baidumap://map/direction?"+
                                "destination=name:"+
                                direction.getdAddress()+
                                "|latlng:"+direction.getdPoint().getLatitude()+
                                ","+direction.getdPoint().getLongitude()+
                                "&mode=riding"
                        ));
                intent.setPackage(direction.getPackageData().getPackageName());
                break;
        }

        if (direction.getPackageData().isInstalled()) {
            if (intent !=null) {
                try {
                    startActivity(intent);
                } catch (ActivityNotFoundException e) {
                    e.printStackTrace();
                }
            }
        } else {
            Toast.makeText(ThirdMapDirection.this, "请先安装" + direction.getPackageData().getLabel() + "APP", Toast.LENGTH_LONG).show();
        }
    }

    public interface SimpleAction{
        void action(DirectionBean direction);
    }
}
