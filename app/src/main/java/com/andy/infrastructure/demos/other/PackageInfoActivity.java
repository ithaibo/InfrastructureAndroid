package com.andy.infrastructure.demos.other;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.os.Bundle;
import android.os.Environment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.andy.baselibrary.activity.BaseActivity;
import com.andy.baselibrary.utils.LogUtil;
import com.andy.infrastructure.R;
import com.andy.infrastructure.adapter.Package4RecyclerAdapter;
import com.andy.infrastructure.module.PackageModule;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * Created by Andy on 2017/2/28.
 */

public class PackageInfoActivity extends BaseActivity {

    @BindView(R.id.lv_packages_install_other)
    RecyclerView lvPackagesInstallOther;

    @Override
    protected int getLayoutId() {
        return R.layout.other_act_package_info;
    }

    @Override
    protected void initData() {
        lvPackagesInstallOther.setLayoutManager(new LinearLayoutManager(this));
        lvPackagesInstallOther.setHasFixedSize(false);

        PackageManager packageManager = getPackageManager();
        List<ResolveInfo> packageList = packageManager.queryIntentActivities(new Intent(Intent.ACTION_MAIN, null), PackageManager.MATCH_DEFAULT_ONLY);
        List<PackageModule> modules = new ArrayList<>();

        for (ResolveInfo info : packageList) {
            PackageModule item = new PackageModule();
            item.setAppName(info.loadLabel(packageManager) + "");
            item.setApkName(info.activityInfo.packageName);
            item.setAppIcon(info.loadIcon(packageManager));

            modules.add(item);
        }

        packageList = null;

        Package4RecyclerAdapter adapter = new Package4RecyclerAdapter(this);
        adapter.initData(modules);

        lvPackagesInstallOther.setAdapter(adapter);
    }

    @Override
    protected void initViews() {
        pullAnrTrace();
    }

    private void pullAnrTrace() {
        String status = Environment.getExternalStorageState();
        if (status.equals(Environment.MEDIA_MOUNTED)) {
            File sd = Environment.getExternalStorageDirectory();
            String path = sd.getPath();
            FileReader input = null;
            FileWriter output = null;
            try {
                File file = new File("/data/anr/traces.txt");
                if(file.exists()){
                    input = new FileReader(file);
                    output = new FileWriter(path + "/trace.txt");
                    int read = input.read();
                    while ( read != -1 ) {
                        output.write(read);
                        read = input.read();
                    }
                    LogUtil.i("trace document pull success");
                } else {
                    LogUtil.e("trace document is null!");
                }
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                try{if(null != input) input.close(); }catch (Exception e) {}
                try{if(null != output) output.close(); }catch (Exception e) {}
            }
        }
    }
}
