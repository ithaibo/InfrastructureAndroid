package com.andy.infrastructure.demos.other;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;

import com.andy.baselibrary.activity.BaseActivity;
import com.andy.infrastructure.R;
import com.andy.infrastructure.module.PackageModule;

import java.util.List;

/**
 * Created by Andy on 2017/2/28.
 */

public class PackageInfoActivity extends BaseActivity {


    @Override
    protected int getLayoutId() {
        return R.layout.other_act_package_info;
    }

    @Override
    protected void initData() {
        PackageManager packageManager = getPackageManager();
        List<PackageInfo> packageList = packageManager.getInstalledPackages(0);

    }

    @Override
    protected void initViews() {

    }
}
