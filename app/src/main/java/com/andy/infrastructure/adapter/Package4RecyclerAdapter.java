package com.andy.infrastructure.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.andy.baselibrary.adapter.BaseRecyclerdapter;
import com.andy.infrastructure.R;
import com.andy.infrastructure.holder.PackageInfoHolder;
import com.andy.infrastructure.module.PackageModule;

/**
 * Created by Andy on 2017/2/28.
 */

public class Package4RecyclerAdapter extends BaseRecyclerdapter<PackageModule> {

    public Package4RecyclerAdapter(Context mContext) {
        super(mContext);
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View rootView = mLayoutInflater.inflate(R.layout.item_package_info, parent, false);

        return new PackageInfoHolder(rootView);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        PackageInfoHolder bindHolder = (PackageInfoHolder) holder;
        bindHolder.bind(getItem(position));
    }
}
