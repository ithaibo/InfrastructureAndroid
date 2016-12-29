package com.andy.infrastructure.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;

import com.andy.baselibrary.adapter.BaseRecyclerdapter;
import com.andy.infrastructure.R;
import com.andy.infrastructure.bean.DemoRxJavaBean;
import com.andy.infrastructure.holder.DemoRxJavaHolder;

/**
 * Created by Andy on 2016/12/28.
 */

public class DemoRxJavaAdapter extends BaseRecyclerdapter<DemoRxJavaBean> {

    public DemoRxJavaAdapter(Context mContext) {
        super(mContext);
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        DemoRxJavaHolder holder = new DemoRxJavaHolder(
                mLayoutInflater.inflate(R.layout.tv_title_demo_rxjava, parent, false));
        return holder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        DemoRxJavaHolder h = (DemoRxJavaHolder) holder;
        DemoRxJavaBean bean = getItem(position);
        h.tvTitle.setText(bean.getDemoTitle());
    }
}
