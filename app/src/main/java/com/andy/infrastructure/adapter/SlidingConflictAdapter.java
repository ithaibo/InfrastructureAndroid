package com.andy.infrastructure.adapter;

import android.content.Context;
import android.view.View;

import com.andy.baselibrary.adapter.AbstractListAdapter;
import com.andy.baselibrary.holder.BaseHolder;
import com.andy.infrastructure.R;
import com.andy.infrastructure.holder.SlidingConflictHolder;


/**
 * Created by Andy on 2017/2/9.
 */

public class SlidingConflictAdapter extends AbstractListAdapter<String> {

    public SlidingConflictAdapter(Context mContext) {
        super(mContext);
    }

    @Override
    public int getItemLayoutId() {
        return R.layout.inner_view_item_sliding_conflict;
    }

    @Override
    public void setItemView(BaseHolder holder, String item, View container) {
        SlidingConflictHolder h = (SlidingConflictHolder) holder;
        h.tvInnerItemConflict.setText(item);
    }

    @Override
    public void setItemOnClickListener(int position, BaseHolder holder) {

    }

    @Override
    public BaseHolder getHolder(View view) {
        return new SlidingConflictHolder(view);
    }
}
