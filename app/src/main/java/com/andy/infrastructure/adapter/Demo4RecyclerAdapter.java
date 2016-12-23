package com.andy.infrastructure.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

import com.andy.baselibrary.adapter.BaseRecyclerdapter;
import com.andy.infrastructure.R;
import com.andy.infrastructure.bean.DemoActivityBean;
import com.andy.infrastructure.holder.DemoActivityHolder;
import com.andy.infrastructure.rxjava.DemoRxJavaActivity;

/**
 * Created by Andy on 2016/12/20.
 */

public class Demo4RecyclerAdapter extends BaseRecyclerdapter<DemoActivityBean> {
    public Demo4RecyclerAdapter(Context mContext) {
        super(mContext);
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        DemoActivityHolder holder = new DemoActivityHolder(mLayoutInflater.inflate(R.layout.act_main_recyeler_item_layout, parent, false));
        return holder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        DemoActivityHolder h = (DemoActivityHolder) holder;
        final DemoActivityBean bean = getItem(position);
        if (bean !=null) {
            h.tvDescDemo.setText(bean.getDesc());
        }
//        h.tvDescDemo.setText("Stack Stack");
        h.tvDescDemo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(mContext, bean.getClassName());
                mContext.startActivity(intent);
            }
        });
    }
}
