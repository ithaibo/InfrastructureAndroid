package com.andy.infrastructure.holder;

import android.databinding.DataBindingUtil;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.andy.infrastructure.bean.MeiziData;
import com.andy.infrastructure.demos.mvp.MeiziDataBind;

/**
 * Created by Andy on 2017/3/7.
 */

public class MeiziHolder extends RecyclerView.ViewHolder {
    private MeiziDataBind mBind;
    public MeiziHolder(View itemView) {
        super(itemView);
        mBind = DataBindingUtil.bind(itemView);
    }

    public void bind(MeiziData meizi) {
        mBind.setMeizi(meizi);
    }
}
