package com.andy.baselibrary.holder;

import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.support.v7.widget.RecyclerView;
import android.view.View;

/**
 * Created by Andy on 2017/3/8.
 */

public abstract class DataBindRecyclerHolder<T> extends RecyclerView.ViewHolder {
    /**DataBinding对象*/
    protected ViewDataBinding mBind;

    public DataBindRecyclerHolder(View itemView) {
        super(itemView);
        mBind = DataBindingUtil.bind(itemView);
    }

    /**
     * 具体的绑定操作
     * @param data
     */
    public abstract void bind(T data);
}
