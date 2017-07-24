package com.andy.infrastructure.demos.map;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.view.View;

import com.andy.baselibrary.activity.DataBindActivity;
import com.andy.baselibrary.utils.CommonUtils;
import com.andy.infrastructure.R;
import com.andy.infrastructure.databinding.MapDirectionBinding;

import java.net.URISyntaxException;
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
        directionList.add(new DirectionBean("com.autonavi.minimap", 0));
        directionList.add(new DirectionBean("com.baidu.BaiduMap", 1));

        checkMapPackageInstalled();
    }

    private void checkMapPackageInstalled() {
        if (CommonUtils.isListEmpty(directionList)){
            return;
        }

        for (DirectionBean direction : directionList) {
            try {
                PackageInfo packageInfo = getPackageManager().getPackageInfo(direction.getPackageName(), PackageManager.GET_ACTIVITIES);
                if (packageInfo!=null) {
                    direction.setInstalled(true);
                    direction.setLabel(getPackageManager().getApplicationLabel(getPackageManager().getApplicationInfo(direction.getPackageName(), 0)).toString());
                }
            } catch (PackageManager.NameNotFoundException e) {
                e.printStackTrace();
                direction.setInstalled(false);
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
        public void action(DirectionBean direction) {
            if (direction.isInstalled()) {
                Intent intent = null;
                switch (direction.getPriority()) {
                    case 0:
                        intent = new Intent();
                        intent.addCategory("android.intent.category.DEFAULT");
                        intent.setAction("android.intent.action.VIEW");
                        intent.setData(android.net.Uri.parse("amapuri://route/plan/?"));
                        intent.setPackage(direction.getPackageName());
                        break;
                    case 1:
                        intent = new Intent();
                        intent.setAction("android.intent.action.VIEW");
                        intent.setData(android.net.Uri.parse("baidumap://map/direction"));
                        intent.setPackage(direction.getPackageName());
                        break;
                }
                if (intent !=null) {
                    try {
                        startActivity(intent);
                    } catch (ActivityNotFoundException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    };

    private String directionDataBuild(String itemData, String itemKey) {
        Map<String, String> data = new HashMap<>();
        data.put(itemKey, itemData);

        Set<String> keySet = data.keySet();
        for (String key : keySet) {
            String value = data.get(key);
        }

        return null;
    }

    class MapDataBuilder{
        String schemeHost;

        public MapDataBuilder(String schemeHost) {
            this.schemeHost = schemeHost;
        }

        public MapDataBuilder addParamter(String paramKey, String paramValue){
            //add data
            return this;
        }
    }

    public interface SimpleAction{
        void action(DirectionBean direction);
    }
}
