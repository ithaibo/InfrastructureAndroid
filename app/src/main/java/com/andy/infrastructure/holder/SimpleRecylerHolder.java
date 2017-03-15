package com.andy.infrastructure.holder;

import android.content.Context;
import android.view.View;

import com.andy.baselibrary.holder.DataBindRecyclerHolder;
import com.andy.infrastructure.SimpleDataBind;

/**
 * Created by Andy on 2017/3/15.
 */

public class SimpleRecylerHolder extends DataBindRecyclerHolder<String> {
    private Context mContext;
    public SimpleRecylerHolder(View itemView) {
        super(itemView);
        this.mContext = itemView.getContext();
    }

    @Override
    public void bind(String data) {
        SimpleDataBind simpleDataBind = (SimpleDataBind) mBind;
        simpleDataBind.setLabelText(data);
    }

}
