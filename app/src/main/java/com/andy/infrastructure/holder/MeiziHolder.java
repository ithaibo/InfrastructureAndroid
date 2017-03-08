package com.andy.infrastructure.holder;

import android.view.View;

import com.andy.baselibrary.holder.DataBindRecyclerHolder;
import com.andy.infrastructure.bean.MeiziData;
import com.andy.infrastructure.demos.mvp.MeiziDataBind;

/**
 * Created by Andy on 2017/3/7.
 */

public class MeiziHolder extends DataBindRecyclerHolder<MeiziData> {
    public MeiziHolder(View itemView) {
        super(itemView);
    }

    @Override
    public void bind(MeiziData data) {
        ((MeiziDataBind)mBind).setMeizi(data);
    }
}
