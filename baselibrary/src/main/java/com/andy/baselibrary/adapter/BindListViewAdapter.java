package com.andy.baselibrary.adapter;

import android.content.Context;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;


import com.andy.baselibrary.utils.CommonUtils;

import java.util.List;

/**
 * Created by Andy on 2017/6/12.
 */

public abstract class BindListViewAdapter<T, DB extends ViewDataBinding> extends BaseAdapter {
    protected Context mContext;
    protected List<T> items;

    public BindListViewAdapter(Context mContext) {
        this.mContext = mContext;
    }

    @Override
    public int getCount() {
        return CommonUtils.isListEmpty(items)? 0 : items.size();
    }

    @Override
    public T getItem(int position) {
        return CommonUtils.isListEmpty(items)? null : items.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    abstract int getItemlayout();

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        DB itemBinding = DataBindingUtil.getBinding(convertView);
        if(itemBinding == null){
            itemBinding = DataBindingUtil.inflate(LayoutInflater.from(mContext), getItemlayout(), parent, false);
        }
        onBindView(itemBinding, getItem(position), position);
        return itemBinding.getRoot();
    }

    public void appendItem(List<T> datas) {
        if (CommonUtils.isListEmpty(datas)) {
            return;
        }
        items.addAll(datas);
        notifyDataSetChanged();
    }

    abstract void onBindView(DB itemBind, T data, int position);

    public void refreshItems(List<T> datas) {
        items = datas;
        notifyDataSetChanged();
    }
}
