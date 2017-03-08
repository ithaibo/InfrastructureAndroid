package com.andy.infrastructure.adapter;

import android.content.Context;
import android.view.View;

import com.andy.baselibrary.adapter.DataBindRecyclerAdapter;
import com.andy.baselibrary.holder.DataBindRecyclerHolder;
import com.andy.infrastructure.bean.MeiziData;
import com.andy.infrastructure.holder.MeiziHolder;

import org.jetbrains.annotations.NotNull;

/**
 * Created by Andy on 2017/3/8.
 */

public class MeiziAdapter extends DataBindRecyclerAdapter<MeiziData> {
    public MeiziAdapter(Context mContext, @NotNull int idResLayout) {
        super(mContext, idResLayout);
    }

    @Override
    public DataBindRecyclerHolder<MeiziData> getHolder(View itemView) {
        return new MeiziHolder(itemView);
    }
}
