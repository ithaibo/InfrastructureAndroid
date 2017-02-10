package com.andy.baselibrary.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.andy.baselibrary.holder.BaseHolder;
import com.andy.baselibrary.utils.CommonUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Andy on 2016/11/15.
 */

public abstract class AbstractListAdapter<T> extends BaseAdapter {
    protected Context mContext;
    private List<T> dataList;

    public AbstractListAdapter(Context mContext) {
        this.mContext = mContext;
    }

    public void initData(List<T> data) {
        this.dataList = data;
    }

    public void refreshData(List<T> data) {
        this.dataList = data;
        notifyDataSetChanged();
    }

    public void appendData(List<T> data) {
        if (CommonUtils.isListEmpty(this.dataList)){
            this.dataList = new ArrayList<>();
        }
        data.addAll(data);
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return CommonUtils.isListEmpty(dataList)? 0 : dataList.size();
    }

    @Override
    public T getItem(int position) {
        return CommonUtils.isListEmpty(dataList)? null : dataList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    public abstract int getItemLayoutId();

    @Override
    public View getView(int position, View view, ViewGroup viewGroup) {
        BaseHolder holder;
        if (view == null) {
            view = View.inflate(mContext, getItemLayoutId(), null);
            holder = getHolder(view);
            view.setTag(holder);
        } else {
            holder = (BaseHolder) view.getTag();
        }
        T item = getItem(position);
        setItemView(holder, item, view);
        setItemOnClickListener(position, holder);
        return view;
    }

    /**
     *
     * @param holder
     * @param item
     */
    public abstract void setItemView(BaseHolder holder, T item, View container);

    /**
     *
     * @param holder
     */
    public abstract void setItemOnClickListener(int position, BaseHolder holder);

    public abstract BaseHolder getHolder(View view);
}
