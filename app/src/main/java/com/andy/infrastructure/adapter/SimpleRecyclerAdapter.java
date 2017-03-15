package com.andy.infrastructure.adapter;

import android.content.Context;
import android.view.View;

import com.andy.baselibrary.adapter.DataBindRecyclerAdapter;
import com.andy.baselibrary.holder.DataBindRecyclerHolder;
import com.andy.infrastructure.R;
import com.andy.infrastructure.holder.SimpleRecylerHolder;

/**
 * Created by Andy on 2017/3/15.
 */

public class SimpleRecyclerAdapter extends DataBindRecyclerAdapter<String> {
    private final int idTv = 0x12324;

    public SimpleRecyclerAdapter(Context mContext) {
        super(mContext);
    }

    @Override
    public DataBindRecyclerHolder<String> getHolder(View itemView) {
        return new SimpleRecylerHolder(itemView);
    }

    @Override
    public int getLayoutId() {
        return R.layout.item_simple_recyler;
    }
}
