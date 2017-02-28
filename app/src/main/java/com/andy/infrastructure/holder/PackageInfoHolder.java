package com.andy.infrastructure.holder;

import android.databinding.DataBindingUtil;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.andy.infrastructure.demos.other.PackageBind;
import com.andy.infrastructure.module.PackageModule;

/**
 * Created by Andy on 2017/2/28.
 */

public class PackageInfoHolder extends RecyclerView.ViewHolder {
    private PackageBind mBind;
    public PackageInfoHolder(View itemView) {
        super(itemView);

        mBind = DataBindingUtil.bind(itemView);
    }

    public void bind(PackageModule info) {
        mBind.setPackageInfo(info);
    }
}
