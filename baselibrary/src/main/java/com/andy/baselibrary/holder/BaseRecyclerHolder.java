package com.andy.baselibrary.holder;

import android.support.v7.widget.RecyclerView;
import android.view.View;

import butterknife.ButterKnife;

/**
 * Created by Andy on 2016/11/23.
 */

public class BaseRecyclerHolder extends RecyclerView.ViewHolder {
    public BaseRecyclerHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
    }
}
