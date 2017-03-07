package com.andy.infrastructure.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

import com.andy.baselibrary.adapter.BaseRecyclerdapter;
import com.andy.infrastructure.R;
import com.andy.infrastructure.bean.MeiziData;
import com.andy.infrastructure.holder.MeiziHolder;

/**
 * Created by Andy on 2017/3/7.
 */

public class MeiziAdapter extends BaseRecyclerdapter<MeiziData> {
    public MeiziAdapter(Context mContext) {
        super(mContext);
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View rootView = mLayoutInflater.inflate(R.layout.item_meizi_mvp, parent, false);
        return new MeiziHolder(rootView);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        MeiziHolder meiziHolder = (MeiziHolder) holder;
        meiziHolder.bind(getItem(position));
    }
}
