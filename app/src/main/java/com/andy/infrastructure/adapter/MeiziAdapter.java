package com.andy.infrastructure.adapter;

import android.content.Context;
import android.view.View;

import com.andy.baselibrary.adapter.DataBindRecyclerAdapter;
import com.andy.baselibrary.holder.DataBindRecyclerHolder;
import com.andy.infrastructure.R;
import com.andy.infrastructure.bean.MeiziData;
import com.andy.infrastructure.holder.MeiziHolder;


/**
 * Created by Andy on 2017/3/8.
 */

public class MeiziAdapter extends DataBindRecyclerAdapter<MeiziData> {
    public MeiziAdapter(Context mContext) {
        super(mContext);
    }

    @Override
    public int getLayoutId() {
        return R.layout.item_meizi_mvp;
    }

    @Override
    public DataBindRecyclerHolder<MeiziData> getHolder(View itemView) {
        return new MeiziHolder(itemView);
    }
}
